package com.ironhack.workouttracker.repositories;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId")
    List<Workout> findAllWorkoutsByUserId(@Param("userId") Long userId);
    @Query("SELECT w FROM Workout w JOIN w.user u WHERE u.id = :userId AND w.workoutType = :workoutType")
    List<Workout> findByUserWorkoutType(@Param("userId") Long userId, @Param("workoutType") WorkoutType workoutType);
    @Query("SELECT w FROM Workout w JOIN w.user u WHERE u.username = :userName AND w.workoutType = :workoutType")
    List<Workout> findByUserNameAndAndWorkoutType(@Param("userName") String userName , @Param("workoutType") WorkoutType workoutType);
    @Query("SELECT w FROM Workout w JOIN w.user u where u.username = :userName AND w.workoutDate = :workoutDate")
    List<Workout> findByUserNameAndByWorkoutDate(@Param("userName") String userName, @Param("workoutDate") Date workoutDate);


}
