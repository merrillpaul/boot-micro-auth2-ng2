package com.pearson.projectone.data.entity.customer.assess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQiAssessmentSubtestData is a Querydsl query type for QiAssessmentSubtestData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQiAssessmentSubtestData extends EntityPathBase<QiAssessmentSubtestData> {

    private static final long serialVersionUID = -1861845265L;

    public static final QQiAssessmentSubtestData qiAssessmentSubtestData = new QQiAssessmentSubtestData("qiAssessmentSubtestData");

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

    public final StringPath key = createString("key");

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath qiAssessmentSubtestId = createString("qiAssessmentSubtestId");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath value = createString("value");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QQiAssessmentSubtestData(String variable) {
        super(QiAssessmentSubtestData.class, forVariable(variable));
    }

    public QQiAssessmentSubtestData(Path<? extends QiAssessmentSubtestData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQiAssessmentSubtestData(PathMetadata metadata) {
        super(QiAssessmentSubtestData.class, metadata);
    }

}

