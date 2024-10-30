package services.webplus.polling.api.services;

import services.webplus.polling.api.models.Account;
import services.webplus.polling.api.web.payloads.SignInRequest;
import services.webplus.polling.api.web.payloads.SignUpRequest;

public interface AuthService {

    public Account createAccount(SignUpRequest request) throws Exception;

    public String authenticateAccount(SignInRequest request);
}
