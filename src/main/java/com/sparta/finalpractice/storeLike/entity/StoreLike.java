package com.sparta.finalpractice.storeLike.entity;

import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STORELIKE_TB")
public class StoreLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Getter
    @Column(columnDefinition = "boolean default true")
    private boolean vaild;

    public StoreLike(User user, Store store) {
        this.user = user;
        this.store = store;
    }

    public void updateLike() {
        this.vaild = !vaild;
    }
}
