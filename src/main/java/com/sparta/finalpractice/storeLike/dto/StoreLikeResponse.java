package com.sparta.finalpractice.storeLike.dto;

import com.sparta.finalpractice.storeLike.StoreLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreLikeResponse {

    private Long storeLikeId;
    private Long userId;
    private Long storeId;
    private String storeName;

    public StoreLikeResponse(StoreLike storeLike) {
        this.storeLikeId = storeLike.getId();
        this.userId = storeLike.getUser().getId();
        this.storeId = storeLike.getStore().getId();
        this.storeName = storeLike.getStore().getName();
    }
}
