package com.ironhack.workouttracker.repositories;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId")
    Optional<Workout> findWorkoutByUserId(@Param("userId") Long userId);
    @Query("SELECT w FROM Workout w JOIN w.user u WHERE u.username = :userName")
    List<Workout> findWorkoutByUserName(@Param("userName") String userName);


}
