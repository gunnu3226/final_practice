package com.sparta.finalpractice.storeLike;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import com.sparta.finalpractice.storeLike.entity.StoreLike;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreLike is a Querydsl query type for StoreLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreLike extends EntityPathBase<StoreLike> {

    private static final long serialVersionUID = 286647231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreLike storeLike = new QStoreLike("storeLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.sparta.finalpractice.store.QStore store;

    public final com.sparta.finalpractice.user.QUser user;

    public final BooleanPath vaild = createBoolean("vaild");

    public QStoreLike(String variable) {
        this(StoreLike.class, forVariable(variable), INITS);
    }

    public QStoreLike(Path<? extends StoreLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreLike(PathMetadata metadata, PathInits inits) {
        this(StoreLike.class, metadata, inits);
    }

    public QStoreLike(Class<? extends StoreLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.sparta.finalpractice.store.QStore(forProperty("store"), inits.get("store")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.finalpractice.user.QUser(forProperty("user")) : null;
    }

}

