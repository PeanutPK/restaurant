package ku.cs.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

public class UserInfoResponse {
    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String role;

    public UserInfoResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }

}
