package com.easybytes.easybank.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class EazyBankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username=authentication.getName();
      String password=authentication.getCredentials().toString();

        UserDetails userDetails=userDetailsService.loadUserByUsername(username);

        //Intentionally removing the password check for lower profiles (Dev,Test,etc,.)
        return new UsernamePasswordAuthenticationToken(username, password,userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}