package com.ironhack.workouttracker.controller;

import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.services.ExerciseService;
import com.ironhack.workouttracker.services.PersonalBestService;
import com.ironhack.workouttracker.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/workouts/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;
    private final PersonalBestService personalBestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void  createExercise(@RequestBody Exercise exercise) {
        exerciseService.createExercise(exercise);
    }




}
