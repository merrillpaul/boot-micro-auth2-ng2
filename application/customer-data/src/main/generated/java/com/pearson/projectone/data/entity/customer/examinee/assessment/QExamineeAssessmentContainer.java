package com.pearson.projectone.data.entity.customer.examinee.assessment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExamineeAssessmentContainer is a Querydsl query type for ExamineeAssessmentContainer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeAssessmentContainer extends EntityPathBase<ExamineeAssessmentContainer> {

    private static final long serialVersionUID = 58880610L;

    public static final QExamineeAssessmentContainer examineeAssessmentContainer = new QExamineeAssessmentContainer("examineeAssessmentContainer");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final MapPath<String, java.util.List<String>, SimplePath<java.util.List<String>>> examineeAssessmentsMap = this.<String, java.util.List<String>, SimplePath<java.util.List<String>>>createMap("examineeAssessmentsMap", String.class, java.util.List.class, SimplePath.class);

    public final StringPath examineeId = createString("examineeId");

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

    public QExamineeAssessmentContainer(String variable) {
        super(ExamineeAssessmentContainer.class, forVariable(variable));
    }

    public QExamineeAssessmentContainer(Path<? extends ExamineeAssessmentContainer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExamineeAssessmentContainer(PathMetadata metadata) {
        super(ExamineeAssessmentContainer.class, metadata);
    }

}

