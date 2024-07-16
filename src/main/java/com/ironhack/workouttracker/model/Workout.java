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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany(mappedBy = "workout")
    private List<CalorieTracker> calorieTrackers;
    @OneToMany(mappedBy = "workout")
    private List<HeartRateMonitor> heartRateMonitors;
}
