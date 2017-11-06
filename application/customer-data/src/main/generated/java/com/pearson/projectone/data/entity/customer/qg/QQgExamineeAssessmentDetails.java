package com.pearson.projectone.data.entity.customer.qg;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQgExamineeAssessmentDetails is a Querydsl query type for QgExamineeAssessmentDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQgExamineeAssessmentDetails extends EntityPathBase<QgExamineeAssessmentDetails> {

    private static final long serialVersionUID = 432066629L;

    public static final QQgExamineeAssessmentDetails qgExamineeAssessmentDetails = new QQgExamineeAssessmentDetails("qgExamineeAssessmentDetails");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final StringPath batchJobId = createString("batchJobId");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath diagnosisPending = createString("diagnosisPending");

    public final StringPath examineeAssessmentId = createString("examineeAssessmentId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final BooleanPath researchOptIn = createBoolean("researchOptIn");

    public final StringPath responseJsonParam = createString("responseJsonParam");

    public final DateTimePath<java.util.Date> rosaStartDate = createDateTime("rosaStartDate", java.util.Date.class);

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QQgExamineeAssessmentDetails(String variable) {
        super(QgExamineeAssessmentDetails.class, forVariable(variable));
    }

    public QQgExamineeAssessmentDetails(Path<? extends QgExamineeAssessmentDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQgExamineeAssessmentDetails(PathMetadata metadata) {
        super(QgExamineeAssessmentDetails.class, metadata);
    }

}

