package com.pearson.projectone.core.support.data.interceptor.trackchanges;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrackableEntity is a Querydsl query type for TrackableEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTrackableEntity extends BeanPath<TrackableEntity> {

    private static final long serialVersionUID = 1768124707L;

    public static final QTrackableEntity trackableEntity = new QTrackableEntity("trackableEntity");

    public final SimplePath<TrackerInfo> trackerInfo = createSimple("trackerInfo", TrackerInfo.class);

    public QTrackableEntity(String variable) {
        super(TrackableEntity.class, forVariable(variable));
    }

    public QTrackableEntity(Path<? extends TrackableEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrackableEntity(PathMetadata metadata) {
        super(TrackableEntity.class, metadata);
    }

}

