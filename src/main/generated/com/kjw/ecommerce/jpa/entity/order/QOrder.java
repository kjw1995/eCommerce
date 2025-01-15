package com.kjw.ecommerce.jpa.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1546317799L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrderDetail orderDetail;

    public final com.kjw.ecommerce.jpa.entity.product.QProduct product;

    public final NumberPath<Long> productIdx = createNumber("productIdx", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public final DateTimePath<java.time.LocalDateTime> upatedAt = createDateTime("upatedAt", java.time.LocalDateTime.class);

    public final com.kjw.ecommerce.jpa.entity.user.QUser user;

    public final NumberPath<Long> userIdx = createNumber("userIdx", Long.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderDetail = inits.isInitialized("orderDetail") ? new QOrderDetail(forProperty("orderDetail"), inits.get("orderDetail")) : null;
        this.product = inits.isInitialized("product") ? new com.kjw.ecommerce.jpa.entity.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new com.kjw.ecommerce.jpa.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

