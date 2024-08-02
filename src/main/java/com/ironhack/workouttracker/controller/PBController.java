package com.ironhack.workouttracker.controller;

import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.services.ExerciseService;
import com.ironhack.workouttracker.services.PersonalBestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personal-bests")
public class PBController {
    private final PersonalBestService personalBestService;
    private final ExerciseService exerciseService;

    @PostMapping("/update")
    public ResponseEntity<String> updatePersonalBest(@RequestParam Long userId, @RequestBody Exercise newExercise) {
        try {
            personalBestService.updatePersonalBest(userId, newExercise);
            return new ResponseEntity<>("Personal best updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update personal best", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExercise(
            @RequestParam Long userId,
            @PathVariable Long id,
            @RequestBody Exercise updatedExercise) {

        try {
            Exercise savedExercise = exerciseService.updateExercise(userId, id, updatedExercise);
            if (savedExercise != null) {
                return new ResponseEntity<>(savedExercise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Exercise not found or doesn't belong to the user", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update exercise", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
