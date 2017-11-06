package com.pearson.projectone.data.entity.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExamineeDetails is a Querydsl query type for ExamineeDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamineeDetails extends EntityPathBase<ExamineeDetails> {

    private static final long serialVersionUID = -1593182233L;

    public static final QExamineeDetails examineeDetails = new QExamineeDetails("examineeDetails");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final BooleanPath diagnosisPending = createBoolean("diagnosisPending");

    public final StringPath examineeId = createString("examineeId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final BooleanPath researchOptIn = createBoolean("researchOptIn");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExamineeDetails(String variable) {
        super(ExamineeDetails.class, forVariable(variable));
    }

    public QExamineeDetails(Path<? extends ExamineeDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExamineeDetails(PathMetadata metadata) {
        super(ExamineeDetails.class, metadata);
    }

}

