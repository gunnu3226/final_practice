package com.sparta.finalpractice.store.repository;

import static com.sparta.finalpractice.food.QFood.food;
import static com.sparta.finalpractice.store.QStore.store;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.store.dto.StoreListResponse;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.repository.StoreRepositoryQuery;
import java.util.List;
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
    public Page<StoreListResponse> search(Pageable pageable) {
        JPAQuery<Store> query = query(store)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());


        List<StoreListResponse> stores = query.fetch().stream().map(
            s -> new StoreListResponse(
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

    private <T> JPAQuery<T> query(Expression<T> expr) {
        return jpaQueryFactory.select(expr)
            .from(store)
            .leftJoin(store.foods, food).fetchJoin();
    }

    private JPAQuery<Long> countQuery() {
        return jpaQueryFactory.select(Wildcard.count)
            .from(store);
    }

//    private BooleanExpression categoryEq(StoreCategory category) {
//        return Objects.nonNull(category) ? store.category.eq(category) : null;
//    }
}
