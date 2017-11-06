package com.pearson.projectone.data.entity.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBundleVariable is a Querydsl query type for BundleVariable
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBundleVariable extends BeanPath<BundleVariable> {

    private static final long serialVersionUID = 2098233493L;

    public static final QBundleVariable bundleVariable = new QBundleVariable("bundleVariable");

    public final StringPath dataType = createString("dataType");

    public final StringPath examineeAssessmentId = createString("examineeAssessmentId");

    public final StringPath name = createString("name");

    public final BooleanPath required = createBoolean("required");

    public final StringPath value = createString("value");

    public QBundleVariable(String variable) {
        super(BundleVariable.class, forVariable(variable));
    }

    public QBundleVariable(Path<? extends BundleVariable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBundleVariable(PathMetadata metadata) {
        super(BundleVariable.class, metadata);
    }

}

