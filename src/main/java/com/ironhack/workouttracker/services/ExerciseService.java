package com.ironhack.workouttracker.services;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.repositories.ExerciseRepository;
import com.ironhack.workouttracker.repositories.PersonalBestRepository;
import com.ironhack.workouttracker.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }






}
