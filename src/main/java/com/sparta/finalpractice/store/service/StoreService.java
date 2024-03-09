package com.sparta.finalpractice.store.service;

import com.sparta.finalpractice.store.dto.StoreResponse;
import com.sparta.finalpractice.store.dto.StoreRegisterRequest;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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
     * @return 가게의 정보 페이지로 반환
     */
    public Page<StoreResponse> selectTotalStoreList(Pageable pageable);

    /**
     * queryDsl 사용하여 가게정보 page로 반환
     * @param pageable 페이징 정보
     * @return 가게의 정보 페이지로 반환
     */
    public Page<StoreResponse> selectTotalStoreListQuery(Pageable pageable);

    /**
     * 가게ID로 상세정보 조회
     * @param storeId 가게 ID
     * @return 가게 상세정보(음식 리스트 포함)
     */
    public StoreResponse selectOneStoreByIdQuery(Long storeId);

    public Store findStoreById(Long id);
}
