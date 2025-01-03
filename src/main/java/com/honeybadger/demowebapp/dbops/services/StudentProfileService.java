package com.honeybadger.demowebapp.dbops.services;


import com.honeybadger.demowebapp.dbops.entities.StudentProfile;
import com.honeybadger.demowebapp.dbops.repo.StudentProfileRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileService {
    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public StudentProfile saveStudentProfile(StudentProfile studentProfile){
        return studentProfileRepository.save(studentProfile);
    }

    public StudentProfile getStudentProfileByID(Integer id){
        return studentProfileRepository.findById(id).orElse(new StudentProfile());
    }
    public void deleteByID(Integer studentProfileId){
        studentProfileRepository.deleteById(studentProfileId);
    }

}
