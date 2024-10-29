package com.curateme.claco.concert.domain.entity;

import com.curateme.claco.global.entity.BaseEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Concert extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ElementCollection
    @CollectionTable(name = "ConcertCategory", joinColumns = @JoinColumn(name = "concert_entity_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "score")
    private Map<String, Double> categories;
}
