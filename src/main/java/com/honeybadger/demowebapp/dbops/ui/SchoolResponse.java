package com.honeybadger.demowebapp.dbops.ui;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SchoolResponse {
    private int schoolId;
    private String schoolName;
}
