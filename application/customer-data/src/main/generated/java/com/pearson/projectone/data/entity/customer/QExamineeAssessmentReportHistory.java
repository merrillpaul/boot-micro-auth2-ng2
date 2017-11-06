package com.pearson.projectone.data.entity.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExamineeAssessmentReportHistory is a Querydsl query type for ExamineeAssessmentReportHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeAssessmentReportHistory extends EntityPathBase<ExamineeAssessmentReportHistory> {

    private static final long serialVersionUID = -702987069L;

    public static final QExamineeAssessmentReportHistory examineeAssessmentReportHistory = new QExamineeAssessmentReportHistory("examineeAssessmentReportHistory");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final StringPath buReportId = createString("buReportId");

    public final StringPath charged = createString("charged");

    public final StringPath childExamineeAssessmentId = createString("childExamineeAssessmentId");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath examineeAssessmentId = createString("examineeAssessmentId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath reportProductCode = createString("reportProductCode");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExamineeAssessmentReportHistory(String variable) {
        super(ExamineeAssessmentReportHistory.class, forVariable(variable));
    }

    public QExamineeAssessmentReportHistory(Path<? extends ExamineeAssessmentReportHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExamineeAssessmentReportHistory(PathMetadata metadata) {
        super(ExamineeAssessmentReportHistory.class, metadata);
    }

}

