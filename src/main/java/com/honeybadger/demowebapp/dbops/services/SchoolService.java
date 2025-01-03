package com.honeybadger.demowebapp.dbops.services;

import com.honeybadger.demowebapp.customexceptions.schoolException.SchoolNotFoundException;
import com.honeybadger.demowebapp.dbops.entities.School;
import com.honeybadger.demowebapp.dbops.repo.SchoolRepository;
import com.honeybadger.demowebapp.dbops.ui.SchoolRequest;
import com.honeybadger.demowebapp.dbops.ui.SchoolResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor

public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private SchoolMapper schoolMapper;

    public SchoolResponse saveSchool(SchoolRequest schoolRequest) {
        return schoolMapper.schoolToSchoolResponse(
                schoolRepository.save(schoolMapper.schoolRequestToSchool(schoolRequest)));

    }

    public SchoolResponse getSchoolByID(Integer id) {
      
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
        return schoolMapper.schoolToSchoolResponse(optionalSchool.get());
        } else {
        throw new SchoolNotFoundException("Error: Student id " + id + " is not found");
        }

       // return Optional.ofNullable( schoolMapper.schoolToSchoolResponse(schoolRepository.findById(id))).orElseThrow(new SchoolNotFoundException("Error: Student id " + id + " is not found"));
    }

    public List<SchoolResponse> getAllSchools() {
        return schoolRepository.findAll().stream().map(schoolMapper::schoolToSchoolResponse).toList();
    }

    public List<SchoolResponse> saveSchools(List<School> schools) {
        return schoolRepository.saveAll(schools).stream().map(schoolMapper::schoolToSchoolResponse).toList();
    }

    public List<SchoolResponse> filter_by_name(String filterCondition) {
        return schoolRepository.findAllBySchoolNameContaining(filterCondition).stream()
                .map(schoolMapper::schoolToSchoolResponse).toList();
    }

    public void deleteById(Integer schoolId) {
        schoolRepository.deleteById(schoolId);
    }
}
