package services.webplus.polling.api.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.webplus.polling.api.services.AccountService;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> loadAccounts() {
        return ResponseEntity.ok("teste");
    }
}
