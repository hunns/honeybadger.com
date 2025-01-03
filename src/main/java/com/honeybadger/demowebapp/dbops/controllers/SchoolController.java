package com.honeybadger.demowebapp.dbops.controllers;


import com.honeybadger.demowebapp.dbops.entities.School;
import com.honeybadger.demowebapp.dbops.services.SchoolService;
import com.honeybadger.demowebapp.dbops.ui.SchoolRequest;
import com.honeybadger.demowebapp.dbops.ui.SchoolResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "School")
@RequestMapping("school")
public class SchoolController {
    @Autowired
    public SchoolService schoolService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }


    @GetMapping("/{school_id}")
    public ResponseEntity<SchoolResponse> getSchool(@PathVariable Integer school_id) {
        return ResponseEntity.ok(schoolService.getSchoolByID(school_id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> addSchool(@RequestBody @Valid SchoolRequest schoolRequest) {
        return ResponseEntity.ok(schoolService.saveSchool(schoolRequest));
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<SchoolResponse>> addSchools(@RequestBody List<School> schools) {
        return ResponseEntity.ok(schoolService.saveSchools(schools));
    }

    @GetMapping("/filter-by-name/{filter_condition}")
    public ResponseEntity<?> findAllByFirstname(@PathVariable String filter_condition) {
        return ResponseEntity.ok(schoolService.filter_by_name(filter_condition));
    }

    @DeleteMapping("/deleteById/{school_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void findAllByFirstname(@PathVariable Integer school_id) {
        schoolService.deleteById(school_id);
    }

}
