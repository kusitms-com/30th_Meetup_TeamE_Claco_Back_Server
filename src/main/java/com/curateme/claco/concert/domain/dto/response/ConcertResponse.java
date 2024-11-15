package com.curateme.claco.concert.domain.dto.response;

import com.curateme.claco.concert.domain.entity.Concert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @Schema(description = "공연 지역", example = "서울특별시")
    private String area;

    @Schema(description = "공연 장르", example = "연극")
    private String genrenm;

    @Schema(description = "오픈런 여부", example = "Y")
    private String openrun;

    @Schema(description = "공연 상태", example = "공연중")
    private String prfstate;

    @Schema(description = "공연 캐스팅", example = "정태령, 유다영, 서해든, 민채우, 가은, 이른봄, 정진혁 등")
    private String prfcast;

    @Schema(description = "공연 크루", example = " ")
    private String prfcrew;

    @Schema(description = "공연 시간", example = "1시간 40분")
    private String prfruntime;

    @Schema(description = "공연 관람 나이", example = "만 13세 이상")
    private String prfage;

    @Schema(description = "공연 회사 M", example = " ")
    private String entrpsnm;

    @Schema(description = "공연 회사 P", example = " ")
    private String entrpsnmP;

    @Schema(description = "공연 회사 A", example = " ")
    private String entrpsnmA;

    @Schema(description = "공연 회사 H", example = "(주)레드앤블루(구. 악어컴퍼니)")
    private String entrpsnmH;

    @Schema(description = "공연 회사 S", example = "(주)레드앤블루(구. 악어컴퍼니)")
    private String entrpsnmS;

    @Schema(description = "자리별 가격", example = "전석 40,000원")
    private String pcseguidance;

    @Schema(description = "방문 여부", example = "N")
    private String visit;

    @Schema(description = "어린이 관람 가능 여부", example = "N")
    private String child;

    @Schema(description = "대학로 공연 여부", example = "Y")
    private String daehakro;

    @Schema(description = "페스티벌 여부", example = "N")
    private String festival;

    @Schema(description = "저작권 여부", example = "N")
    private String musicallicense;

    @Schema(description = "뮤지컬 창작 여부", example = "N")
    private String musicalcreate;

    @Schema(description = "업데이트 날짜", example = "2024-10-24 11:01:03")
    private String updatedate;

    @Schema(description = "공연 요일 및 시간대", example = "월요일 ~ 목요일(15:00,16:00,17:15,19:30), 토요일 ~ 일요일(11:50,12:50,14:00,15:00,16:15,17:15,18:30,19:30,20:30), HOL(11:50,12:00,12:50,14:00,14:10,15:00,16:15,16:20,17:15,18:30,19:30,20:30), 금요일(15:00,16:00,17:15,19:00,19:30)")
    private String dtguidance;

    @Schema(description = "공연 소개 URL", example = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF121682_240913_0959491.jpg")
    private String styurl;

    public static ConcertResponse fromEntity(Concert concert){
        return new ConcertResponse(concert.getId(), concert.getMt20id(), concert.getPrfnm(),
            concert.getPrfpdfrom(), concert.getPrfpdto(), concert.getFcltynm(), concert.getPoster(),
            concert.getArea(), concert.getGenrenm(), concert.getOpenrun(), concert.getPrfstate(),
            concert.getPrfcast(), concert.getPrfcrew(), concert.getPrfruntime(),
            concert.getPrfage(), concert.getEntrpsnm(), concert.getEntrpsnmP(),
            concert.getEntrpsnmA(), concert.getEntrpsnmH(), concert.getEntrpsnmS(),
            concert.getPcseguidance(), concert.getVisit(), concert.getChild(), concert.getDaehakro(),
            concert.getFestival(), concert.getMusicallicense(), concert.getMusicalcreate(),
            concert.getUpdatedate(), concert.getDtguidance(), concert.getStyurl());
    }
}
