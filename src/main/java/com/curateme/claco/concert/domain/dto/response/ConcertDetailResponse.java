package com.curateme.claco.concert.domain.dto.response;

import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.review.domain.entity.TicketReview;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
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
public class ConcertDetailResponse {
    @NotNull
    private Long id;

    @NotNull
    @Schema(name = "공연 아이디")
    private String mt20id;

    @NotNull
    @Schema(name = "공연 제목")
    private String prfnm;

    @Schema(name = "공연 시작날짜")
    private LocalDate prfpdfrom;

    @Schema(name = "공연 종료날짜")
    private LocalDate prfpdto;

    @Schema(name = "공연 장소")
    private String fcltynm;

    @Schema(name = "공연 포스터 URL")
    private String poster;

    @Schema(name = "공연 지역")
    private String area;

    @Schema(name = "공연 장르")
    private String genrenm;

    @Schema(name = "오픈런 여부")
    private String openrun;

    @Schema(name = "공연 상태")
    private String prfstate;

    @Schema(name = "공연 캐스팅")
    private String prfcast;

    @Schema(name = "공연 크루")
    private String prfcrew;

    @Schema(name = "공연 시간")
    private String prfruntime;

    @Schema(name = "공연 관람 나이")
    private String prfage;

    @Schema(name = "공연 회사 M")
    private String entrpsnm;

    @Schema(name = "공연 회사 P")
    private String entrpsnmP;

    @Schema(name = "공연 회사 A")
    private String entrpsnmA;

    @Schema(name = "공연 회사 H")
    private String entrpsnmH;

    @Schema(name = "공연 회사 S")
    private String entrpsnmS;

    @Schema(name = "자리별 가격 ")
    private String pcseguidance;

    @Schema(name = "visit")
    private String visit;

    @Schema(name = "어린이 관람 가능")
    private String child;

    @Schema(name = "대학로 공연 여부")
    private String daehakro;

    @Schema(name = "페스티벌 여부")
    private String festival;

    @Schema(name = "저작권 여부")
    private String musicallicense;

    @Schema(name = "musical_create")
    private String musicalcreate;

    @Schema(name = "update_date")
    private String updatedate;

    @Schema(name = "공연 요일 및 시간대")
    private String dtguidance;

    @Schema(name = "공연 소개 URL")
    private String styurl;

    @Schema(name = "티켓 리뷰 리스트")
    private List<TicketReview> ticketReview;

    @Schema(name = "공연 성격 리스트")
    private List<ConcertCategoryResponse> categories;

    public static ConcertDetailResponse fromEntity(Concert concert, List<ConcertCategoryResponse> categories){
        return new ConcertDetailResponse(concert.getId(), concert.getMt20id(), concert.getPrfnm(),
            concert.getPrfpdfrom(), concert.getPrfpdto(), concert.getFcltynm(), concert.getPoster(),
            concert.getArea(), concert.getGenrenm(), concert.getOpenrun(), concert.getPrfstate(),
            concert.getPrfcast(), concert.getPrfcrew(), concert.getPrfruntime(),
            concert.getPrfage(), concert.getEntrpsnm(), concert.getEntrpsnmP(),
            concert.getEntrpsnmA(), concert.getEntrpsnmH(), concert.getEntrpsnmS(),
            concert.getPcseguidance(), concert.getVisit(), concert.getChild(), concert.getDaehakro(),
            concert.getFestival(), concert.getMusicallicense(), concert.getMusicalcreate(),
            concert.getUpdatedate(), concert.getDtguidance(), concert.getStyurl(), concert.getTicketReview(),
            categories);
    }

    public void setCategories(List<ConcertCategoryResponse> categories) {
        this.categories = categories;
    }

}
