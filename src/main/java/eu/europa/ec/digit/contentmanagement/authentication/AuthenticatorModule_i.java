package eu.europa.ec.digit.contentmanagement.authentication;

import eu.europa.ec.digit.contentmanagement.domain.api.ServerModule_i;
import eu.europa.ec.digit.contentmanagement.domain.api.entities.user.User_i;

/**
 * 
 * @author bentsth
 */
public interface AuthenticatorModule_i extends ServerModule_i {

    User_i authenticate(Credentials_i credentials);
    
}
