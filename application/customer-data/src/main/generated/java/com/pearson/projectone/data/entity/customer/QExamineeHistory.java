package com.pearson.projectone.data.entity.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExamineeHistory is a Querydsl query type for ExamineeHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeHistory extends EntityPathBase<ExamineeHistory> {

    private static final long serialVersionUID = 2070997561L;

    public static final QExamineeHistory examineeHistory = new QExamineeHistory("examineeHistory");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath examineeId = createString("examineeId");

    public final StringPath historyJson = createString("historyJson");

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

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExamineeHistory(String variable) {
        super(ExamineeHistory.class, forVariable(variable));
    }

    public QExamineeHistory(Path<? extends ExamineeHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExamineeHistory(PathMetadata metadata) {
        super(ExamineeHistory.class, metadata);
    }

}

