package com.pearson.projectone.data.entity.customer.examiner;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExaminer is a Querydsl query type for Examiner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExaminer extends EntityPathBase<Examiner> {

    private static final long serialVersionUID = 489347767L;

    public static final QExaminer examiner = new QExaminer("examiner");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final StringPath examinerId = createString("examinerId");

    public final StringPath firstName = createString("firstName");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    public final StringPath lastName = createString("lastName");

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath middleName = createString("middleName");

    public final StringPath suffix = createString("suffix");

    public final StringPath title = createString("title");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExaminer(String variable) {
        super(Examiner.class, forVariable(variable));
    }

    public QExaminer(Path<? extends Examiner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExaminer(PathMetadata metadata) {
        super(Examiner.class, metadata);
    }

}

