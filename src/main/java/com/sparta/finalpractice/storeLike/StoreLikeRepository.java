package com.sparta.finalpractice.storeLike;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {

    boolean existsByUserIdAndStoreId(Long userId, Long storeId);

    Optional<StoreLike> findByUserIdAndStoreId(Long userId, Long StoreId);
}
