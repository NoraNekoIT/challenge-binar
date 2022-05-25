package com.noranekoit.challenge4.models.entity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class SeatsIdentity implements Serializable {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;

    @NonNull
    @Column(name = "studio_name")
    private String studioName;

    @NonNull
    @Column(name = "no")
    private String no;

}