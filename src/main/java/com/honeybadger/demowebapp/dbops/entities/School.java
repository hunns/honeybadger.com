package com.honeybadger.demowebapp.dbops.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "School_Seq")
    @Column(name = "School_id")
    private Integer id;

    @Column(name = "School_name")
    private String schoolName;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    List<Student> studentList;
}
