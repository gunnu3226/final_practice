package com.sparta.finalpractice.store;

import com.sparta.finalpractice.store.dto.StoreRegisterRequestDto;
import com.sparta.finalpractice.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STORE_TB")
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String introduce;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private int storeLike;

    public Store(StoreRegisterRequestDto requestDto, User store) {
        this.name = requestDto.getName();
        this.introduce = requestDto.getIntroduce();
        this.category = StoreCategory.valueOf(requestDto.getCategory());
        this.owner = store;
    }
}
