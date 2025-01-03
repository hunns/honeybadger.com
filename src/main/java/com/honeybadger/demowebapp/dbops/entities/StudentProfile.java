package com.honeybadger.demowebapp.dbops.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "School_Profile_Seq")

    @Column(name = "Student_Profile_id")
    private Integer id;

    private String bio;

    /*
    (name="student_id")
    this will create the student_id a column name and set the datatype
    matching to Student Id column

    private Student studentLink
    the studentLink should exactly match with the mappedBy attribute value
     in Student Class of studentProfile field.
     */
    @OneToOne
    @JoinColumn
            (name = "student_id")
    private Student studentLink;


}
