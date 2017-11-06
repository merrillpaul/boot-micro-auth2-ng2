package com.pearson.projectone.data.entity.customer.assess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQiResultArchive is a Querydsl query type for QiResultArchive
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQiResultArchive extends EntityPathBase<QiResultArchive> {

    private static final long serialVersionUID = -226428838L;

    public static final QQiResultArchive qiResultArchive = new QQiResultArchive("qiResultArchive");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

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

    public final StringPath qiAssessmentId = createString("qiAssessmentId");

    public final StringPath resultJson = createString("resultJson");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QQiResultArchive(String variable) {
        super(QiResultArchive.class, forVariable(variable));
    }

    public QQiResultArchive(Path<? extends QiResultArchive> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQiResultArchive(PathMetadata metadata) {
        super(QiResultArchive.class, metadata);
    }

}

