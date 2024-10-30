package services.webplus.polling.api.web.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @Email(message = "Invalid email") @NotBlank(message = "required field") String email,
        @NotBlank(message = "required field") String password
) {
}
