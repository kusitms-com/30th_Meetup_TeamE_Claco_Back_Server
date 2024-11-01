package com.curateme.claco.concert.service;

import com.curateme.claco.concert.domain.dto.response.ConcertResponse;
import com.curateme.claco.concert.repository.ConcertCategoryRepository;
import com.curateme.claco.concert.repository.ConcertRepository;
import com.curateme.claco.global.response.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService{
    private final ConcertRepository concertRepository;
    private final ConcertCategoryRepository concertCategoryRepository;
    @Override
    public PageResponse<List<ConcertResponse>> getConcertInfos(String categoryName, String direction, Pageable pageable) {


        return null;
    }
}
