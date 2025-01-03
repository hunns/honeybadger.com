package com.honeybadger.demowebapp.dbops.controllers;


import com.honeybadger.demowebapp.dbops.services.StudentService;
import com.honeybadger.demowebapp.dbops.ui.StudentUserInput;
import com.honeybadger.demowebapp.dbops.ui.StudentUserOutput;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Student")
public class StudentController {
    //@Autowired
    public static StudentService studentService;

    @GetMapping("/students/{student_id}")
    public StudentUserOutput getStudentByID(@PathVariable Integer student_id) {

        return studentService.getStudentByID(student_id);
    }

    @PostMapping("/students")
    public StudentUserOutput addStudent(
            @Valid @RequestBody StudentUserInput studentUserInput
    ) {
        return studentService.saveStudent(studentUserInput);
    }

    @GetMapping("/students-set")
    public List<StudentUserOutput> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students-set")
    public List<StudentUserOutput> addStudents(@Valid  @RequestBody List<StudentUserInput> studentUserInputs) {
        return studentService.saveStudents(studentUserInputs);
    }

    @GetMapping("/students/filter-by-name/{filter_condition}")
    public List<StudentUserOutput> findAllByFirstname(@PathVariable String filter_condition) {
        return studentService.filter_by_name(filter_condition);
    }

    @DeleteMapping("/students/deleteById/{student_id}")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Integer student_id) {
        studentService.deleteById(student_id);
    }


}
