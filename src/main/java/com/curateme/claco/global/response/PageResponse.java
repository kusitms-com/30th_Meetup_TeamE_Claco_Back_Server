package com.curateme.claco.global.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> listPageResponse = new ArrayList<>();

    private Long totalCount;

    private Integer size;

    @Builder
    public PageResponse(List<T> listPageResponse, Long totalCount, Integer size) {

        this.listPageResponse = listPageResponse;
        this.totalCount = totalCount;
        this.size = size;

    }

}
