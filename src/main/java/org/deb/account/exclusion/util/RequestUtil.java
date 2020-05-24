package org.deb.account.exclusion.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
public class RequestUtil {
    public String[] getCurrentUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String[] usernameRole = new String[2];
        if (principal instanceof UserDetails) {
            usernameRole[0] = ((UserDetails)principal).getUsername();
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
            if (authorities.size()>0) {
                usernameRole[1] = authorities.iterator().next().toString();
            }
        } else {
            usernameRole[0] = principal.toString();
        }
        return usernameRole;
    }
}
