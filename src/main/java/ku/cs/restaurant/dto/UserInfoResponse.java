package ku.cs.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class UserInfoResponse {
    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String role;

    public UserInfoResponse(String username, String role) {
        this.username = username;
        if (Objects.equals(role, "ROLE_USER"))
            this.role = "User";
        else if (Objects.equals(role, "ROLE_ADMIN"))
            this.role = "Admin";
        else
            this.role = role;
    }

}
