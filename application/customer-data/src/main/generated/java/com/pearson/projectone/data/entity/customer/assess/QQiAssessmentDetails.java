package com.pearson.projectone.data.entity.customer.assess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQiAssessmentDetails is a Querydsl query type for QiAssessmentDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQiAssessmentDetails extends EntityPathBase<QiAssessmentDetails> {

    private static final long serialVersionUID = 1690141973L;

    public static final QQiAssessmentDetails qiAssessmentDetails = new QQiAssessmentDetails("qiAssessmentDetails");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final EnumPath<com.pearson.projectone.data.constants.AssessmentType> assessmentType = createEnum("assessmentType", com.pearson.projectone.data.constants.AssessmentType.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath examineeAssessmentId = createString("examineeAssessmentId");

    public final DateTimePath<java.util.Date> exportTime = createDateTime("exportTime", java.util.Date.class);

    public final StringPath externalApp = createString("externalApp");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final EnumPath<com.pearson.projectone.data.constants.AssessProgressState> progressState = createEnum("progressState", com.pearson.projectone.data.constants.AssessProgressState.class);

    public final StringPath resultsJson = createString("resultsJson");

    public final BooleanPath syncSucceeded = createBoolean("syncSucceeded");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QQiAssessmentDetails(String variable) {
        super(QiAssessmentDetails.class, forVariable(variable));
    }

    public QQiAssessmentDetails(Path<? extends QiAssessmentDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQiAssessmentDetails(PathMetadata metadata) {
        super(QiAssessmentDetails.class, metadata);
    }

}

