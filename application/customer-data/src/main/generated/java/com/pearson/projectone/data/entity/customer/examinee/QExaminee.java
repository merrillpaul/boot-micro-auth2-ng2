package com.pearson.projectone.data.entity.customer.examinee;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExaminee is a Querydsl query type for Examinee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExaminee extends EntityPathBase<Examinee> {

    private static final long serialVersionUID = -1250943977L;

    public static final QExaminee examinee = new QExaminee("examinee");

    public final com.pearson.projectone.core.support.data.QDocumentEntity _super = new com.pearson.projectone.core.support.data.QDocumentEntity(this);

    public final StringPath accountId = createString("accountId");

    public final BooleanPath archived = createBoolean("archived");

    public final StringPath comments = createString("comments");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateDeleted = _super.dateDeleted;

    public final DateTimePath<java.util.Date> dateOfLastAssessment = createDateTime("dateOfLastAssessment", java.util.Date.class);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final DateTimePath<java.util.Date> dob = createDateTime("dob", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath examineeId = createString("examineeId");

    public final StringPath firstName = createString("firstName");

    public final EnumPath<com.pearson.projectone.data.constants.Gender> gender = createEnum("gender", com.pearson.projectone.data.constants.Gender.class);

    public final StringPath hashedIdentifier = createString("hashedIdentifier");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastCreated = _super.lastCreated;

    public final StringPath lastName = createString("lastName");

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath middleName = createString("middleName");

    public final BooleanPath practiceMode = createBoolean("practiceMode");

    //inherited
    public final SimplePath<com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackerInfo> trackerInfo = _super.trackerInfo;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QExaminee(String variable) {
        super(Examinee.class, forVariable(variable));
    }

    public QExaminee(Path<? extends Examinee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExaminee(PathMetadata metadata) {
        super(Examinee.class, metadata);
    }

}

