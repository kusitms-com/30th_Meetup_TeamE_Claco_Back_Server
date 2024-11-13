package com.curateme.claco.recommendation.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendationConcertsResponseV1 {

    @Schema(name = "공연 아이디")
    private Long id;

    @Schema(name = "공연 제목")
    private String prfnm;

    @Schema(name = "공연 포스터 URL")
    private String poster;

    @Schema(name = "공연 장르")
    private String genrenm;

    @Schema(name = "공연 좋아요 여부")
    private boolean isLiked;

    public RecommendationConcertsResponseV1(Long id, String prfnm, String poster, String genrenm, boolean isLiked) {
        this.id = id;
        this.prfnm = prfnm;
        this.poster = poster;
        this.genrenm = genrenm;
        this.isLiked = isLiked;
    }
}
