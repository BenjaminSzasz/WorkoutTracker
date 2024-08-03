package com.ironhack.workouttracker.controller;

import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.services.ExerciseService;
import com.ironhack.workouttracker.services.PersonalBestService;
import com.ironhack.workouttracker.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ExerciseController.class);
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;
    private final PersonalBestService personalBestService;

    //The getByDate method retrieves a list of exercises based on a specified workout date.
    @GetMapping("/by-date")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <List<Exercise>> getByDate(@RequestParam("workoutDate") final LocalDate date) {
        var exercises = exerciseService.getExerciseByWorkoutDate(date);
        return new ResponseEntity<>(exercises,HttpStatus.OK);
    }

    //The createExercise method is used to create a new exercise.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  createExercise(@RequestBody Exercise exercise) {
        try {
            exerciseService.createExercise(exercise);
            log.info("Exercise created");
            return new ResponseEntity<>(exercise,HttpStatus.CREATED);
        }catch (Exception e) {
            log.error("Error creating exercise", e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // add an exercise to a specific workout identified by the workout ID.
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

