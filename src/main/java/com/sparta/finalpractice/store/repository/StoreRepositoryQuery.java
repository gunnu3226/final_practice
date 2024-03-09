package com.sparta.finalpractice.store.repository;

import com.sparta.finalpractice.store.dto.StoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryQuery {

    Page<StoreResponse> search(Pageable pageable, StoreSearchCond cond);

    StoreResponse searchOneStore(StoreSearchCond cond);
}
