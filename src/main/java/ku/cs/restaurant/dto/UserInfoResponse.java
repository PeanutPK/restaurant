package ku.cs.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

public class UserInfoResponse {
    @Setter
    @Getter
    private String username;

    public UserInfoResponse(String username) {
        this.username = username;
    }

}
