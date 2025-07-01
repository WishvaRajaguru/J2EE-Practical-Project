package lk.rajaguru.app.web.security;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.service.UserService;

import java.util.Set;

@ApplicationScoped
public class AppIdentityStore implements IdentityStore {

    @EJB
    private UserService userService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential upc) {
            if (userService.validate(upc.getCaller(), upc.getPasswordAsString())) {
                User user = userService.getUserByEmail(upc.getCaller());
                return new CredentialValidationResult(upc.getCaller(), Set.of(user.getUserType().name()));
            }
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
