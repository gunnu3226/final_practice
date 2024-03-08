package com.sparta.finalpractice.store.dto;

import com.sparta.finalpractice.store.Store;
import com.sparta.finalpractice.store.StoreCategory;
import lombok.Getter;

@Getter
public class StoreResponseDto {

    private Long id;
    private String name;
    private StoreCategory category;
    private Long owner_id;

    public StoreResponseDto(Store savedStore) {
        this.id = savedStore.getId();
        this.name = savedStore.getName();
        this.category = savedStore.getCategory();
        this.owner_id = savedStore.getId();
    }
}
