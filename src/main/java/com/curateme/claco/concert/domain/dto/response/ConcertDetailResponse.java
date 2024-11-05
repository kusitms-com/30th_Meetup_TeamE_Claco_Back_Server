package com.curateme.claco.concert.domain.dto.response;

import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.domain.entity.ConcertCategory;
import com.curateme.claco.review.domain.entity.TicketReview;
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
    @Column(name = "concert_id")
    private String mt20id;

    @NotNull
    @Column(name = "concert_name")
    private String prfnm;

    @Column(name = "start_date")
    private LocalDate prfpdfrom;

    @Column(name = "end_date")
    private LocalDate prfpdto;

    @Column(name = "facility_name")
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

    @Column(name = "company_name")
    private String entrpsnm;

    @Column(name = "company_namep")
    private String entrpsnmP;

    @Column(name = "company_namea")
    private String entrpsnmA;

    @Column(name = "company_nameh")
    private String entrpsnmH;

    @Column(name = "company_names")
    private String entrpsnmS;

    @Column(name = "seat_guidance")
    private String pcseguidance;

    @Column(name = "visit")
    private String visit;

    @Column(name = "child")
    private String child;

    @Column(name = "daehakro")
    private String daehakro;

    @Column(name = "festival")
    private String festival;

    @Column(name = "musical_license")
    private String musicallicense;

    @Column(name = "musical_create")
    private String musicalcreate;

    @Column(name = "update_date")
    private String updatedate;

    @Column(name = "schedule_guidance", length = 1000)
    private String dtguidance;

    @Column(name = "introduction")
    private String styurl;

    @Column(name = "ticketReview")
    private List<TicketReview> ticketReview;

    @Column(name = "categories")
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
