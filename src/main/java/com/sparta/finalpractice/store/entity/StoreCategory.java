package com.sparta.finalpractice.store.entity;

public enum StoreCategory {
    CHINESE("중식"),
    KOREAN("한식"),
    CHICKEN("치킨"),
    PIZZA("피자");

    private final String category;

    StoreCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
