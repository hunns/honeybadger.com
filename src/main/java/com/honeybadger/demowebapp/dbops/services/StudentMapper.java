package com.honeybadger.demowebapp.dbops.services;


import com.honeybadger.demowebapp.dbops.entities.Student;
import com.honeybadger.demowebapp.dbops.repo.SchoolRepository;
import com.honeybadger.demowebapp.dbops.ui.StudentUserInput;
import com.honeybadger.demowebapp.dbops.ui.StudentUserOutput;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class StudentMapper {

    @Autowired
    private SchoolRepository schoolRepository;

    public Student toStudent(StudentUserInput studentRecord) {
       if (studentRecord == null){
           throw new NullPointerException("User input cannot be empty");
        }

       return Student.builder().id(studentRecord.std_Id())
               .id(studentRecord.std_Id())
               .firstName(studentRecord.f_name())
               .lastName(studentRecord.l_name())
               .mailID(studentRecord.email())
               .school(studentRecord.school())
               .build();


    }

    public List<Student> toStudentList(List<StudentUserInput> studentRecords) {
        return studentRecords
                .stream()
                .map(studentRecord ->
                        Student.builder()
                                .id(studentRecord.std_Id())
                                .firstName(studentRecord.f_name())
                                .lastName(studentRecord.l_name())
                                .mailID(studentRecord.email())
                                .school((studentRecord.school()))
                                .build()).collect(Collectors.toList());


    }

    public List<StudentUserInput> toStudentRecordList(List<Student> students) {
        ArrayList<StudentUserInput> studentRecords = new ArrayList<StudentUserInput>();

        for (Student student : students)
            studentRecords.add(
                    new StudentUserInput(student.getId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getMailID(),
                            student.getSchool()));

        return studentRecords;
    }

    public List<StudentUserOutput> toStudentUserOutputList(List<Student> students) {
        return students.stream().map(student -> new StudentUserOutput
                (
                        student.getFirstName(),
                        student.getLastName(),
                        student.getMailID(),
                        student.getSchool().getSchoolName() == null ? schoolRepository.getReferenceById(student.getSchool().getId()).getSchoolName() : student.getSchool().getSchoolName()
                )

        ).collect(Collectors.toList());

    }

    public StudentUserOutput toStudentUserOutput(Student student) {
        return new StudentUserOutput(
                student.getFirstName(),
                student.getLastName(),
                student.getMailID(),
                student.getSchool().getSchoolName() == null ? schoolRepository.getReferenceById(student.getSchool().getId()).getSchoolName() : student.getSchool().getSchoolName()
        );
    }


}
