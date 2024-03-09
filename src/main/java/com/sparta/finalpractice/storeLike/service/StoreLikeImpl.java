package com.sparta.finalpractice.storeLike.service;

import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.service.StoreService;
import com.sparta.finalpractice.storeLike.entity.StoreLike;
import com.sparta.finalpractice.storeLike.repository.StoreLikeRepository;
import com.sparta.finalpractice.user.entity.User;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreLikeImpl implements StoreLikeService{

    private final StoreLikeRepository storeLikeRepository;
    private final StoreService storeService;
    private final RedisTemplate<String, Map<String, Boolean>> redisTemplate;

    private final String REDIS_KEY = "StoreLike";

//    @Scheduled(fixedRate = 60 * 60 * 1000) // 1시간마다 실행
//    @Transactional
//    public void syncStoreLikes() {
//        log.info("Syncing store likes from Redis to DB...");
//        Set<String> hashKeys = redisTemplate.opsForHash().keys(REDIS_KEY);
//
//        for (String hashKey : hashKeys) {
//            Long userId = Long.valueOf(hashKey.split("::")[0]);
//            Long storeId = Long.valueOf(hashKey.split("::")[1]);
//            Boolean like = (Boolean) redisTemplate.opsForHash().get(REDIS_KEY, hashKey);
//
//            // Save or update the like status in the database
//            storeLikeRepository.findByUserIdAndStoreId(userId, storeId).ifPresentOrElse(
//                storeLike -> storeLike.setLike(like),
//                () -> storeLikeRepository.save(
//                    new StoreLike(storeService.findStoreById(storeId), like))
//            );
//        }
//    }

    @Override
    @Transactional
    public void saveStoreLikeDB(User user, Long storeId) {
        Store store = storeService.findStoreById(storeId);
        storeLikeRepository.save(new StoreLike(user, store));
    }

    @Override
    @Transactional
    public void storeLikeRedis(User user, Long storeId) {
        String hashKey = generateHashKey(user.getId(), storeId);

        if(redisTemplate.opsForHash().hasKey(REDIS_KEY, hashKey)) {
            Boolean like = (Boolean) redisTemplate.opsForHash().get(REDIS_KEY, hashKey);
            redisTemplate.opsForHash().put(REDIS_KEY, hashKey, !like);
            return;
        }
        saveStoreLikeDB(user, storeId);
        redisTemplate.opsForHash().put(REDIS_KEY, hashKey, true);
    }

    private String generateHashKey(Long userId, Long storeId) {
        return userId + "::" + storeId;
    }
}
