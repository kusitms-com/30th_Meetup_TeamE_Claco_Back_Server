package com.curateme.claco.concert.repository;

import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.domain.entity.ConcertLike;
import com.curateme.claco.member.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcertLikeRepository extends JpaRepository<ConcertLike,Long> {

    @Query("SELECT cl FROM ConcertLike cl WHERE cl.member = :member AND cl.concert = :concert")
    Optional<ConcertLike> findByMemberAndConcert(@Param("member") Member member, @Param("concert") Concert concert);
}
