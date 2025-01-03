package com.honeybadger.demowebapp.dbops.services;

import org.springframework.stereotype.Service;

import com.honeybadger.demowebapp.dbops.entities.School;
import com.honeybadger.demowebapp.dbops.ui.SchoolRequest;
import com.honeybadger.demowebapp.dbops.ui.SchoolResponse;

import lombok.Data;

@Data
@Service
public class SchoolMapper {
    public School schoolRequestToSchool(SchoolRequest schoolRequest) {
        return School.builder()
                .schoolName(schoolRequest.getSchoolName())
                .build();
    }

    public SchoolResponse schoolToSchoolResponse(School school) {
        return SchoolResponse.builder()
                .schoolId(school.getId())
                .schoolName(school.getSchoolName())
                .build();

    }

}
