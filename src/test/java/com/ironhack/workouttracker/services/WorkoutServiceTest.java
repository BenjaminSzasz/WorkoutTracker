package com.ironhack.workouttracker.services;

import com.ironhack.workouttracker.model.Workout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class WorkoutServiceTest {
    @Autowired
    private WorkoutService workoutService;

    @Test
    @Rollback
    public void testCreateWorkout() {
        // Create a new workout
        Workout workout = new Workout();

        // Call the createWorkout method
        Workout createdWorkout = workoutService.createWorkout(workout);

        // Assert that the createdWorkout is not null
        assertNotNull(createdWorkout, "Workout should not be null after creation");

        // You can add more assertions based on the expected behavior of the createWorkout method
    }
}

