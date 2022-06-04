package com.noranekoit.challenge4.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "films")
public class Films {
    @Id
    @Column(name = "film_code", unique = true)
    private String filmCode;

    @Column(name = "film_name")
    private String filmName;

    @NonNull
    @Column(name = "status_tayang")
    private boolean statusTayang;

    @JsonIgnore
    @OneToMany(mappedBy = "films", cascade = CascadeType.ALL)
    private List<Schedules> schedules ;

    public Films(String filmCode, String filmName, @NonNull boolean statusTayang) {
        this.filmCode = filmCode;
        this.filmName = filmName;
        this.statusTayang = statusTayang;
    }


}