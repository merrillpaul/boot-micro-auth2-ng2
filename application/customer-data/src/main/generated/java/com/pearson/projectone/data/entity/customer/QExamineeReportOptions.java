package com.pearson.projectone.data.entity.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExamineeReportOptions is a Querydsl query type for ExamineeReportOptions
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeReportOptions extends EntityPathBase<ExamineeReportOptions> {

    private static final long serialVersionUID = -1282163537L;

    public static final QExamineeReportOptions examineeReportOptions = new QExamineeReportOptions("examineeReportOptions");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final StringPath buReportId = createString("buReportId");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath examineeAssessmentScoreId = createString("examineeAssessmentScoreId");

    public final StringPath examineeId = createString("examineeId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath reportOptions = createString("reportOptions");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExamineeReportOptions(String variable) {
        super(ExamineeReportOptions.class, forVariable(variable));
    }

    public QExamineeReportOptions(Path<? extends ExamineeReportOptions> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExamineeReportOptions(PathMetadata metadata) {
        super(ExamineeReportOptions.class, metadata);
    }

}

