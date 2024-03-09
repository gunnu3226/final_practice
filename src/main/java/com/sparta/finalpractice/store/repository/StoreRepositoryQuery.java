package com.sparta.finalpractice.store.repository;

import com.sparta.finalpractice.store.dto.StoreListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryQuery {

    Page<StoreListResponse> search(Pageable pageable);
}
