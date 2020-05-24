package org.deb.account.exclusion.security;


import lombok.extern.slf4j.Slf4j;
import org.casbin.jcasbin.main.Enforcer;
import org.deb.account.exclusion.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Service Enforcer
 */
@Service
@Slf4j
public class ServiceEnforcer {
    
    private Enforcer enforcer;


    @Autowired
    private AppConfig appConfig;
    /**
     * Called on Post Construction
     * @throws IOException Exception
     */
    @PostConstruct
    public void onPostConstruct() throws IOException {
        
        // Loading Model and Policy from File System
        final String modelFilename = "config/authorization_model.conf";
        final String policyFilename = "config/authorization_policy.csv";
        
        // Create Enforcer
        enforcer = new Enforcer(modelFilename, policyFilename);
        
        enforcer.enableEnforce(appConfig.isSecurityEnabled());
        
        log.info("Enforcer created");
    }

    /**
     * Get {@link Enforcer} enforcer
     * @return {@link Enforcer} enforcer
     */
    public Enforcer getEnforcer() {
        return enforcer;
    }
    
}
