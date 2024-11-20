package com.curateme.claco.concert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.curateme.claco.authentication.domain.JwtMemberDetail;
import com.curateme.claco.authentication.util.SecurityContextUtil;
import com.curateme.claco.concert.domain.dto.response.*;
import com.curateme.claco.concert.domain.entity.*;
import com.curateme.claco.concert.repository.*;
import com.curateme.claco.global.response.PageResponse;
import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.repository.MemberRepository;
import com.curateme.claco.review.domain.entity.TicketReview;
import com.curateme.claco.review.repository.TicketReviewRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock private ConcertRepository concertRepository;
    @Mock private ConcertCategoryRepository concertCategoryRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private MemberRepository memberRepository;
    @Mock private SecurityContextUtil securityContextUtil;
    @Mock private ConcertLikeRepository concertLikeRepository;
    @Mock private TicketReviewRepository ticketReviewRepository;

    @InjectMocks private ConcertServiceImpl concertService;

    private final Pageable pageable = PageRequest.of(0, 10, Sort.by("prfpdfrom").ascending());

    @Test
    @DisplayName("장르 기반 콘서트 조회")
    void testGetConcertInfos() {
        // Given
        String genre = "Classical";
        Concert mockConcert = Concert.builder()
            .id(1L)
            .prfnm("클래식 콘서트")
            .prfpdfrom(LocalDate.of(2024, 1, 1))
            .prfpdto(LocalDate.of(2024, 12, 31))
            .genrenm("Classical")
            .build();

        when(concertRepository.findConcertsByGenreWithPagination(eq(genre), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(mockConcert), pageable, 1));

        when(concertCategoryRepository.findCategoryIdsByCategoryName(1L))
            .thenReturn(List.of(1L));
        when(categoryRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(
                Category.builder().id(1L).category("웅장한").imageUrl("image-url-1").build()
            ));

        // When
        PageResponse<ConcertResponse> result = concertService.getConcertInfos(genre, "asc", pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getListPageResponse()).hasSize(1);
        assertThat(result.getListPageResponse().get(0).getPrfnm()).isEqualTo("클래식 콘서트");
        verify(concertRepository, times(1)).findConcertsByGenreWithPagination(eq(genre), any(Pageable.class));
    }

    @Test
    @DisplayName("필터 기반 콘서트 조회")
    void testGetConcertInfosWithFilter() {
        // Given
        List<String> categories = List.of("웅장한");
        Concert mockConcert = Concert.builder()
            .id(1L)
            .prfnm("테스트 콘서트")
            .area("서울특별시")
            .prfpdfrom(LocalDate.of(2024, 1, 1))
            .prfpdto(LocalDate.of(2024, 12, 31))
            .build();

        when(concertRepository.findConcertsByFilters(
            eq("서울특별시"),
            eq(LocalDate.of(2023, 1, 1)),
            eq(LocalDate.of(2024, 12, 31)),
            eq(categories),
            any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(mockConcert), pageable, 1));

        when(concertCategoryRepository.findCategoryIdsByCategoryName(1L))
            .thenReturn(List.of(1L));
        when(categoryRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(
                Category.builder().id(1L).category("웅장한").imageUrl("image-url-1").build()
            ));

        // When
        PageResponse<ConcertResponse> result = concertService.getConcertInfosWithFilter(
            0.0, 100.0, "서울특별시",
            LocalDate.of(2023, 1, 1), LocalDate.of(2024, 12, 31),
            "asc", categories, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getListPageResponse()).hasSize(1);
        assertThat(result.getListPageResponse().get(0).getPrfnm()).isEqualTo("테스트 콘서트");
        verify(concertRepository, times(1)).findConcertsByFilters(
            eq("서울특별시"),
            eq(LocalDate.of(2023, 1, 1)),
            eq(LocalDate.of(2024, 12, 31)),
            eq(categories),
            any(Pageable.class)
        );
    }

    /*
    @Test
    @DisplayName("콘서트 상세 정보 조회")
    void testGetConcertDetailWithCategories() {
        // Given
        Long concertId = 1L;
        Long memberId = 10L; // Mock된 사용자 ID

        Concert mockConcert = Concert.builder()
            .id(concertId)
            .prfnm("테스트 콘서트")
            .build();

        when(concertRepository.findConcertById(concertId))
            .thenReturn(mockConcert);

        when(concertCategoryRepository.findCategoryIdsByCategoryName(concertId))
            .thenReturn(List.of(1L));

        when(categoryRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(
                Category.builder().id(1L).category("웅장한").imageUrl("image-url-1").build()
            ));

        when(ticketReviewRepository.findByConcertId(concertId))
            .thenReturn(List.of(1L));

        when(ticketReviewRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(
                TicketReview.builder()
                    .id(1L)
                    .starRate(BigDecimal.valueOf(4.5))
                    .build()
            ));

        // Mock JwtMemberDetail
        JwtMemberDetail mockJwtMemberDetail = JwtMemberDetail.builder()
            .memberId(memberId)
            .email("test@example.com")
            .build();

        when(securityContextUtil.getContextMemberInfo())
            .thenReturn(mockJwtMemberDetail);

        when(concertLikeRepository.existsByConcertIdAndMemberId(concertId, memberId))
            .thenReturn(true);

        // When
        ConcertDetailResponse result = concertService.getConcertDetailWithCategories(concertId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPrfnm()).isEqualTo("테스트 콘서트");
        assertThat(result.getCategories()).hasSize(1);
        assertThat(result.getCategories().get(0).getCategory()).isEqualTo("웅장한");
        assertThat(result.isLiked()).isTrue(); // 좋아요 상태 확인
        verify(concertRepository, times(1)).findConcertById(concertId);
        verify(securityContextUtil, times(1)).getContextMemberInfo();
        verify(concertLikeRepository, times(1)).existsByConcertIdAndMemberId(concertId, memberId);
    }
*/

}
