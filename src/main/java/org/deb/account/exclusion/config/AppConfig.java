package org.deb.account.exclusion.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfig {

    
    @Value("${security_enabled:true}")
    private boolean securityEnabled;

    @Value("${monthly.batch_file}")
    private String fileNameWithPath;
    
    /**
     * Constructor
     */
    public AppConfig() {
        super();
    }
	

    
}
