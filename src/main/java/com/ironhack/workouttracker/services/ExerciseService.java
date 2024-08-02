package com.ironhack.workouttracker.services;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.repositories.ExerciseRepository;
import com.ironhack.workouttracker.repositories.PersonalBestRepository;
import com.ironhack.workouttracker.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }
    public void addExerciseToWorkout(Long workoutId, Exercise exercise) {
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (!workoutOptional.isPresent()) {
            throw new DataIntegrityViolationException("Workout with ID " + workoutId + " does not exist.");
        }
        exercise.setWorkout(workoutOptional.get());
        exerciseRepository.save(exercise);
    }






}
