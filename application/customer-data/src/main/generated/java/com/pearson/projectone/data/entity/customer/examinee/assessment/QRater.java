package com.pearson.projectone.data.entity.customer.examinee.assessment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRater is a Querydsl query type for Rater
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QRater extends BeanPath<Rater> {

    private static final long serialVersionUID = 836577561L;

    public static final QRater rater = new QRater("rater");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public QRater(String variable) {
        super(Rater.class, forVariable(variable));
    }

    public QRater(Path<? extends Rater> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRater(PathMetadata metadata) {
        super(Rater.class, metadata);
    }

}

