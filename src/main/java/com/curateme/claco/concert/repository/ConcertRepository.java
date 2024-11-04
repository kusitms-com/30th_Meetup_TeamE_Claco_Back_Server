package com.curateme.claco.concert.repository;

import com.curateme.claco.concert.domain.entity.Concert;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcertRepository extends JpaRepository<Concert,Long> {
    Page<Concert> findByIdIn(List<Long> ids, Pageable pageable);

}
