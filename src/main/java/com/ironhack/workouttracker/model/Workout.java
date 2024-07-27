package com.ironhack.workouttracker.model;

import com.ironhack.workouttracker.enums.WorkoutType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;
    private LocalDate workoutDate;
    private float duration;
    @OneToMany
    private List<Exercise> exercises;

    public Workout(User user, WorkoutType workoutType, LocalDate workoutDate, float duration, List<Exercise> exercises) {
        this.user = user;
        this.workoutType = workoutType;
        this.workoutDate = workoutDate;
        this.duration = duration;
        this.exercises = exercises;
    }
}
