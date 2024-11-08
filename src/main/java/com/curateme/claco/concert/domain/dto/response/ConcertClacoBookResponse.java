package com.curateme.claco.concert.domain.dto.response;

import com.curateme.claco.concert.domain.entity.Concert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
public class ConcertClacoBookResponse {

    @NotNull
    @Schema(name = "concert_name")
    private String prfnm;

    @Schema(name = "poster")
    private String poster;

    @Schema(name = "facility_name")
    private String fcltynm;

    @Schema(name = "categories")
    private List<ConcertCategoryResponse> categories;

    public static ConcertClacoBookResponse fromEntity(
        Concert concert, List<ConcertCategoryResponse> categories
    ) {
        return new ConcertClacoBookResponse(concert.getPrfnm(), concert.getPoster(),
            concert.getFcltynm(), categories);
    }
}
