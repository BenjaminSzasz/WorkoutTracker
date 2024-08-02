package com.ironhack.workouttracker.demo;

import com.ironhack.workouttracker.enums.WorkoutType;
import com.ironhack.workouttracker.model.Exercise;
import com.ironhack.workouttracker.model.Role;
import com.ironhack.workouttracker.model.User;
import com.ironhack.workouttracker.model.Workout;
import com.ironhack.workouttracker.repositories.ExerciseRepository;
import com.ironhack.workouttracker.repositories.WorkoutRepository;
import com.ironhack.workouttracker.services.ExerciseService;
import com.ironhack.workouttracker.services.RoleService;
import com.ironhack.workouttracker.services.UserService;
import com.ironhack.workouttracker.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.lang.ProcessHandle.of;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    @Override
    public void run(String... args) throws Exception {
        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_ADMIN"));

        userService.saveUser(new User("John Doe", "john", "1234"));
        userService.saveUser(new User("James Smith", "james", "1234"));
        userService.saveUser(new User("Jane Carry", "jane", "1234"));
        userService.saveUser(new User("Chris Anderson", "chris", "1234"));
        userService.saveUser(new User("Project Maker", "Project101", "1234"));

        roleService.addRoleToUser("john", "ROLE_USER");
        roleService.addRoleToUser("james", "ROLE_ADMIN");
        roleService.addRoleToUser("jane", "ROLE_USER");
        roleService.addRoleToUser("chris", "ROLE_ADMIN");
        roleService.addRoleToUser("chris", "ROLE_USER");
        roleService.addRoleToUser("Project101", "ROLE_ADMIN");





    }
}
