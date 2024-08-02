package com.ironhack.workouttracker.services;
import com.ironhack.workouttracker.model.PersonalBest;
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
    private final PersonalBestRepository personalBestRepository;
    private final PersonalBestService personalBestService;

    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    public void addExerciseToWorkout(Long workoutId, Exercise exercise) {
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (!workoutOptional.isPresent()) {
            throw new DataIntegrityViolationException("Workout with ID " + workoutId + " does not exist.");
        }

        Workout workout = workoutOptional.get();
        exercise.setWorkout(workout);
        exerciseRepository.save(exercise);

        updatePersonalBest(workout.getUser().getId(), exercise);
    }

    private void updatePersonalBest(Long userId, Exercise newExercise) {
        Optional<PersonalBest> personalBestOptional = personalBestRepository.findByUserIdAndExerciseName(userId, newExercise.getName());

        if (!personalBestOptional.isPresent()) {
            // No personal best exists, create a new one
            PersonalBest personalBest = new PersonalBest(newExercise.getRepetitions(), newExercise.getWeight(), newExercise.getName(), newExercise);
            personalBestRepository.save(personalBest);
        } else {
            // Update the existing personal best if the new exercise is an improvement
            PersonalBest existingPersonalBest = personalBestOptional.get();
            boolean updated = false;
            if (newExercise.getRepetitions() > existingPersonalBest.getBestReps()) {
                existingPersonalBest.setBestReps(newExercise.getRepetitions());
                updated = true;
            }
            if (newExercise.getWeight() > existingPersonalBest.getBestWeight()) {
                existingPersonalBest.setBestWeight(newExercise.getWeight());
                updated = true;
            }
            if (updated) {
                personalBestRepository.save(existingPersonalBest);
            }
        }
    }

    public Exercise updateExercise(Long userId, Long id, Exercise updatedExercise) {
        if (exerciseRepository.existsById(id)) {
            Exercise existingExercise = exerciseRepository.findById(id).orElse(null);
            if (existingExercise != null && existingExercise.getWorkout().getUser().getId().equals(userId)) {
                updatedExercise.setId(id);
                Exercise savedExercise = exerciseRepository.save(updatedExercise);
                personalBestService.updatePersonalBest(userId, savedExercise);
                return savedExercise;
            }
        }
        return null;


    }
}
