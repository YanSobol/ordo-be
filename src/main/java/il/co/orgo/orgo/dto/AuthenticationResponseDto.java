package il.co.orgo.orgo.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String username;
    private String token;

    public AuthenticationResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
