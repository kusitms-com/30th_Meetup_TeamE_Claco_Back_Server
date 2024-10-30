package com.curateme.claco.concert.repository;

import com.curateme.claco.concert.domain.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert,Long> {

}
