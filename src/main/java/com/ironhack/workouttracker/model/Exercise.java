package com.ironhack.workouttracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int sets;
    private int repetitions;
    private float weight;
    @OneToMany(mappedBy = "exercise",cascade = {CascadeType.PERSIST,CascadeType.REFRESH},orphanRemoval = true)
    private List<PersonalBest> personalBests;

    public Exercise(String name, int sets, int repetitions, float weight) {
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
        this.weight = weight;
    }
}
