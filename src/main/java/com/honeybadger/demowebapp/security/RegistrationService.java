package com.honeybadger.demowebapp.security;

import com.honeybadger.demowebapp.customexceptions.securityexeption.RegistrationFailedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityMapper securityMapper;

    @Autowired
    private TokenRepository tokenRepository;

    public MinUserDetail registerNewUser(UserRegistrationInput userRegistrationInput) throws Exception {
        boolean validRoles = isValidRoles(userRegistrationInput.getRoles());
        return securityMapper.appUserToMinUserdetails(appUserRepository.save(securityMapper.userRegistrationInputToUser(userRegistrationInput)));
    }

    private boolean isValidRoles(String roles)  {
        try {
            List<Role> list = Arrays.stream(roles.split(",")).map(x -> Role.valueOf(x.toUpperCase().trim())).toList();
        } catch (IllegalArgumentException e) {

            throw new RegistrationFailedException(
                    e.getMessage().replaceAll("([a-zA-Z0-9_\\.]+\\.)+", "")
                            .replaceAll("No enum constant","Invalid role")
            );
        }
        return true;
    }


    public String Authenticate(@Valid AuthenticationInput authenticationInput) {
        AppUser appUser = appUserRepository.findByEmail(authenticationInput.username()).orElseThrow(() -> new UsernameNotFoundException("User not found, contact Admin"));
        String jwt = jwtService.generateToken(userDetailsService.loadUserByUsername(appUser.getUsername()));

        Token token = Token.builder()
                .expiredAt(jwtService.extractExpiredAt(jwt))
                .appUser(appUser)
                .issuedAt(jwtService.extractIssueAt(jwt))
                .token(jwt)
                .build();
        Token save = tokenRepository.save(token);
        return jwt;
    }
}
