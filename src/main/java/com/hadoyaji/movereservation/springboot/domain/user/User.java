package com.hadoyaji.movereservation.springboot.domain.user;

import com.hadoyaji.movereservation.springboot.domain.BaseTimeEntity;
import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hpNumber;

    @ManyToOne
    @JoinColumn(name="APART_ID")
    private Aparts aparts;

    @Builder
    public User(String hpNumber, Aparts aparts){
        this.hpNumber = hpNumber;
        this.aparts = aparts;
    }

    public User update( String hpNumber){
        this.hpNumber = hpNumber;
        return this;
    }
}
