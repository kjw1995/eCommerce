package com.kjw.ecommerce.jpa.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1828491833L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QAddress address;

    public final ListPath<Auth, QAuth> auth = this.<Auth, QAuth>createList("auth", Auth.class, QAuth.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath isActive = createString("isActive");

    public final ListPath<com.kjw.ecommerce.jpa.entity.order.Order, com.kjw.ecommerce.jpa.entity.order.QOrder> order = this.<com.kjw.ecommerce.jpa.entity.order.Order, com.kjw.ecommerce.jpa.entity.order.QOrder>createList("order", com.kjw.ecommerce.jpa.entity.order.Order.class, com.kjw.ecommerce.jpa.entity.order.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phonenumber = createString("phonenumber");

    public final ListPath<com.kjw.ecommerce.jpa.entity.product.Product, com.kjw.ecommerce.jpa.entity.product.QProduct> product = this.<com.kjw.ecommerce.jpa.entity.product.Product, com.kjw.ecommerce.jpa.entity.product.QProduct>createList("product", com.kjw.ecommerce.jpa.entity.product.Product.class, com.kjw.ecommerce.jpa.entity.product.QProduct.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> upatedAt = createDateTime("upatedAt", java.time.LocalDateTime.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address"), inits.get("address")) : null;
    }

}

