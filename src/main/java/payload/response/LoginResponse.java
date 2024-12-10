package payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private Date issuedAt;
    private Date expiry;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
