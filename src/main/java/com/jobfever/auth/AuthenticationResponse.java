package com.jobfever.auth;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobfever.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("name")
    private String name;
    @JsonProperty("role")
    private RoleType role;
    @JsonProperty("employer_id")
    private int employer_id;
    @JsonProperty("candidate_id")
    private int candidate_id;
}
