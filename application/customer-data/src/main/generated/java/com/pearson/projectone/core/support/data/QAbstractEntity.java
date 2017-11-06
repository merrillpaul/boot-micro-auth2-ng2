package com.pearson.projectone.core.support.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractEntity is a Querydsl query type for AbstractEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QAbstractEntity extends BeanPath<AbstractEntity> {

    private static final long serialVersionUID = 1856452685L;

    public static final QAbstractEntity abstractEntity = new QAbstractEntity("abstractEntity");

    public final com.pearson.projectone.core.support.data.interceptor.trackchanges.QTrackableEntity _super = new com.pearson.projectone.core.support.data.interceptor.trackchanges.QTrackableEntity(this);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateDeleted = createDateTime("dateDeleted", java.util.Date.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath deletedBy = createString("deletedBy");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> lastCreated = createDateTime("lastCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> lastUpdated = createDateTime("lastUpdated", java.util.Date.class);

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    public final StringPath updatedBy = createString("updatedBy");

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QAbstractEntity(String variable) {
        super(AbstractEntity.class, forVariable(variable));
    }

    public QAbstractEntity(Path<? extends AbstractEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractEntity(PathMetadata metadata) {
        super(AbstractEntity.class, metadata);
    }

}

