package com.curateme.claco.concert.domain.dto.response;

import com.curateme.claco.concert.domain.entity.Concert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertResponse {

    @NotNull
    private Long id;

    @NotNull
    @Schema(description = "공연 아이디", example = "PF121682")
    private String mt20id;

    @NotNull
    @Schema(description = "공연 제목", example = "옥탑방 고양이 [대학로]")
    private String prfnm;

    @Schema(description = "공연 시작날짜", example = "2010-04-06")
    private LocalDate prfpdfrom;

    @Schema(description = "공연 종료날짜", example = "2024-11-30")
    private LocalDate prfpdto;

    @Schema(description = "공연 장소", example = "틴틴홀")
    private String fcltynm;

    @Schema(description = "공연 포스터 URL", example = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF121682_210322_143051.gif")
    private String poster;

    @Schema(description = "공연 장르", example = "연극")
    private String genrenm;

    @Schema(description = "공연 상태", example = "공연중")
    private String prfstate;

    @Schema(description = "공연 성격 리스트")
    private List<ConcertCategoryResponse> categories;

    public static ConcertResponse fromEntity(Concert concert, List<ConcertCategoryResponse> categories) {
        return ConcertResponse.builder()
            .id(concert.getId())
            .mt20id(concert.getMt20id())
            .prfnm(concert.getPrfnm())
            .prfpdfrom(concert.getPrfpdfrom())
            .prfpdto(concert.getPrfpdto())
            .fcltynm(concert.getFcltynm())
            .poster(concert.getPoster())
            .genrenm(concert.getGenrenm())
            .prfstate(concert.getPrfstate())
            .categories(categories)
            .build();
    }
}