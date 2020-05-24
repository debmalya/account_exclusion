package org.deb.account.exclusion.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String userName = s;
        String password = "";
        String role = "";

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("Tom".equals(s)){
            password = "mot";
            authorities.add(new SimpleGrantedAuthority("User"));
        }else if ("John".equals(s)){
            password = "nhoj";
            authorities.add(new SimpleGrantedAuthority("Admin"));
        }else {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userName, password, false,false,false,false,authorities);
        return userDetails;
    }
}
