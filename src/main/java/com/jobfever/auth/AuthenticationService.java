package com.jobfever.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobfever.config.JwtService;
import com.jobfever.model.Candidate;
import com.jobfever.model.Employer;
import com.jobfever.model.User;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.repository.EmployerRepository;
import com.jobfever.repository.UserRepository;
import com.jobfever.role.RoleType;
import com.jobfever.service.SendingEmailService;
import com.jobfever.token.Token;
import com.jobfever.token.TokenRepository;
import com.jobfever.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final EmployerRepository employerRepository;
    private final CandidateRepository candidateRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SendingEmailService sendingEmailService;

    public AuthenticationResponse register(RegisterRequest request) {
        String defaultImgFilename = "looogo.png";
        var candidate = Candidate.builder()
                .imgFileName(defaultImgFilename)
                .build();
        candidateRepository.save(candidate);

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleType(RoleType.CANDIDATE)
                .candidate_id(repository.findCandidateLastId())
                .build();
        return getAuthenticationResponse(user);
    }

    public AuthenticationResponse registerEmployer(RegisterRequest request) {
        String defaultImgFilename = "looogo.png";
        var employer = Employer.builder()
                .companyName(request.getCompanyName())
                .nameAndSurname(request.getNameAndSurname())
                .phoneNumber(request.getPhoneNumber())
                .imgFileName(defaultImgFilename)
                .build();
        employerRepository.save(employer);

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleType(RoleType.EMPLOYER)
                .employer_id(repository.findEmployerLastId())
                .build();
        return getAuthenticationResponse(user);
    }

    private AuthenticationResponse getAuthenticationResponse(User user) {
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        if (user.getEmployer_id() == null) {
            return buildCandidateAuthenticationResponse(user, jwtToken, refreshToken);
        } else {
            return buildEmployerAuthenticationResponse(user, jwtToken, refreshToken);
        }
    }

    private static AuthenticationResponse buildEmployerAuthenticationResponse(User user, String jwtToken, String refreshToken) {
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .name(user.getEmail())
                .role(user.getRoleType())
                .employer_id(user.getEmployer_id())
                .build();
    }

    private static AuthenticationResponse buildCandidateAuthenticationResponse(User user, String jwtToken, String refreshToken) {
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .name(user.getEmail())
                .role(user.getRoleType())
                .candidate_id(user.getCandidate_id())
                .build();
    }

    public String forgotPassword(String email) {
        Optional<User> userOptional = repository.findByEmail(email);
        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }
        User user = userOptional.get();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        sendingEmailService.sendRecoveryMessage(email, "Password recovery", "http://localhost:3000/change-password/" + jwtToken);
        return jwtToken;
    }

    public String resetPassword(String token, String password) {
        Optional<Token> tokenObj = tokenRepository.findByToken(token);
        Optional<User> userOptional = repository.findByTokensContains(tokenObj.get());

        if (!userOptional.isPresent()) {
            return "Invalid token.";
        }

        User user = userOptional.get();

        user.setPassword(passwordEncoder.encode(password));

        repository.save(user);
        return "Your password successfully updated.";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request, RoleType roleType) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        if (user.getEmployer_id() == null) {
            return buildCandidateAuthenticationResponse(user, jwtToken, refreshToken);
        } else {
            return buildEmployerAuthenticationResponse(user, jwtToken, refreshToken);
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}

