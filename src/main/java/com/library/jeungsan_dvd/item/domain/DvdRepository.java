package com.library.jeungsan_dvd.item.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DvdRepository extends JpaRepository<DVD, Long> {

    List<DVD> findAll(Sort sort);
}
