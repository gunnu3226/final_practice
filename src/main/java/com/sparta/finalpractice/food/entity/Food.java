package com.sparta.finalpractice.food.entity;

import com.sparta.finalpractice.global.entity.TimeStamp;
import com.sparta.finalpractice.food.dto.FoodRequest;
import com.sparta.finalpractice.food.dto.FoodUpdateRequest;
import com.sparta.finalpractice.store.entity.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@Table(name = "FOOD_TB")
public class Food extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private Long reviewCount;

    public Food(FoodRequest request, Store store) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.price = request.getPrice();
        this.store = store;
    }

    public void update(FoodUpdateRequest foodRequest) {
        this.name = foodRequest.getName();
        this.description = foodRequest.getDescription();
        this.price = foodRequest.getPrice();
    }

    public void addReviewCount() {
        reviewCount++;
    }

    public void subtractReviewCount() {
        reviewCount--;
    }
}
