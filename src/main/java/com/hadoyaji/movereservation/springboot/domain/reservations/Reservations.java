package com.hadoyaji.movereservation.springboot.domain.reservations;

import com.hadoyaji.movereservation.springboot.domain.BaseTimeEntity;
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
    private Long reservationId;

    @Column(nullable = false)
    private LocalDateTime moveDate;

    @Column(nullable = false)
    private String reservationYn;

    @Column
    private String cancelReason;

//    @Column(nullable = false)
//    private Long userId;
//
//    @Column(nullable = false)
//    private Long apartId;


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
