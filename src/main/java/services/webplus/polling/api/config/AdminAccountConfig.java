package services.webplus.polling.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import services.webplus.polling.api.enuns.Role;
import services.webplus.polling.api.models.Account;
import services.webplus.polling.api.repositories.AccountRepository;

import java.time.Instant;
import java.util.Set;

@Configuration
public class AdminAccountConfig implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Account account = Account.builder()
                .name("Administrador")
                .username("admin")
                .email("admin@polling.com.br")
                .password(passwordEncoder.encode("102030"))
                .roles(Set.of(Role.ADMIN, Role.USER))
                .createdAt(Instant.now())
                .build();

        if (accountRepository.findByEmail(account.getEmail()).isEmpty()) {
            accountRepository.save(account);
            System.out.println("Administrador cadastrado com sucesso!");
        }
    }
}
