package services.webplus.polling.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import services.webplus.polling.api.enuns.Role;
import services.webplus.polling.api.models.Account;
import services.webplus.polling.api.repositories.AccountRepository;
import services.webplus.polling.api.services.AuthService;
import services.webplus.polling.api.web.payloads.SignInRequest;
import services.webplus.polling.api.web.payloads.SignUpRequest;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Override
    public Account createAccount(SignUpRequest request) throws Exception {
        Optional<Account> optional = accountRepository.findByEmail(request.email());

        if(optional.isPresent()) throw new BadCredentialsException("Account already registered with email");

        var account = Account.convertRequestToModel(request, passwordEncoder);
        return accountRepository.save(account);
    }

    @Override
    public String authenticateAccount(SignInRequest request) {
        Optional<Account> optional = accountRepository.findByEmail(request.email());

        if (optional.isEmpty() || !optional.get().isCorrectPassword(request.password(), passwordEncoder))
            throw new BadCredentialsException("E-mail or password is invalid");

        return tokenGenerate(optional.get());
    }

    private String tokenGenerate(Account account) {
        var now = Instant.now();
        var expiresIn = 300L; // tempo do token

        // add o perfil do usuário
        var scopes = account.getRoles().stream().map(Role::getInit).collect(Collectors.joining(" "));

        System.out.println("********** "+scopes);

        var claims = JwtClaimsSet.builder()
                .issuer("polling-api")
                .subject(account.getId())
                .issuedAt(now)
                .claim("scopes", scopes)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
