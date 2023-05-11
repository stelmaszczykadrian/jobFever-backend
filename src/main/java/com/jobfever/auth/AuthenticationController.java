package com.jobfever.auth;
import com.jobfever.role.RoleType;
import com.jobfever.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/authentication")
//@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/candidates/register")
    public ResponseEntity<?> registerCandidate(@RequestBody RegisterRequest request) {
        if(userService.isUserExists(request.getEmail())){
            return new ResponseEntity<>("Candidate already exists.", HttpStatus.BAD_REQUEST);
        }
        service.register(request);
        return new ResponseEntity<>("Candidate added successfully.", HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public String checkEmail(@RequestParam String email){
        String response = service.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/api/authentication/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {

        return service.resetPassword(token, password);
    }


    @PostMapping("/authenticate/candidate")
    public ResponseEntity<AuthenticationResponse> authenticateCandidate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request, RoleType.CANDIDATE));
    }

    @PostMapping("/authenticate/employer")
    public ResponseEntity<AuthenticationResponse> authenticateEmployer(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request, RoleType.EMPLOYER));
    }


    @PostMapping("/employers/register")
    public ResponseEntity<?> registerEmployer(@RequestBody RegisterRequest request) {
        if(userService.isUserExists(request.getEmail())){
            return new ResponseEntity<>("Employer already exists.", HttpStatus.BAD_REQUEST);
        }
        service.registerEmployer(request);
        return new ResponseEntity<>("Employer added successfully.", HttpStatus.OK);
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
