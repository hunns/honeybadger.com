package com.honeybadger.demowebapp.security;

import jakarta.validation.constraints.NotNull;

public record AuthenticationInput(@NotNull String username,@NotNull String password) {

}
