package com.ironhack.workouttracker.controller;

import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getAllWorkoutsByUserId(@PathVariable("id") Long userId) {
        workoutService.getAllWorkoutsByUserId(userId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWorkout(@RequestBody Workout workout) {
        workoutService.createWorkout(workout);
    }

}
