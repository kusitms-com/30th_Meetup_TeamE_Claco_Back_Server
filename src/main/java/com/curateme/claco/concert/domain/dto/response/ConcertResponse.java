package com.curateme.claco.concert.domain.dto.response;

import com.curateme.claco.concert.domain.entity.Concert;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "concertId")
    private String mt20id;

    @Column(name = "concertName")
    private String prfnm;

    @Column(name = "startDate")
    private String prfpdfrom;

    @Column(name = "endDate")
    private String prfpdto;

    @Column(name = "facilityName")
    private String fcltynm;

    @Column(name = "poster")
    private String poster;

    @Column(name = "area")
    private String area;

    @Column(name = "genre")
    private String genrenm;

    @Column(name = "openrun")
    private String openrun;

    @Column(name = "status")
    private String prfstate;

    @Column(name = "cast")
    private String prfcast;

    @Column(name = "crew")
    private String prfcrew;

    @Column(name = "runtime")
    private String prfruntime;

    @Column(name = "age")
    private String prfage;

    @Column(name = "companyName")
    private String entrpsnm;

    @Column(name = "companyNameP")
    private String entrpsnmP;

    @Column(name = "companyNameA")
    private String entrpsnmA;

    @Column(name = "companyNameH")
    private String entrpsnmH;

    @Column(name = "companyNameS")
    private String entrpsnmS;

    @Column(name = "seatGuidance")
    private String pcseguidance;

    @Column(name = "visit")
    private String visit;

    @Column(name = "child")
    private String child;

    @Column(name = "daehakro")
    private String daehakro;

    @Column(name = "festival")
    private String festival;

    @Column(name = "musicalLicense")
    private String musicallicense;

    @Column(name = "musicalCreate")
    private String musicalcreate;

    @Column(name = "updateDate")
    private String updatedate;

    @Column(name = "scheduleGuidance", length = 1000)
    private String dtguidance;

    @Column(name = "introduction")
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
