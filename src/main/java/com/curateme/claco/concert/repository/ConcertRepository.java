package com.curateme.claco.concert.repository;

import com.curateme.claco.concert.domain.entity.Concert;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcertRepository extends JpaRepository<Concert,Long> {
    Page<Concert> findByIdIn(List<Long> ids, Pageable pageable);

    @Query("SELECT DISTINCT c.id FROM Concert c JOIN c.categories cat WHERE c.area = :area AND c.prfpdto BETWEEN :startDate AND :endDate AND EXISTS (SELECT 1 FROM ConcertCategory cc WHERE cc.concert = c AND cc.category.category IN :categories)")
    List<Long> findConcertIdsByFilters(@Param("area") String area, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("categories") List<String> categories);

    @Query("SELECT c.id FROM Concert c " + "WHERE (c.prfnm LIKE %:query% " + "OR c.prfcast LIKE %:query% " + "OR c.fcltynm LIKE %:query%)" + "AND c.prfpdto >= CURRENT_DATE")
    List<Long> findConcertIdsBySearchQuery(@Param("query") String query);

    @Query("SELECT c FROM Concert c WHERE c.id = :concertId")
    Concert findConcertById(@Param("concertId") Long concertId);

    @Query("SELECT c.id FROM Concert c WHERE c.genrenm = :genre AND c.prfpdto <= CURRENT_DATE")
    List<Long> findConcertIdsByGenre(@Param("genre") String genre);

}
