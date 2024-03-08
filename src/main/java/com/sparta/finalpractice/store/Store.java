package com.sparta.finalpractice.store;

import com.sparta.finalpractice.food.Food;
import com.sparta.finalpractice.store.dto.StoreRegisterRequestDto;
import com.sparta.finalpractice.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STORE_TB")
public class Store {

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

    private int storeLike = 0;

    public Store(StoreRegisterRequestDto requestDto, User store) {
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
