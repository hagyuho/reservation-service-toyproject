package com.hadoyaji.movereservation.springboot.domain.reservations;

import com.hadoyaji.movereservation.springboot.domain.BaseTimeEntity;
import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Reservations extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime moveDate;

    @Column(nullable = false)
    private String reservationYn;

    @Column
    private String cancelReason;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="APART_ID")
    private Aparts aparts;



    @Builder
    public Reservations(String reservationYn, LocalDateTime moveDate, String cancelReason, User user, Aparts aparts){
        this.reservationYn = reservationYn;
        this.moveDate = moveDate;
        this.cancelReason = cancelReason;
        this.user = user;
        this.aparts = aparts;
    }


    public Reservations cancelReservations(String cancelReason){
        this.reservationYn = "N";
        this.cancelReason = cancelReason;
        return this;
    }
}
