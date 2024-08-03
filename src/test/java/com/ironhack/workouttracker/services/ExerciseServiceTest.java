package com.ironhack.workouttracker.services;

import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.repositories.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@SpringBootTest
@Transactional
class ExerciseServiceTest {

    @Autowired
    private ExerciseService exerciseService;

    @Test
    @Rollback
    public void testCreateExercise() {
        // Create a sample exercise
        Exercise exercise = new Exercise();

        // Call the createExercise method
        Exercise createExercise = exerciseService.createExercise(exercise);

        // Verify that the save method of exerciseRepository is called once with the exercise object
        assertNotNull(createExercise, "Exercise created");

    }
}