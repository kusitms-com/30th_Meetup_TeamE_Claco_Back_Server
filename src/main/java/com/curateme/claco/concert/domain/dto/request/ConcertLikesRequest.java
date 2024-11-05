package com.curateme.claco.concert.domain.dto.request;

import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.domain.entity.ConcertLike;
import com.curateme.claco.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertLikesRequest {

    private Long memberId;

    private Long concertId;

    public ConcertLike toEntity(Member member, Concert concert) {
        return ConcertLike.builder()
            .member(member)
            .concert(concert)
            .build();
    }
}
