package com.honeybadger.demowebapp.dbops.ui;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SchoolRequest {

    @NotNull(message = "School Name cannot be blank/null")
    @NotBlank(message = "School Name cannot be blank/null")
    private String schoolName;
}

    


