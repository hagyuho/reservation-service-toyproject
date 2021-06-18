package com.hadoyaji.movereservation.springboot.domain.aparts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartsRepository extends JpaRepository<Aparts,Long> {
    public Aparts findByDongAndHo(String dong, String ho);
}
