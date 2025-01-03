package com.honeybadger.demowebapp;

import com.honeybadger.demowebapp.security.AppUser;
import com.honeybadger.demowebapp.security.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SecretKeyMaker {

    @Test
    public void generateSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.printf("\nKey = [%s]\n", encodedKey);
        System.out.println("Expiration duration:" + TimeUnit.DAYS.toMillis(1));
        System.out.println("RefreshToken duration:" + TimeUnit.MINUTES.toMillis(30));
    }

    @Test
    public void TestRoleCollection() {
        Set<Role> roles = Set.of(Role.ADMIN, Role.MANAGER);
        //System.out.println(roles.stream().map(Role::getAuthorities).toList());
        System.out.println(roles.stream().map(Role::getAuthorities).flatMap(Collection::stream)
                .collect(Collectors.toSet()));


    }

    @Test
    public void TestStringArrayToEnum() {

        String[] rls = {"ADMIN", "USER"};

        System.out.println(Arrays.stream(rls).map(x -> Role.valueOf(x).getAuthorities()).flatMap(Collection::stream)
                .collect(Collectors.toSet()));
    }


    @Test
    public void TestJsonRepresentationOfEnumSet() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        AppUser appUser = AppUser.builder()
                .firstName("David")
                .lastName("Parasu")
                .email("david.parasu@gmail.com")
                .RegistrationId(String.valueOf(UUID.randomUUID()))
                .roles("USER,ADMIN")
                .build();
        System.out.println(objectMapper.writeValueAsString(appUser));


        Set<Role> roles = Set.of(Role.ADMIN, Role.MANAGER);
        System.out.println(roles);
        System.out.println(objectMapper.writeValueAsString(roles));

    }

}
//import io.jsonwebtoken.Jwts;