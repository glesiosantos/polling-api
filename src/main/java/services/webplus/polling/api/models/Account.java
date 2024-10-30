package services.webplus.polling.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import services.webplus.polling.api.enuns.Role;
import services.webplus.polling.api.web.payloads.SignUpRequest;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends DateAudit{

    private String name;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"))
    private Set<Role> roles = new HashSet<>();

    public static Account convertRequestToModel(SignUpRequest request, PasswordEncoder passwordEncoder) {
        return Account.builder()
                .name(request.name())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(Role.USER))
                .createdAt(Instant.now())
                .build();
    }

    public boolean isCorrectPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
