package com.sparta.finalpractice.store;

import com.sparta.CommonTest;
import com.sparta.finalpractice.food.FoodTest;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.store.dto.StoreRegisterRequest;
import com.sparta.finalpractice.store.dto.StoreResponse;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.entity.StoreCategory;
import com.sparta.finalpractice.user.UserTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface StoreTest extends UserTest ,FoodTest, CommonTest {

    Long TEST_STORE_ID = 1L;
    String TEST_STORE_NAME = "store";
    String TEST_STORE_INTRODUCE = "introduce";
    String TEST_STORE_CATEGORY = "KOREAN";

    StoreCategory TEST_STORE_CATEGORY_KOREAN = StoreCategory.KOREAN;

    Store TEST_STORE = Store.builder()
        .id(TEST_STORE_ID)
        .name(TEST_STORE_NAME)
        .introduce(TEST_STORE_INTRODUCE)
        .category(TEST_STORE_CATEGORY_KOREAN)
        .owner(TEST_OWNER_USER)
        .foods(TEST_FOOD_LIST)
        .build();
    List<Store> TEST_STORE_LIST = new ArrayList<>(List.of(new Store[]{TEST_STORE}));
    StoreRegisterRequest TEST_STORE_REGISTER_REQUEST = StoreRegisterRequest.builder()
        .name(TEST_STORE_NAME)
        .introduce(TEST_STORE_INTRODUCE)
        .category(TEST_STORE_CATEGORY)
        .build();
    StoreResponse TEST_STORE_RESPONSE = StoreResponse.builder()
        .id(TEST_STORE_ID)
        .name(TEST_STORE_NAME)
        .introduce(TEST_STORE_INTRODUCE)
        .foods(TEST_FOOD_LIST.stream().map(FoodResponse::new).collect(Collectors.toList()))
        .build();
}
