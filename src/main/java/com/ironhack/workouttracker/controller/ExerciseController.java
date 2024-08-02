package com.ironhack.workouttracker.controller;

import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.services.ExerciseService;
import com.ironhack.workouttracker.services.PersonalBestService;
import com.ironhack.workouttracker.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;
    private final PersonalBestService personalBestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void  createExercise(@RequestBody Exercise exercise) {
        exerciseService.createExercise(exercise);
    }
    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<String> addExerciseToWorkout(
            @PathVariable Long workoutId,
            @RequestBody Exercise exercise) {

        try {
            exerciseService.addExerciseToWorkout(workoutId, exercise);
            return new ResponseEntity<>("Exercise added to workout successfully!", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add exercise to workout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
