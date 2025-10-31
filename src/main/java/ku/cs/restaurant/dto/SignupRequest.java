package ku.cs.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
    @Size(min=4, message = "Username is mandatory and at least 4 characters in length")
    private String username;

    @Size(min=8, message = "Password is mandatory and at least 8 characters in length")
    private String password;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$",
            message = "Name can only contain letters")
    private String name;
}
