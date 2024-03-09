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


    /**
     * 가게 등록(Owner 유저만 가능)
     * @param owner 인증 유저
     * @param requestDto 등록할 가게 정보
     * @return 등록된 가게 정보
     */
    @Transactional
    public StoreResponse registerStore(User owner, StoreRegisterRequest requestDto);


    /**
     * JpaRepository 사용하여 가게정보 page로 반환
     * @param pageable 페이징 정보
     * @return 페이지<store> 가게의 메뉴정보 포함
     */
    public Page<StoreListResponse> selectTotalStoreList(Pageable pageable);

    /**
     * queryDsl 사용하여 가게정보 page로 반환
     * @param pageable 페이징 정보
     * @return 페이지<store> 가게의 메뉴정보 포함
     */
    public Page<StoreListResponse> selectTotalStoreListQuery(Pageable pageable);

    public Store findStoreById(Long id);
}
