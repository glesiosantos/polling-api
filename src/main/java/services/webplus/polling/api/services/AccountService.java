package services.webplus.polling.api.services;

import services.webplus.polling.api.models.Account;
import services.webplus.polling.api.web.payloads.SignUpRequest;

import java.util.List;

public interface AccountService {

    List<Account> loadAll();

    Account findAccountById(String id) throws Exception;

    Account findAccountByEmail(String email) throws Exception;
}
