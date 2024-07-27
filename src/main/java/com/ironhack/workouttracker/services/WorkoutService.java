package com.ironhack.workouttracker.services;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    public void  getAllWorkoutsByUserId(Long userId) {
        log.info("getAllWorkoutsByUserId start");
        workoutRepository.findAllWorkoutsByUserId(userId);
    }
    public void getWorkoutByUserNameAndWorkoutType(String userName, WorkoutType workoutType) {
        workoutRepository.findByUserNameAndAndWorkoutType(userName, workoutType);
    }
    public void getWorkoutByUserNameAndWorkoutDate(String userName, Date workoutDate) {
        workoutRepository.findByUserNameAndByWorkoutDate(userName, workoutDate);
    }
    public void getWorkoutByUserIdWorkoutType(Long userId, WorkoutType workoutType) {
        workoutRepository.findByUserWorkoutType(userId, workoutType);
    }
    public void createWorkout(Workout workout) {
        log.info("Creating workout {}", workout);
        workoutRepository.save(workout);
    }
}
