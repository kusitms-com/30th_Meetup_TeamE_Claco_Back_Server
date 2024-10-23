package com.curateme.claco.clacobook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curateme.claco.clacobook.domain.entity.ClacoBook;

/**
 * @author      : 이 건
 * @date        : 2024.10.24
 * @author devkeon(devkeon123@gmail.com)
 * ===========================================================
 * DATE               AUTHOR        NOTE
 * -----------------------------------------------------------
 * 2024.10.24   	   이 건        최초 생성
 */
public interface ClacoBookRepository extends JpaRepository<ClacoBook, Long> {
}
