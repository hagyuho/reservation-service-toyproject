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
public class User extends BaseTimeEntity  {
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name="APART_ID")
    private Aparts aparts;

    @Builder
    public User(String hpNumber, Role role, Aparts aparts, String email, String name, String picture){
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.hpNumber = hpNumber;
        this.role = role;
        this.aparts = aparts;
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
