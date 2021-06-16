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

//    @Column(name = "USER_ID", nullable = false)
//    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

//    @Column(name = "APART_ID",nullable = false)
//    private Long apartId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="APART_ID")
    private Aparts aparts;



    @Builder
    public Reservations(String reservationYn, LocalDateTime moveDate, String cancelReason){
        this.reservationYn = reservationYn;
        this.moveDate = moveDate;
        this.cancelReason = cancelReason;
    }


    public Reservations cancelReservations(String cancelReason){
        this.reservationYn = "N";
        this.cancelReason = cancelReason;
        return this;
    }
}
