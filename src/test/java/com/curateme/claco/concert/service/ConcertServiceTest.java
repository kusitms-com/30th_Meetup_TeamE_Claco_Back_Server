package com.curateme.claco.concert.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.curateme.claco.authentication.util.SecurityContextUtil;
import com.curateme.claco.concert.domain.dto.request.ConcertLikesRequest;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock private ConcertRepository concertRepository;
    @Mock private ConcertCategoryRepository concertCategoryRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private MemberRepository memberRepository;
    @Mock private ConcertLikeRepository concertLikeRepository;
    @Mock private SecurityContextUtil securityContextUtil;
    @Mock private TicketReviewRepository ticketReviewRepository;

    @InjectMocks private ConcertServiceImpl concertService;

    private final Pageable pageable = PageRequest.of(0, 10);

    @Test
    @DisplayName("장르 기반 콘서트 조회")
    void testGetConcertInfos() {
        // Given
        String genre = "Classical";
        List<Long> concertIds = List.of(1L, 2L);

        // Mock Concert entities
        Concert mockConcert1 = Concert.builder()
            .id(1L)
            .prfnm("클래식 콘서트 1")
            .build();
        Concert mockConcert2 = Concert.builder()
            .id(2L)
            .prfnm("클래식 콘서트 2")
            .build();

        when(concertRepository.findConcertIdsByGenre(genre)).thenReturn(concertIds);

        // Mock Concert by ID
        when(concertRepository.findConcertById(1L)).thenReturn(mockConcert1);
        when(concertRepository.findConcertById(2L)).thenReturn(mockConcert2);

        // Mock Category
        when(concertCategoryRepository.findCategoryIdsByCategoryName(1L)).thenReturn(List.of(1L));
        when(concertCategoryRepository.findCategoryIdsByCategoryName(2L)).thenReturn(List.of(2L));

        when(categoryRepository.findAllById(List.of(1L))).thenReturn(
            List.of(Category.builder().id(1L).category("웅장한").imageUrl("image-url-1").build())
        );
        when(categoryRepository.findAllById(List.of(2L))).thenReturn(
            List.of(Category.builder().id(2L).category("현대적인").imageUrl("image-url-2").build())
        );

        // Mock Pageable result
        when(concertRepository.findByIdIn(eq(concertIds), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(mockConcert1, mockConcert2)));

        // When
        PageResponse<ConcertResponse> result = concertService.getConcertInfos(genre, "asc", pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getListPageResponse()).hasSize(2);
        assertThat(result.getListPageResponse().get(0).getPrfnm()).isEqualTo("클래식 콘서트 1");
        assertThat(result.getListPageResponse().get(1).getPrfnm()).isEqualTo("클래식 콘서트 2");

        verify(concertRepository).findConcertIdsByGenre(genre);
        verify(concertRepository, times(1)).findByIdIn(eq(concertIds), any(Pageable.class));
        verify(concertRepository, times(2)).findConcertById(anyLong());
        verify(concertCategoryRepository, times(2)).findCategoryIdsByCategoryName(anyLong());
        verify(categoryRepository, times(2)).findAllById(anyList());
    }


    @Test
    @DisplayName("필터 기반 콘서트 조회")
    void testGetConcertInfosWithFilter() {
        // Given
        List<Long> concertIds = List.of(1L); // Mock concert IDs
        when(concertRepository.findConcertIdsByFilters(
            eq("서울특별시"), any(), any(), eq(List.of("웅장한")))).thenReturn(concertIds);

        // Mock concert entity
        Concert mockConcert = Concert.builder()
            .id(1L)
            .prfnm("테스트 콘서트")
            .area("서울특별시")
            .prfpdfrom(LocalDate.now().minusDays(10))
            .prfpdto(LocalDate.now().plusDays(10))
            .build();
        when(concertRepository.findConcertById(1L)).thenReturn(mockConcert);

        // Mock category data
        List<Long> categoryIds = List.of(1L, 2L);
        when(concertCategoryRepository.findCategoryIdsByCategoryName(1L)).thenReturn(categoryIds);

        Category mockCategory1 = Category.builder().id(1L).category("웅장한").imageUrl("image1.png").build();
        Category mockCategory2 = Category.builder().id(2L).category("현대적인").imageUrl("image2.png").build();
        when(categoryRepository.findAllById(categoryIds)).thenReturn(List.of(mockCategory1, mockCategory2));

        // Mock pageable data
        Sort sort = Sort.by("prfpdfrom").ascending(); // Matching sort order
        PageRequest pageableWithSort = PageRequest.of(0, 10, sort);
        when(concertRepository.findByIdIn(eq(concertIds), eq(pageableWithSort)))
            .thenReturn(new PageImpl<>(List.of(mockConcert), pageableWithSort, 1));

        // When
        PageResponse<ConcertResponse> result = concertService.getConcertInfosWithFilter(
            0.0, 100.0, "서울특별시",
            LocalDate.now().minusDays(30), LocalDate.now().plusDays(30),
            "asc", List.of("웅장한"), pageableWithSort);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getListPageResponse()).isNotEmpty();
        assertThat(result.getListPageResponse().get(0).getPrfnm()).isEqualTo("테스트 콘서트");

        verify(concertRepository).findConcertIdsByFilters(eq("서울특별시"), any(), any(), eq(List.of("웅장한")));
        verify(concertRepository).findByIdIn(eq(concertIds), eq(pageableWithSort));
    }



    @Test
    @DisplayName("좋아요 등록 및 취소")
    void testPostLikes() {
        // Given
        Long memberId = 1L, concertId = 2L;
        Member member = Member.builder().id(memberId).build();
        Concert concert = Concert.builder().id(concertId).build();

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(concertRepository.findById(concertId)).thenReturn(Optional.of(concert));
        when(concertLikeRepository.findByMemberAndConcert(member, concert)).thenReturn(Optional.empty());

        // When
        String result = concertService.postLikes(concertId);

        // Then
        assertThat(result).isEqualTo("좋아요가 등록되었습니다.");
        verify(concertLikeRepository).save(any(ConcertLike.class));

        // Test 좋아요 취소
        when(concertLikeRepository.findByMemberAndConcert(member, concert))
            .thenReturn(Optional.of(
                ConcertLike.builder()
                    .member(member)
                    .concert(concert)
                    .build()
            ));
        String cancelResult = concertService.postLikes(concertId);
        assertThat(cancelResult).isEqualTo("좋아요가 취소되었습니다.");
    }

    @Test
    @DisplayName("콘서트 상세 정보 조회")
    void testGetConcertDetailWithCategories() {
        // Given
        Long concertId = 1L;

        // Mock Concert
        Concert concert = Concert.builder()
            .id(concertId)
            .prfnm("클래식 콘서트")
            .build();
        when(concertRepository.findConcertById(concertId)).thenReturn(concert);

        // Mock TicketReview IDs
        when(ticketReviewRepository.findByConcertId(concertId)).thenReturn(List.of(1L, 2L));

        // Mock Member
        Member mockMember = Member.builder()
            .id(1L)
            .nickname("사용자1")
            .email("user1@test.com")
            .build();

        // Mock TicketReview
        TicketReview mockReview = TicketReview.builder()
            .id(1L)
            .member(mockMember) // Include Member
            .watchRound("1")
            .watchDate(LocalDate.of(2024, 11, 18))
            .watchSit("R")
            .starRate(BigDecimal.valueOf(5.0))
            .content("강추강추!")
            .casting("이승기")
            .build();

        when(ticketReviewRepository.findAllById(anyList())).thenReturn(List.of(mockReview));

        // Mock Category Data
        List<Long> categoryIds = List.of(1L, 2L);
        when(concertCategoryRepository.findCategoryIdsByCategoryName(concertId)).thenReturn(categoryIds);

        Category mockCategory1 = Category.builder().id(1L).category("웅장한").imageUrl("image1.png").build();
        Category mockCategory2 = Category.builder().id(2L).category("현대적인").imageUrl("image2.png").build();
        when(categoryRepository.findAllById(categoryIds)).thenReturn(List.of(mockCategory1, mockCategory2));

        // When
        ConcertDetailResponse result = concertService.getConcertDetailWithCategories(concertId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPrfnm()).isEqualTo("클래식 콘서트");
        assertThat(result.getTicketReviewSimpleResponses()).hasSize(1); // Validate ticket reviews
        assertThat(result.getTicketReviewSimpleResponses().get(0).getNickname()).isEqualTo("사용자1"); // Validate nickname
        verify(concertRepository).findConcertById(concertId);
        verify(ticketReviewRepository).findByConcertId(concertId);
    }

}
