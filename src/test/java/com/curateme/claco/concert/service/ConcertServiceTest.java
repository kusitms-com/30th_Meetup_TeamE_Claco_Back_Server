package com.curateme.claco.concert.service;

import com.curateme.claco.concert.domain.dto.request.ConcertLikesRequest;
import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.concert.domain.entity.Category;
import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.domain.entity.ConcertLike;
import com.curateme.claco.concert.repository.*;
import com.curateme.claco.global.exception.BusinessException;
import com.curateme.claco.global.response.PageResponse;
import com.curateme.claco.member.domain.entity.Member;
import com.curateme.claco.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class ConcertServiceTest {

    @Mock
    private ConcertRepository concertRepository;

    @Mock
    private ConcertCategoryRepository concertCategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ConcertLikeRepository concertLikeRepository;

    @InjectMocks
    private ConcertServiceImpl concertService;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testGetConcertInfos() {
        // Given
        String genre = "Classical";
        String direction = "asc";

        List<Long> concertIds = List.of(1L, 2L);
        Pageable pageable = PageRequest.of(0, 10);

        // Mocking
        when(concertRepository.findConcertIdsByGenre(anyString())).thenReturn(concertIds);
        when(concertRepository.findConcertById(anyLong())).thenReturn(mock(Concert.class));
        when(concertCategoryRepository.findCategoryIdsByCategoryName(anyLong())).thenReturn(List.of(1L, 2L));
        when(categoryRepository.findAllById(anyList())).thenReturn(List.of(mock(Category.class), mock(Category.class)));
        when(concertRepository.findByIdIn(anyList(), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(mock(Concert.class)), pageable, 1));

        // When
        PageResponse<ConcertResponse> response = concertService.getConcertInfos(genre, direction, pageable);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getListPageResponse()).isNotEmpty();
        verify(concertRepository, times(1)).findConcertIdsByGenre(genre);
        verify(concertRepository, times(1)).findByIdIn(concertIds, pageable);
    }


    @Test
    void testPostLikes() {
        // Given
        Long memberId = 1L;
        Long concertId = 2L;

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mock(Member.class)));
        when(concertRepository.findById(concertId)).thenReturn(Optional.of(mock(Concert.class)));
        when(concertLikeRepository.findByMemberAndConcert(any(Member.class), any(Concert.class)))
            .thenReturn(Optional.empty());
        // When
        String result = concertService.postLikes(new ConcertLikesRequest(memberId, concertId));

        // Then
        assertThat(result).isEqualTo("좋아요가 등록되었습니다.");
        verify(concertLikeRepository, times(1)).save(any(ConcertLike.class));
    }

    @Test
    void testPostLikesRemoveLike() {
        // Given
        Long memberId = 1L;
        Long concertId = 2L;

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mock(Member.class)));
        when(concertRepository.findById(concertId)).thenReturn(Optional.of(mock(Concert.class)));
        when(concertLikeRepository.findByMemberAndConcert(any(Member.class), any(Concert.class)))
            .thenReturn(Optional.empty());

        // When
        String result = concertService.postLikes(new ConcertLikesRequest(memberId, concertId));

        // Then
        assertThat(result).isEqualTo("좋아요가 취소되었습니다.");
        verify(concertLikeRepository, times(1)).delete(any(ConcertLike.class));
    }

    @Test
    void testPostLikesMemberNotFound() {
        // Given
        when(memberRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> concertService.postLikes(new ConcertLikesRequest(1L, 1L)))
            .isInstanceOf(BusinessException.class)
            .hasMessage("MEMBER_NOT_FOUND");
    }

    @Test
    void testGetSearchConcert() {
        // Given
        String query = "연극";
        String direction = "asc";
        Pageable pageable = PageRequest.of(0, 10);

        List<Long> concertIds = List.of(1L, 2L);

        // Mocking
        when(concertRepository.findConcertIdsBySearchQuery(anyString())).thenReturn(concertIds);
        when(concertRepository.findConcertById(anyLong())).thenReturn(mock(Concert.class));
        when(concertCategoryRepository.findCategoryIdsByCategoryName(anyLong())).thenReturn(List.of(1L, 2L));
        when(categoryRepository.findAllById(anyList())).thenReturn(List.of(mock(Category.class), mock(Category.class)));
        when(concertRepository.findByIdIn(anyList(), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(mock(Concert.class)), pageable, 1));

        // When
        PageResponse<ConcertResponse> response = concertService.getSearchConcert(query, direction, pageable);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getListPageResponse()).isNotEmpty();
        verify(concertRepository, times(1)).findConcertIdsBySearchQuery(query);
        verify(concertRepository, times(1)).findByIdIn(concertIds, pageable);
    }


}
