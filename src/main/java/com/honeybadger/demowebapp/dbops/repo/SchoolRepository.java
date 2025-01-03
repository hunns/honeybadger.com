package com.honeybadger.demowebapp.dbops.repo;


import com.honeybadger.demowebapp.dbops.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School,Integer> {
    List<School> findAllBySchoolNameContaining(String filterCondition);
}
