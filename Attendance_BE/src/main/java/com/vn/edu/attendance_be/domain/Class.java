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
    private Long id;

    @Column(name = "className", nullable = false, length = 100)
    private String className;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "aclass", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentJoinClass> studentList;


}