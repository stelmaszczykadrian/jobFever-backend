package com.jobfever.auth;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobfever.model.Employer;
import com.jobfever.model.User;
import com.jobfever.role.RoleType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/candidates/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/employers/register")
    public ResponseEntity<AuthenticationResponse> addEmployer(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerEmployer(request));

//        employerService.addEmployer(Employer.builder()
//                .companyName(json.get("companyName").asText())
//                .nameAndSurname(json.get("nameAndSurname").asText())
//                .phoneNumber(json.get("phoneNumber").asInt()).build());
//        Employer savedEmployer = employerService.getEmployerByName(json.get("companyName").asText());
//        userService.addUser(User.builder()
//                .employer_id(savedEmployer.getId())
//                .email(json.get("email").asText())
//                .candidate_id(null)
//                .roleType(RoleType.EMPLOYER)
//                .password(json.get("password").asText())
//                .build());
//        return new ResponseEntity<>("Employer added successfully.",
//                HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
