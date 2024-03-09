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
@Table(name = "Classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classID;
    @Column(name = "className", nullable = false, length = 100)
    private String className;

    @ManyToOne
    @JoinColumn(name = "teacherID")
    private Teacher teacher;

    @OneToMany(mappedBy = "aClass")
    @JsonIgnore
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "aClass")
    @JsonIgnore
    private List<Student> studentList;

}