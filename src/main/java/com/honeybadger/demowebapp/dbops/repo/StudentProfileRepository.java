package com.honeybadger.demowebapp.dbops.repo;


import com.honeybadger.demowebapp.dbops.entities.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Integer> {
}
