package services.webplus.polling.api.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.webplus.polling.api.services.AccountService;
import services.webplus.polling.api.web.payloads.SignUpRequest;

import java.net.URI;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> singUp(@RequestBody SignUpRequest request) {
        var account = accountService.add(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("v1/accounts/${id}")
                .buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping
    public ResponseEntity<?> singIn() {
        return ResponseEntity.ok("token");
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> forgot() {
        return ResponseEntity.noContent().build();
    }
}
