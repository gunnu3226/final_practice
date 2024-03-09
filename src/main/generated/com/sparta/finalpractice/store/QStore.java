package com.sparta.finalpractice.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.entity.StoreCategory;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 1498322065L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final com.sparta.finalpractice.QTimeStamp _super = new com.sparta.finalpractice.QTimeStamp(this);

    public final EnumPath<StoreCategory> category = createEnum("category", StoreCategory.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final ListPath<Food, com.sparta.finalpractice.food.QFood> foods = this.<Food, com.sparta.finalpractice.food.QFood>createList("foods", Food.class, com.sparta.finalpractice.food.QFood.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduce = createString("introduce");

    public final StringPath name = createString("name");

    public final com.sparta.finalpractice.user.QUser owner;

    public final NumberPath<Integer> storeLike = createNumber("storeLike", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new com.sparta.finalpractice.user.QUser(forProperty("owner")) : null;
    }

}

