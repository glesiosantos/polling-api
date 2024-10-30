package services.webplus.polling.api.services.impl;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.webplus.polling.api.models.Account;
import services.webplus.polling.api.repositories.AccountRepository;
import services.webplus.polling.api.services.AccountService;
import services.webplus.polling.api.web.payloads.SignUpRequest;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<Account> loadAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Account findAccountById(String id) throws Exception {
        return accountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Account not found with id "+ id));
    }

    @Transactional(readOnly = true)
    @Override
    public Account findAccountByEmail(String email) throws Exception {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Account not found with e-mail "+ email));
    }
}
