package com.pearson.projectone.core.support.data.interceptor.trackchanges;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a domain class and/or fields to be eligible to
 * listen for changes. The mechanism depends on the underlying engine, as it is
 * different for hibernate, jpa, mongo or dynamo
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackChanges {
}
