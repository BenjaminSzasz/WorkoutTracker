package com.ironhack.workouttracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Entity
@Data
@NoArgsConstructor
public class HeartRateMonitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int heartRate;
    private LocalTime timestamp;
    @ManyToOne
    @JoinColumn(name = "workout_id",nullable = false)
    private Workout workout;
}
