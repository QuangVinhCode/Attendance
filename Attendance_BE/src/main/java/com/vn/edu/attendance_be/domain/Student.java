package com.vn.edu.attendance_be.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Students")
public class Student {

    @Id
    @Column(name = "studentID", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToOne(mappedBy = "student")
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentJoinClass> studentList;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Attendance_Student> attendanceList;
}
