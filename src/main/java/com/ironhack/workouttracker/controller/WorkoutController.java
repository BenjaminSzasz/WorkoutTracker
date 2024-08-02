package com.ironhack.workouttracker.controller;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.services.WorkoutService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workouts")
public class WorkoutController {
    private static final Logger log = LoggerFactory.getLogger(WorkoutController.class);
    private final WorkoutService workoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWorkout(@RequestBody Workout workout) {
        try {
            workoutService.createWorkout(workout);
            log.info("Created workout: {}", workout);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            log.error("Workout not created: {}",e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> getWorkoutByUserId(@PathVariable("userId") Long userId) {
        try {
            workoutService.getWorkoutByUserID(userId);
            return new ResponseEntity<>("Workout fetched successfully", HttpStatus.FOUND);
        } catch (Exception e) {
            log.info("Error while fetching workout {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteWorkoutByUserId(@PathVariable("userId") Long userId) {
        try {
            workoutService.deleteWorkout(userId);
            return new ResponseEntity<>("Workout deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            log.info("Error while deleting workout {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/by-date-and-user")
    public ResponseEntity<List<Workout>> getWorkoutsByDateAndUserId(@RequestParam("date") String date, @RequestParam("userId") Long userId) {
        LocalDate workoutDate = LocalDate.parse(date);
        List<Workout> workouts = workoutService.getWorkoutsByDateAndUserId(workoutDate, userId);
        if (workouts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }


}
