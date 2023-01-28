package com.online.diary.authentication;

import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
    @Autowired
    UserFacade userFacade;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserForm account = userFacade.getUserByUsername(username) ;
        if(account.getUsername()==null){
            throw new UsernameNotFoundException("User: "+ username + " is null");
        }
        List<GrantedAuthority> grantList = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+account.getRole());

        grantList.add(authority);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(account.getUsername(),encoder.encode(account.getPassword()), enabled,
                accountNonExpired,credentialsNonExpired,accountNonLocked,grantList);

        return userDetails;
    }
}
