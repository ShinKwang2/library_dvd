package com.library.jeungsan_dvd.item.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DvdQueryRepository {

    private final EntityManager em;

    public List<DVD> findMostPopular() {
        List<DVD> dvdList = em.createQuery("select d from DVD d order by d.viewCnt desc", DVD.class)
                .setMaxResults(6)
                .getResultList();

        return dvdList;
    }
}
