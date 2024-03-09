package com.sparta.finalpractice.store.entity;

import com.sparta.finalpractice.global.entity.TimeStamp;
import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.store.dto.StoreRegisterRequest;
import com.sparta.finalpractice.store.entity.StoreCategory;
import com.sparta.finalpractice.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STORE_TB")
public class Store extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String introduce;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "store")
    @BatchSize(size = 100)
    private List<Food> foods;

    private int storeLike;

    public Store(StoreRegisterRequest requestDto, User store) {
        this.name = requestDto.getName();
        this.introduce = requestDto.getIntroduce();
        this.category = StoreCategory.valueOf(requestDto.getCategory());
        this.owner = store;
    }

    public void addStoreLikeCount() {
        this.storeLike++;
    }

    public void subtractLikeCount() {
        this.storeLike--;
    }
}
