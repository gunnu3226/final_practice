package com.sparta.finalpractice.storeLike;

import com.sparta.finalpractice.exception.storeLike.AlReadyStoreLikeException;
import com.sparta.finalpractice.store.Store;
import com.sparta.finalpractice.store.StoreService;
import com.sparta.finalpractice.storeLike.dto.StoreLikeResponse;
import com.sparta.finalpractice.user.User;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;
    private final StoreService storeService;
    private final RedisTemplate<String, Map<String, Boolean>> redisTemplate;

    private final String REDIS_KEY = "StoreLike";

//    @Transactional
//    public StoreLikeResponse doStoreLike(User user, Long storeId) {
//        if (storeLikeRepository.existsByUserIdAndStoreId(user.getId(), storeId)) {
//            throw new AlReadyStoreLikeException("이미 찜한 가게 입니다.");
//        }
//        Store store = storeService.findStoreById(storeId);
//        store.addStoreLikeCount();
//        StoreLike savedStoreLike = storeLikeRepository.save(
//            new StoreLike(user, store));
//        return new StoreLikeResponse(savedStoreLike);
//    }
//
//    @Transactional
//    public StoreLikeResponse cancelStoreLike(User user, Long storeId) {
//        StoreLike findStoreLike = storeLikeRepository.findByUserIdAndStoreId(
//            user.getId(), storeId).orElseThrow(
//            () -> new NoSuchElementException("유저가 찜하지 않은 가게 입니다.")
//        );
//        findStoreLike.getStore().subtractLikeCount();
//        storeLikeRepository.delete(findStoreLike);
//        return new StoreLikeResponse(findStoreLike);
//    }

//    @Transactional
//    public void storeLikeRedis(User user, Long storeId) {
//        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
//    }
//
//    @Transactional
//    public void storeLike(User user, Long storeId) {
//        Store store = storeService.findStoreById(storeId);
//        if (!storeLikeRepository.existsByUserIdAndStoreId(user.getId(), storeId)) {
//            storeLikeRepository.save(new StoreLike(user, store));
//            return;
//        } else {
//            StoreLike storeLike = storeLikeRepository.findByUserIdAndStoreId(user.getId(), storeId)
//                .orElseThrow();
//            if(storeLike.getVaild()) {
//                storeLike.disLike();
//                return;
//            }
//            storeLike.doLike();
//            return;
//        }
//    }


    @Transactional
    public void storeLikeRedis(User user, Long storeId) {
        String hashKey = generateHashKey(user.getId(), storeId);
        log.info("레디스 접근");
        if(redisTemplate.opsForHash().hasKey(REDIS_KEY, hashKey)) {
            // 기존 값 가져오기
            log.info("레디스 값 존재, 찾아오기");
            Optional<Boolean> like = Optional.ofNullable(
                (Boolean) redisTemplate.opsForHash().get(REDIS_KEY, hashKey));
            log.info("레디스 값 바꾸기");
            redisTemplate.opsForHash().put(REDIS_KEY, hashKey, !like.get());
            return;
        }
        log.info("레디스 값 true로 바꿈");
        redisTemplate.opsForHash().put(REDIS_KEY, hashKey, true);
        return;
    }

    // Hash Key 생성 메서드
    private String generateHashKey(Long userId, Long storeId) {
        return userId + ":" + storeId;
    }

    // 실제 DB와의 동기화 작업
    private void syncLikesWithDB() {
        // 이곳에 레디스에 저장된 좋아요 정보를 가져와서 DB에 적용하는 작업을 수행
        // storeLikeRepository.save(new StoreLike(user, store)); 와 같은 형태로 실제 DB에 적용
        // 이 작업은 프로젝트의 요구사항에 맞게 구현되어야 합니다.
    }
}
