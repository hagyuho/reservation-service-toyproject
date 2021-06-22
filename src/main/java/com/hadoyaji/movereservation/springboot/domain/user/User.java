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
public class User extends BaseTimeEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Reservations> reservations = new ArrayList<>();

    @Builder
    public User( Role role,  String email, String name, String picture){
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.role = role;
    }

    public User update(String name, String picture){

        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
