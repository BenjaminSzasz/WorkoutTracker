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
    @Transactional
    public  Workout createWorkout(Workout workout) {
        log.info("Creating workout {}", workout);
       return workoutRepository.save(workout);
    }
    @Transactional
    public Workout getWorkoutByUserID(Long userId) {
        Optional<Workout> workoutOptional = workoutRepository.findWorkoutByUserId(userId);
        if (workoutOptional.isEmpty()) {
            throw new DataIntegrityViolationException("Workout not found");
        }
        return workoutOptional.get();
    }
    @Transactional
    public void deleteWorkout(Long id) {
        Workout workout = workoutRepository.findWorkoutByUserId(id).orElseThrow(() -> new EntityNotFoundException("Workout with ID " + id + " does not exist."));
        workoutRepository.delete(workout);
    }
    @Transactional
    public List<Workout> getWorkoutsByDateAndUserId(LocalDate workoutDate, Long userId) {
        log.info("getWorkoutsByDateAndUserId {}", workoutDate);
        return workoutRepository.findWorkoutsByDateAndUserId(workoutDate, userId);
    }



}
