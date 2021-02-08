package eu.europa.ec.digit.contentmanagement.authentication;

import org.apache.log4j.Logger;

import eu.europa.ec.digit.contentmanagement.domain.api.util.EccmUtils;

/**
 * 
 * @author bentsth
 */
public class AuthenticatorModuleFactory {
    
    private static final Logger logger = Logger.getLogger(AuthenticatorModuleFactory.class);
    private static volatile AuthenticatorModule_i authenticator;

    public static AuthenticatorModule_i getModule() throws Exception {
        if (authenticator == null) {
            synchronized (AuthenticatorModuleFactory.class) {
                if (authenticator == null) {
                    Class<?> clazz = EccmUtils.getImplementingClass(AbstractAuthenticatorModule.class);
                    if(clazz != null) {
                        authenticator = (AuthenticatorModule_i) clazz.getDeclaredConstructor().newInstance();
                        Runtime.getRuntime().addShutdownHook(new Thread() {
                            public void run() {
                                try {
                                    authenticator.close();
                                } catch (Exception e) {
                                    logger.warn("", e);
                                }
                            }
                        });
                    }
                }
            }
        }

        return authenticator;
    }
}
