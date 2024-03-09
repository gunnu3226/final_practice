package com.sparta.finalpractice.store.service;

import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.store.dto.StoreListResponse;
import com.sparta.finalpractice.store.dto.StoreRegisterRequest;
import com.sparta.finalpractice.store.dto.StoreResponse;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.repository.StoreRepository;
import com.sparta.finalpractice.store.service.StoreService;
import com.sparta.finalpractice.user.entity.User;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public StoreResponse registerStore(User owner, StoreRegisterRequest requestDto) {
        Store savedStore = storeRepository.save(new Store(requestDto, owner));
        return new StoreResponse(savedStore);
    }

    @Override
    public Store findStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("ID : " + id + "인 Store는 존재하지 않습니다.")
        );
    }

    @Override
    public Page<StoreListResponse> selectTotalStoreList(Pageable pageable) {
        Page<Store> page = storeRepository.findAll(pageable);

        return page.map(s -> new StoreListResponse(s.getId(), s.getName(), s.getIntroduce(),
            s.getFoods().stream()
                .map(food -> new FoodResponse(food)).collect(Collectors.toList())));
    }

    @Override
    public Page<StoreListResponse> selectTotalStoreListQuery(Pageable pageable) {
        return storeRepository.search(pageable);
    }

}
