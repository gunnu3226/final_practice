package com.sparta.finalpractice.store;

import com.sparta.finalpractice.store.dto.StoreRegisterRequestDto;
import com.sparta.finalpractice.store.dto.StoreResponseDto;
import com.sparta.finalpractice.user.User;
import com.sparta.finalpractice.user.UserService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Transactional
    public StoreResponseDto registerStore(User owner, StoreRegisterRequestDto requestDto) {
        Store savedStore = storeRepository.save(new Store(requestDto, owner));
        return new StoreResponseDto(savedStore);
    }

    public Store findStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("ID : " + id + "인 Store는 존재하지 않습니다.")
        );
    }
}
