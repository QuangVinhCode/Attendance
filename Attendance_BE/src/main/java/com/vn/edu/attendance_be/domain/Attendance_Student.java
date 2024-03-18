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
@Table(name = "Attendance_Student")
public class Attendance_Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attendanceId")
    private Attendance attendance;

    @Column(name = "status", nullable = false, length = 100)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student student;



    // Getters and setters
}