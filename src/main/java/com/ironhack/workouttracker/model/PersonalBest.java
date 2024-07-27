package com.ironhack.workouttracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PersonalBest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int bestReps;
    private float bestWeight;
    private String exerciseName;
    @ManyToOne
    @JoinColumn(name = "exercise_id",nullable = false)
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PersonalBest(int bestReps, float bestWeight, Exercise exercise) {
        this.bestReps = bestReps;
        this.bestWeight = bestWeight;
        this.exercise = exercise;
    }
}
