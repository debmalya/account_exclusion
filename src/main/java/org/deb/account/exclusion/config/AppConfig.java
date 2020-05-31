package org.deb.account.exclusion.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfig {


    @Value("${security_enabled:true}")
    private boolean securityEnabled;



    /**
     * Constructor
     */
    public AppConfig() {
        super();
    }



}
