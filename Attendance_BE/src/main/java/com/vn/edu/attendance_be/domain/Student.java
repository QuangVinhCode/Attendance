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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userID")
    private User user;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "studentCode", nullable = false, length = 100)
    private String studentCode;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "classID")
    private Class aClass;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Attendance> attendanceList;
}
