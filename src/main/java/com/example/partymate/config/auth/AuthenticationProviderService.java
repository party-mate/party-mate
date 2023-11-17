package com.example.partymate.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailAddress = authentication.getName();
        String password = authentication
                .getCredentials()
                .toString();
        PrincipalDetails member = principalDetailsService.loadUserByUsername(emailAddress);

        return checkPassword(member, password, bCryptPasswordEncoder);
    }

    private Authentication checkPassword(PrincipalDetails member, String rawPassword, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (bCryptPasswordEncoder.matches(rawPassword, member.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                    member.getUsername(),
                    member.getPassword(),
                    member.getAuthorities());
        }else{
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    @Override
    public boolean supports(Class<?> aClass){
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
