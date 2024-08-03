package com.ironhack.workouttracker.repositories;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Find a workout for a specific user by user ID.
     *
     * @param userId The ID of the user to search for.
     * @return An optional containing the workout if found, empty otherwise.
     */
    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId")
    Optional<Workout> findWorkoutByUserId(@Param("userId") Long userId);
    /**
     * Find workouts for a specific user by username.
     *
     * @param userName The username of the user to search for.
     * @return A list of workouts associated with the specified username.
     */

    @Query("SELECT w FROM Workout w JOIN w.user u WHERE u.username = :userName")
    List<Workout> findWorkoutByUserName(@Param("userName") String userName);

    /**
     * Find workouts for a specific user on a given date.
     *
     * @param workoutDate The date of the workout to filter by.
     * @param userId The ID of the user whose workouts are being searched.
     * @return A list of workouts for the user on the specified date.
     */
    @Query("SELECT w FROM Workout w WHERE w.workoutDate = :workoutDate AND w.user.id = :userId")
    List<Workout> findWorkoutsByDateAndUserId(@Param("workoutDate") LocalDate workoutDate, @Param("userId") Long userId);


}
