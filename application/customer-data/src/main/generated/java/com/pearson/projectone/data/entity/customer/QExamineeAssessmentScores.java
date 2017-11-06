package com.pearson.projectone.data.entity.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExamineeAssessmentScores is a Querydsl query type for ExamineeAssessmentScores
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeAssessmentScores extends EntityPathBase<ExamineeAssessmentScores> {

    private static final long serialVersionUID = 869245310L;

    public static final QExamineeAssessmentScores examineeAssessmentScores = new QExamineeAssessmentScores("examineeAssessmentScores");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

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

    public final StringPath reportId = createString("reportId");

    public final StringPath reportOptionsId = createString("reportOptionsId");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExamineeAssessmentScores(String variable) {
        super(ExamineeAssessmentScores.class, forVariable(variable));
    }

    public QExamineeAssessmentScores(Path<? extends ExamineeAssessmentScores> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExamineeAssessmentScores(PathMetadata metadata) {
        super(ExamineeAssessmentScores.class, metadata);
    }

}

