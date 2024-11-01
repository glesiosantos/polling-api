package services.webplus.polling.api.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.webplus.polling.api.services.AccountService;
import services.webplus.polling.api.web.payloads.SignInRequest;
import services.webplus.polling.api.web.payloads.SignUpRequest;

import java.net.URI;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> singUp(@RequestBody @Valid SignUpRequest request) {
        var account = accountService.add(request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/accounts/{id}")
                .buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping
    public ResponseEntity<?> singIn(@RequestBody @Valid SignInRequest request) {
        return ResponseEntity.ok("token");
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> forgot() {
        return ResponseEntity.noContent().build();
    }
}
