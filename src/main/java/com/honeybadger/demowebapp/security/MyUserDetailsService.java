package com.honeybadger.demowebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<AppUser> appUser= appUserRepository.findByEmail(username);
//        if (appUser.isPresent()){
//            return (UserDetails) appUser.get();
//        }
//        else{
//            throw new UsernameNotFoundException("Username "+ username +" is not valid");
//        }

        return (UserDetails) appUserRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Username "+ username +" is not valid"));


    }
}
