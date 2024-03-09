package com.sparta.finalpractice.storeLike.repository;

import com.sparta.finalpractice.storeLike.entity.StoreLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {

    boolean existsByUserIdAndStoreId(Long userId, Long storeId);

    //이거 쿼리 1개로 나갈 수 있도록 store join쿼리 추가해야함.
    Optional<StoreLike> findByUserIdAndStoreId(Long userId, Long StoreId);
}
