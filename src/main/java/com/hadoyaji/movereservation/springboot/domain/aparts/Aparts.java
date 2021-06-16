package com.hadoyaji.movereservation.springboot.domain.aparts;

import com.hadoyaji.movereservation.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Aparts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long apartId;

    @Column(length = 4, nullable = false)
    private String dong;

    @Column(length = 4, nullable = false)
    private String ho;

    @Builder
    public Aparts(String dong, String ho){
        this.dong = dong;
        this.ho = ho;
    }
}
