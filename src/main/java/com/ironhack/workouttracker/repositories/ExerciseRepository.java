package com.ironhack.workouttracker.repositories;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

 //   @Query("SELECT e FROM Exercise e INNER JOIN e.workout w WHERE w.workoutDate = :date")
    //   List<Exercise> findExercisesByWorkoutDate(@Param("date") LocalDate date);


    //  @Query("SELECT e FROM Exercise e " +
    //         "JOIN e.workout w " +
    //         "JOIN w.user u " +
    //         "JOIN PersonalBest pb ON pb.user.id = u.id AND pb.exerciseName = e.name " +
    //         "WHERE u.id = :userId AND w.workoutDate = :date")
    //   List<Exercise> findExercisesWithPersonalBestByDate(@Param("userId") Long userId, @Param("date") LocalDate date);
    //  @Query("SELECT e FROM Exercise e " +
    //         "JOIN e.workout w " +
    //        "JOIN w.user u " +
    //        "WHERE u.username = :userName AND w.workoutType = :workoutType")
    // List<Exercise> findExerciseByUserNameAndWorkoutType(@Param("userName") String userName, @Param("workoutType") WorkoutType workoutType);

}