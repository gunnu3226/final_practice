package com.sparta.finalpractice.store.dto;

import com.sparta.finalpractice.store.entity.Store;
import lombok.Getter;

@Getter
public class StoreResponse {

    private Long id;
    private String name;

    public StoreResponse(Store savedStore) {
        this.id = savedStore.getId();
        this.name = savedStore.getName();
    }
}
