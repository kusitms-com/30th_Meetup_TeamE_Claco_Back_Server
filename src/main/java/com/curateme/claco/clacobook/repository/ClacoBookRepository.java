package com.curateme.claco.clacobook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curateme.claco.clacobook.domain.entity.ClacoBook;

public interface ClacoBookRepository extends JpaRepository<ClacoBook, Long> {
}
