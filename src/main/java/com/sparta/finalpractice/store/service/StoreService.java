package com.sparta.finalpractice.store.service;

import com.sparta.finalpractice.store.dto.StoreListResponse;
import com.sparta.finalpractice.store.dto.StoreRegisterRequest;
import com.sparta.finalpractice.store.dto.StoreResponse;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public interface StoreService {


    @Transactional
    public StoreResponse registerStore(User owner, StoreRegisterRequest requestDto);

    public Store findStoreById(Long id);

    public Page<StoreListResponse> selectTotalStoreList(Pageable pageable);

    public Page<StoreListResponse> selectTotalStoreListQuery(Pageable pageable);
}
