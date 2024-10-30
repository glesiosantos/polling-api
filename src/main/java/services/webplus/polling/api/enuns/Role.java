package services.webplus.polling.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN","Administrador"), USER("USER","Usuario");

    private final String init;
    private final String name;
}
