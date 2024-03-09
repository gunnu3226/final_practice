package com.sparta.finalpractice.store.repository;

import static com.sparta.finalpractice.food.entity.QFood.food;
import static com.sparta.finalpractice.store.entity.QStore.*;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.store.dto.StoreResponse;
import com.sparta.finalpractice.store.entity.QStore;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.entity.StoreCategory;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@Slf4j
@RequiredArgsConstructor
public class StoreRepositoryQueryImpl implements StoreRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public StoreResponse searchOneStore(StoreSearchCond cond) {
        Store store = query(QStore.store, cond).fetch().get(0);
        return new StoreResponse(store);
    }

    @Override
    public Page<StoreResponse> search(Pageable pageable, StoreSearchCond cond) {
        JPAQuery<Store> query = query(store, cond)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());


        List<StoreResponse> stores = query.fetch().stream().map(
            s -> new StoreResponse(
                s.getId(),
                s.getName(),
                s.getIntroduce(),
                s.getFoods().stream().map(FoodResponse::new)
                    .collect(Collectors.toList())))
            .collect(Collectors.toList());

        Long totalCount = countQuery().fetch().get(0);
//        Long totalCount2 = (long) stores.size();

        return PageableExecutionUtils.getPage(stores, pageable, () -> totalCount);
    }


    private <T> JPAQuery<T> query(Expression<T> expr, StoreSearchCond cond) {
        return jpaQueryFactory.select(expr)
            .from(store)
            .leftJoin(store.foods, food).fetchJoin()
            .where(
                storeIdEq(cond.getStoreId()),
                storeCategoryEq(cond.getCategory())
            );
    }

    private JPAQuery<Long> countQuery() {
        return jpaQueryFactory.select(Wildcard.count)
            .from(store);
    }

    private BooleanExpression storeIdEq(Long storeId) {
        return Objects.nonNull(storeId) ? store.id.eq(storeId) : null;
    }

    private BooleanExpression storeCategoryEq(StoreCategory category) {
        return Objects.nonNull(category) ? store.category.eq(category) : null;
    }
}
