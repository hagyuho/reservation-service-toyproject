package com.hadoyaji.movereservation.springboot.domain.user;

import com.hadoyaji.movereservation.springboot.domain.BaseTimeEntity;
import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Column(nullable = false)
    private String hpNumber;

    @OneToMany(mappedBy = "user")
    private List<Reservations> reservations = new ArrayList<>();

    @Builder
    public User(String name, String email, String picture, String hpNumber){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.hpNumber = hpNumber;
    }

    public User update(String name, String picture, String hpNumber){
        this.name = name;
        this.picture = picture;
        this.hpNumber = hpNumber;
        return this;
    }
}
