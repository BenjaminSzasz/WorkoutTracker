package com.ironhack.workouttracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@NoArgsConstructor
@Entity
public class CalorieTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int calorieBurned;
    private LocalTime endOfWorkoutTime;
    @ManyToOne
    @JoinColumn(name = "workout_id",nullable = false)
    private Workout workout;

}
