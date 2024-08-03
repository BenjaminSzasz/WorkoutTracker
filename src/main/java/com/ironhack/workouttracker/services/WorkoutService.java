package com.ironhack.workouttracker.services;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.repositories.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;


    public void getWorkoutByUserName(String userName) {
        workoutRepository.findWorkoutByUserName(userName);
    }
    /**
     * Create and save a new workout record in the database.
     *
     * @param workout The workout object to be created and saved.
     * @return The created workout object.
     */

    @Transactional
    public  Workout createWorkout(Workout workout) {
        log.info("Creating workout {}", workout);
       return workoutRepository.save(workout);
    }
    /**
     * Retrieve a workout for a specific user by the user's ID.
     *
     * @param userId The ID of the user to get the workout for.
     * @return The workout associated with the specified user.
     * @throws DataIntegrityViolationException if the workout is not found.
     */

    @Transactional
    public Workout getWorkoutByUserID(Long userId) {
        Optional<Workout> workoutOptional = workoutRepository.findWorkoutByUserId(userId);
        if (workoutOptional.isEmpty()) {
            throw new DataIntegrityViolationException("Workout not found");
        }
        return workoutOptional.get();
    }
    /**
     * Delete a workout based on the provided ID.
     *
     * @param id The ID of the workout to be deleted.
     * @throws EntityNotFoundException if the workout with the specified ID does not exist.
     */

    @Transactional
    public void deleteWorkout(Long id) {
        Workout workout = workoutRepository.findWorkoutByUserId(id).orElseThrow(() -> new EntityNotFoundException("Workout with ID " + id + " does not exist."));
        workoutRepository.delete(workout);
    }
    /**
     * Retrieve a list of workouts for a specific user on a given date.
     *
     * @param workoutDate The date of the workouts to filter by.
     * @param userId The ID of the user whose workouts are being retrieved.
     * @return A list of workouts for the user on the specified date.
     */

    @Transactional
    public List<Workout> getWorkoutsByDateAndUserId(LocalDate workoutDate, Long userId) {
        log.info("getWorkoutsByDateAndUserId {}", workoutDate);
        return workoutRepository.findWorkoutsByDateAndUserId(workoutDate, userId);
    }



}
