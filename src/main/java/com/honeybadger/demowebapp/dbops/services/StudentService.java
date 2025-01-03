package com.honeybadger.demowebapp.dbops.services;


import com.honeybadger.demowebapp.dbops.repo.StudentRepository;
import com.honeybadger.demowebapp.dbops.ui.StudentUserInput;
import com.honeybadger.demowebapp.dbops.ui.StudentUserOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentRepository studentRepository;

    public StudentUserOutput saveStudent(StudentUserInput studentUserInput) {
        return studentMapper.toStudentUserOutput(
                studentRepository.save(
                        studentMapper.toStudent(studentUserInput)
                )
        );
    }

    public StudentUserOutput getStudentByID(Integer id) {
        return studentMapper.toStudentUserOutput(
                studentRepository
                        .findById(id)
                        .orElse(null));
    }

    public List<StudentUserOutput> saveStudents(List<StudentUserInput> studentRecords) {
        var students = studentRepository.saveAll(
                studentRecords
                        .stream()
                        .map(studentMapper::toStudent)
                        .collect(Collectors.toList())
        );
        return studentMapper.toStudentUserOutputList(students);
    }

    public List<StudentUserOutput> getAllStudents() {
        return studentMapper.toStudentUserOutputList(
                studentRepository.findAll());


    }

    public List<StudentUserOutput> filter_by_name(String filterCondition) {
        return studentRepository.findAllByFirstNameContaining(filterCondition)
                .stream()
                .map(studentMapper::toStudentUserOutput)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
