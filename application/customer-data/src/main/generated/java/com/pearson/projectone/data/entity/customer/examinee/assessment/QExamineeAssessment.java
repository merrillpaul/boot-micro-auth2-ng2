package com.pearson.projectone.data.entity.customer.examinee.assessment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExamineeAssessment is a Querydsl query type for ExamineeAssessment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeAssessment extends EntityPathBase<ExamineeAssessment> {

    private static final long serialVersionUID = -1374747425L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExamineeAssessment examineeAssessment = new QExamineeAssessment("examineeAssessment");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final StringPath accountId = createString("accountId");

    public final DateTimePath<java.util.Date> adminstrationDate = createDateTime("adminstrationDate", java.util.Date.class);

    public final com.pearson.projectone.data.entity.customer.QBundleVariable bundleVariables;

    public final BooleanPath cloned = createBoolean("cloned");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath deliveryTypeId = createString("deliveryTypeId");

    public final StringPath examineeId = createString("examineeId");

    public final StringPath examinerId = createString("examinerId");

    public final StringPath formId = createString("formId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath parentAssessmentId = createString("parentAssessmentId");

    public final BooleanPath practiceMode = createBoolean("practiceMode");

    public final QRater rater;

    public final StringPath status = createString("status");

    public final ListPath<String, StringPath> subtests = this.<String, StringPath>createList("subtests", String.class, StringPath.class, PathInits.DIRECT2);

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExamineeAssessment(String variable) {
        this(ExamineeAssessment.class, forVariable(variable), INITS);
    }

    public QExamineeAssessment(Path<? extends ExamineeAssessment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamineeAssessment(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExamineeAssessment(PathMetadata metadata, PathInits inits) {
        this(ExamineeAssessment.class, metadata, inits);
    }

    public QExamineeAssessment(Class<? extends ExamineeAssessment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bundleVariables = inits.isInitialized("bundleVariables") ? new com.pearson.projectone.data.entity.customer.QBundleVariable(forProperty("bundleVariables")) : null;
        this.rater = inits.isInitialized("rater") ? new QRater(forProperty("rater")) : null;
    }

}

