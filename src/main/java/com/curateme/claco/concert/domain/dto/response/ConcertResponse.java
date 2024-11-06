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
    @Schema(name = "concert_id")
    private String mt20id;

    @NotNull
    @Schema(name = "concert_name")
    private String prfnm;

    @Schema(name = "start_date")
    private LocalDate prfpdfrom;

    @Schema(name = "end_date")
    private LocalDate prfpdto;

    @Schema(name = "facility_name")
    private String fcltynm;

    @Schema(name = "poster")
    private String poster;

    @Schema(name = "area")
    private String area;

    @Schema(name = "genre")
    private String genrenm;

    @Schema(name = "openrun")
    private String openrun;

    @Schema(name = "status")
    private String prfstate;

    @Schema(name = "cast")
    private String prfcast;

    @Schema(name = "crew")
    private String prfcrew;

    @Schema(name = "runtime")
    private String prfruntime;

    @Schema(name = "age")
    private String prfage;

    @Schema(name = "company_name")
    private String entrpsnm;

    @Schema(name = "company_namep")
    private String entrpsnmP;

    @Schema(name = "company_namea")
    private String entrpsnmA;

    @Schema(name = "company_nameh")
    private String entrpsnmH;

    @Schema(name = "company_names")
    private String entrpsnmS;

    @Schema(name = "seat_guidance")
    private String pcseguidance;

    @Schema(name = "visit")
    private String visit;

    @Schema(name = "child")
    private String child;

    @Schema(name = "daehakro")
    private String daehakro;

    @Schema(name = "festival")
    private String festival;

    @Schema(name = "musical_license")
    private String musicallicense;

    @Schema(name = "musical_create")
    private String musicalcreate;

    @Schema(name = "update_date")
    private String updatedate;

    @Schema(name = "schedule_guidance")
    private String dtguidance;

    @Schema(name = "introduction")
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
