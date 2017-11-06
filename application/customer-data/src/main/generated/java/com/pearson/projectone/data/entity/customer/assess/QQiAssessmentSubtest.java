package com.pearson.projectone.data.entity.customer.assess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQiAssessmentSubtest is a Querydsl query type for QiAssessmentSubtest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQiAssessmentSubtest extends EntityPathBase<QiAssessmentSubtest> {

    private static final long serialVersionUID = -1735166555L;

    public static final QQiAssessmentSubtest qiAssessmentSubtest = new QQiAssessmentSubtest("qiAssessmentSubtest");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final StringPath assessSubtestId = createString("assessSubtestId");

    public final NumberPath<java.math.BigDecimal> completionTime = createNumber("completionTime", java.math.BigDecimal.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    public final DateTimePath<java.util.Date> dateWeSetStartTime = createDateTime("dateWeSetStartTime", java.util.Date.class);

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

    public final NumberPath<Integer> orderId = createNumber("orderId", Integer.class);

    public final StringPath qiAssessmentId = createString("qiAssessmentId");

    public final StringPath subtestInstanceId = createString("subtestInstanceId");

    public final StringPath subtestJson = createString("subtestJson");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<java.math.BigDecimal> usage = createNumber("usage", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final BooleanPath wasStarted = createBoolean("wasStarted");

    public QQiAssessmentSubtest(String variable) {
        super(QiAssessmentSubtest.class, forVariable(variable));
    }

    public QQiAssessmentSubtest(Path<? extends QiAssessmentSubtest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQiAssessmentSubtest(PathMetadata metadata) {
        super(QiAssessmentSubtest.class, metadata);
    }

}

