package services.webplus.polling.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import services.webplus.polling.api.models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
}
