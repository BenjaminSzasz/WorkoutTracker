package com.ironhack.workouttracker.repositories;

import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.PersonalBest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


import java.util.List;

public interface PersonalBestRepository extends JpaRepository<PersonalBest, Long> {
    List<PersonalBest> findByExercise(Exercise exercise);
    @Query("SELECT pb FROM PersonalBest pb " +
            "JOIN pb.user u " +
            "WHERE u.id = :userId AND pb.exerciseName = :exerciseName")
    Optional<PersonalBest> findByUserIdAndExerciseName(@Param("userId") Long userId, @Param("exerciseName") String exerciseName);


}
