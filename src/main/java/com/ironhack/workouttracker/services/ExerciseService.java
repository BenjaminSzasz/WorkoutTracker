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

    /**
     * Save a new exercise to the database.
     *
     * @param exercise The exercise object to be saved.
     */
    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    /**
     * Retrieve a list of exercises for a specific workout date.
     *
     * @param workoutDate The date of the workout to filter exercises by.
     * @return A list of exercises associated with the specified workout date.
     */

    public List<Exercise> getExerciseByWorkoutDate(LocalDate workoutDate) {
        return exerciseRepository.findByWorkout_WorkoutDate(workoutDate);
    }


    /**
     * Add an exercise to a workout identified by the provided workout ID.
     *
     * @param workoutId The ID of the workout to add the exercise to.
     * @param exercise The exercise to be added to the workout.
     */

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

    /**
     * Update the personal best records based on a new exercise added.
     *
     * @param userId The ID of the user to update personal best records for.
     * @param newExercise The new exercise added to potentially update personal best.
     */

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

    /**
     * Update an existing exercise for a specific user.
     *
     * @param userId The ID of the user performing the update.
     * @param id The ID of the exercise to update.
     * @param updatedExercise The updated exercise object.
     * @return The updated exercise if successful, null otherwise.
     */

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
