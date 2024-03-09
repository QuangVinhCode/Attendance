package com.vn.edu.attendance_be.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceID;

    @ManyToOne
    @JoinColumn(name = "classID")
    private Class aClass;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student student;



    // Getters and setters
}