package com.honeybadger.demowebapp.config;

import com.honeybadger.demowebapp.dbops.services.StudentMapper;
import com.honeybadger.demowebapp.security.SecurityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class BeanConig {

  //  @Bean(name = "StudentMapperBean")
    public StudentMapper getStudentMapper(){
        return new StudentMapper();
    }

    @Bean(name ="SecurityMapper")
    public SecurityMapper getSecurityMapper(){
        return new SecurityMapper();
    }

    @Bean(name="Encoder")
    public PasswordEncoder  getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
