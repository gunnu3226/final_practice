package com.sparta.finalpractice.store.service;


import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.finalpractice.food.FoodTest;
import com.sparta.finalpractice.store.StoreTest;
import com.sparta.finalpractice.store.dto.StoreResponse;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.repository.StoreRepository;
import com.sparta.finalpractice.store.repository.StoreSearchCond;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest implements StoreTest {

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    StoreRepository storeRepository;

    @DisplayName("가게 등록")
    @Test
    void registerStore() {
        //given
        given(storeRepository.save(any())).willReturn(TEST_STORE);

        //when
        StoreResponse response = storeService.registerStore(TEST_OWNER_USER,
            TEST_STORE_REGISTER_REQUEST);

        //then
        assertThat(response.getId()).isEqualTo(TEST_STORE_ID);
        assertThat(response.getName()).isEqualTo(TEST_STORE_NAME);
        assertThat(response.getIntroduce()).isEqualTo(TEST_STORE_INTRODUCE);
    }

    @DisplayName("가게 전체조회(페이징)")
    @Test
    void selectTotalStoreList() {
        //given
        Pageable pageable = PageRequest.of(0,10);
        Page<Store> page = new PageImpl<>(TEST_STORE_LIST);
        given(storeRepository.findAll(any(Pageable.class))).willReturn(page);

        //when
        Page<StoreResponse> response = storeService.selectTotalStoreList(pageable);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getTotalElements()).isEqualTo(TEST_STORE_LIST.size());
        assertThat(response.getTotalPages()).isEqualTo(1);
    }

    @DisplayName("가게ID로 상세정보 조회")
    @Test
    void selectOneStoreByIdQuery() {
        //given
        given(storeRepository.searchOneStore(any(StoreSearchCond.class))).willReturn(TEST_STORE_RESPONSE);

        //when
        StoreResponse response = storeService.selectOneStoreByIdQuery(TEST_STORE_ID);

        //then
        assertThat(response.getId()).isEqualTo(TEST_STORE_ID);
        assertThat(response.getName()).isEqualTo(TEST_STORE_NAME);
        assertThat(response.getIntroduce()).isEqualTo(TEST_STORE_INTRODUCE);
        assertThat(response.getFoods().size()).isEqualTo(TEST_FOOD_LIST.size());
    }
}