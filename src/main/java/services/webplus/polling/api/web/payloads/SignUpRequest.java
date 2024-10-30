package services.webplus.polling.api.web.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        String id,
        @NotBlank(message = "required field") String name,
        @NotBlank(message = "required field") String username,
        @Email(message = "Invalid email") @NotBlank(message = "required field") String email,
        @NotBlank(message = "required field") String password
) {}
