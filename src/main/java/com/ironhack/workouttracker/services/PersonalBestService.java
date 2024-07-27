package com.ironhack.workouttracker.services;
import java.util.stream.Stream;
import java.util.stream.Collectors;


import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.PersonalBest;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.repositories.ExerciseRepository;
import com.ironhack.workouttracker.repositories.PersonalBestRepository;
import com.ironhack.workouttracker.repositories.UserRepository;
import com.ironhack.workouttracker.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class PersonalBestService {
    private final PersonalBestRepository personalBestRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public Exercise createExercise(Long userId, Exercise exercise) {
        Workout workout = workoutRepository.findById(exercise.getWorkout().getId()).orElse(null);
        if (workout != null && workout.getUser().getId().equals(userId)) {
            Exercise savedExercise = exerciseRepository.save(exercise);
            updatePersonalBest(userId, savedExercise);
            return savedExercise;
        }
        return null;
    }


    public Exercise updateExercise(Long userId, Long id, Exercise updatedExercise) {
        if (exerciseRepository.existsById(id)) {
            Exercise existingExercise = exerciseRepository.findById(id).orElse(null);
            if (existingExercise != null && existingExercise.getWorkout().getUser().getId().equals(userId)) {
                updatedExercise.setId(id);
                Exercise savedExercise = exerciseRepository.save(updatedExercise);
                updatePersonalBest(userId, savedExercise);
                return savedExercise;
            }
        }
        return null;
    }
     void updatePersonalBest(Long userId, Exercise exercise) {
        String exerciseName = exercise.getName();
        Optional<PersonalBest> personalBestOpt = personalBestRepository.findByUserIdAndExerciseName(userId, exerciseName);

        int bestReps = exercise.getRepetitions();
        float bestWeight = exercise.getWeight();

        if (personalBestOpt.isPresent()) {
            PersonalBest personalBest = personalBestOpt.get();
            boolean updated = false;
            if (bestReps > personalBest.getBestReps()) {
                personalBest.setBestReps(bestReps);
                updated = true;
            }
            if (bestWeight > personalBest.getBestWeight()) {
                personalBest.setBestWeight(bestWeight);
                updated = true;
            }
            if (updated) {
                personalBestRepository.save(personalBest);
            }
        } else {
            PersonalBest newPersonalBest = new PersonalBest();
            newPersonalBest.setUser(userRepository.findById(userId).get());
            newPersonalBest.setExerciseName(exerciseName);
            newPersonalBest.setBestReps(bestReps);
            newPersonalBest.setBestWeight(bestWeight);
            personalBestRepository.save(newPersonalBest);
        }
    }
}


