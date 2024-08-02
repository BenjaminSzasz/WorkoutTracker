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
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PersonalBest(int bestReps, float bestWeight, String exerciseName, Exercise exercise) {
        this.bestReps = bestReps;
        this.bestWeight = bestWeight;
        this.exerciseName = exerciseName;
        this.exercise = exercise;
    }
}