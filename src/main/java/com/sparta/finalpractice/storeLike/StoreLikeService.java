package com.sparta.finalpractice.storeLike;

import com.sparta.finalpractice.exception.storeLike.AlReadyStoreLikeException;
import com.sparta.finalpractice.store.Store;
import com.sparta.finalpractice.store.StoreService;
import com.sparta.finalpractice.storeLike.dto.StoreLikeResponse;
import com.sparta.finalpractice.user.User;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;
    private final StoreService storeService;

    @Transactional
    public StoreLikeResponse doStoreLike(User user, Long storeId) {
        if (storeLikeRepository.existsByUserIdAndStoreId(user.getId(), storeId)) {
            throw new AlReadyStoreLikeException("이미 찜한 가게 입니다.");
        }
        Store store = storeService.findStoreById(storeId);
        store.addStoreLikeCount();
        StoreLike savedStoreLike = storeLikeRepository.save(
            new StoreLike(user, store));
        return new StoreLikeResponse(savedStoreLike);
    }

    @Transactional
    public StoreLikeResponse cancelStoreLike(User user, Long storeId) {
        StoreLike findStoreLike = storeLikeRepository.findByUserIdAndStoreId(
            user.getId(), storeId).orElseThrow(
            () -> new NoSuchElementException("유저가 찜하지 않은 가게 입니다.")
        );
        findStoreLike.getStore().subtractLikeCount();
        storeLikeRepository.delete(findStoreLike);
        return new StoreLikeResponse(findStoreLike);
    }
}
