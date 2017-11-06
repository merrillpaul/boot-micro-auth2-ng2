package com.pearson.projectone.core.support.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDocumentEntity is a Querydsl query type for DocumentEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QDocumentEntity extends BeanPath<DocumentEntity> {

    private static final long serialVersionUID = 1291958918L;

    public static final QDocumentEntity documentEntity = new QDocumentEntity("documentEntity");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QDocumentEntity(String variable) {
        super(DocumentEntity.class, forVariable(variable));
    }

    public QDocumentEntity(Path<? extends DocumentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumentEntity(PathMetadata metadata) {
        super(DocumentEntity.class, metadata);
    }

}

