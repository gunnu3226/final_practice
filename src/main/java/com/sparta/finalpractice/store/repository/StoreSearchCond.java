package com.sparta.finalpractice.store.repository;

import com.sparta.finalpractice.store.entity.StoreCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreSearchCond {

    private Long storeId;
    private StoreCategory category;
}
