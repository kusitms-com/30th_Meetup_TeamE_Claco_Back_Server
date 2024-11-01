package com.curateme.claco.concert.repository;

import com.curateme.claco.concert.domain.entity.ConcertCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertCategoryRepository extends JpaRepository<ConcertCategory,Long> {

}
