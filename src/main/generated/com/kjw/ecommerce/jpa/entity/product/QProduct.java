package com.kjw.ecommerce.jpa.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 432776617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.kjw.ecommerce.jpa.entity.order.Order, com.kjw.ecommerce.jpa.entity.order.QOrder> order = this.<com.kjw.ecommerce.jpa.entity.order.Order, com.kjw.ecommerce.jpa.entity.order.QOrder>createList("order", com.kjw.ecommerce.jpa.entity.order.Order.class, com.kjw.ecommerce.jpa.entity.order.QOrder.class, PathInits.DIRECT2);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.LocalDateTime> upatedAt = createDateTime("upatedAt", java.time.LocalDateTime.class);

    public final com.kjw.ecommerce.jpa.entity.user.QUser user;

    public final NumberPath<Long> userIdx = createNumber("userIdx", Long.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kjw.ecommerce.jpa.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

