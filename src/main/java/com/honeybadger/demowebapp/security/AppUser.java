package com.honeybadger.demowebapp.security;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Appuser")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Seq")
    @Column(name = "user_id")
    private Integer id;
    @Column(nullable = false)
    private String  firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String roles;
    @Column(nullable = false)
    private String  RegistrationId;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(roles.split(",")).map(x->Role.valueOf(x.toUpperCase().trim()).getAuthorities()).flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @OneToMany(mappedBy = "appUser")
    private List<Token> tokens;


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
