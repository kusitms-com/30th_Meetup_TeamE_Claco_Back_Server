package com.curateme.claco.concert.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.curateme.claco.concert.domain.entity.Category;
import com.curateme.claco.concert.domain.entity.Concert;
import com.curateme.claco.concert.domain.entity.ConcertCategory;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConcertCategoryRepositoryTest {

    @Autowired
    private ConcertCategoryRepository concertCategoryRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        // 1. Concert 생성
        Concert concert = concertRepository.save(
            Concert.builder()
                .mt20id("C12345")
                .prfnm("Test Concert")
                .prfpdfrom(LocalDate.of(2024, 1, 1))
                .prfpdto(LocalDate.of(2024, 1, 31))
                .fcltynm("Grand Theater")
                .build()
        );

        // 2. Category 생성
        Category classicalCategory = categoryRepository.save(
            Category.builder()
                .category("웅장한")
                .imageUrl("xxx")
                .build()
        );

        Category modernCategory = categoryRepository.save(
            Category.builder()
                .category("현대적인")
                .imageUrl("xxx")
                .build()
        );

        // 3. ConcertCategory 생성
        concertCategoryRepository.save(ConcertCategory.builder()
            .concert(concert)
            .score(8.5) // 점수 추가
            .category(classicalCategory)
            .build()
        );

       concertCategoryRepository.save(ConcertCategory.builder()
            .concert(concert)
            .score(7.0) // 점수 추가
            .category(modernCategory)
            .build()
        );
    }


    @Test
    void testFindCategoryIdsByCategoryName() {
        // Given
        Long concertId = concertRepository.findAll().get(0).getId();

        // When
        List<Long> categoryIds = concertCategoryRepository.findCategoryIdsByCategoryName(concertId);

        // Then
        assertThat(categoryIds).isNotNull();
        assertThat(categoryIds).containsExactlyInAnyOrder(1L, 2L);
    }
}

