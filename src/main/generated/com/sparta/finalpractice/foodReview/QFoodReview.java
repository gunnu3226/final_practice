package com.sparta.finalpractice.foodReview;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodReview is a Querydsl query type for FoodReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodReview extends EntityPathBase<FoodReview> {

    private static final long serialVersionUID = 1966603243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodReview foodReview = new QFoodReview("foodReview");

    public final com.sparta.finalpractice.QTimeStamp _super = new com.sparta.finalpractice.QTimeStamp(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final com.sparta.finalpractice.food.QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.finalpractice.user.QUser user;

    public QFoodReview(String variable) {
        this(FoodReview.class, forVariable(variable), INITS);
    }

    public QFoodReview(Path<? extends FoodReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodReview(PathMetadata metadata, PathInits inits) {
        this(FoodReview.class, metadata, inits);
    }

    public QFoodReview(Class<? extends FoodReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new com.sparta.finalpractice.food.QFood(forProperty("food"), inits.get("food")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.finalpractice.user.QUser(forProperty("user")) : null;
    }

}

