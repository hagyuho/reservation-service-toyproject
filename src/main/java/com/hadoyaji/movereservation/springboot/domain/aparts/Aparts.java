package com.hadoyaji.movereservation.springboot.domain.aparts;

import com.hadoyaji.movereservation.springboot.domain.BaseTimeEntity;
import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Aparts extends BaseTimeEntity {

    @Id
    private long id;

    @Column(length = 4, nullable = false)
    private String dong;

    @Column(length = 4, nullable = false)
    private String ho;


    @OneToMany(mappedBy = "aparts")
    private List<Reservations> reservations = new ArrayList<>();



    @Builder
    public Aparts(String dong, String ho){
        this.id = Long.parseLong(dong.concat(ho));
        this.dong = dong;
        this.ho = ho;
    }
}
