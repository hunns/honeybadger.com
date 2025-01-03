package com.honeybadger.demowebapp.dbops.ui;


import com.honeybadger.demowebapp.dbops.entities.School;
import jakarta.validation.constraints.NotEmpty;

public record StudentUserInput(
        Integer std_Id,
        @NotEmpty (message = "First name should not be empty")
        String f_name,
        @NotEmpty (message = "Last name should not be empty")
        String l_name,
        String email,
        School school
) {
}
