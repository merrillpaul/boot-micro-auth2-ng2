package com.pearson.projectone.core.support.dev.db;

import java.sql.Connection;

/**
 * Implementation classes to provide a way to clean up a db.
 * Typically , this is when we run the app in dev(local) mode and
 * we want to have a pristine database. For this we need to cleanse the db.
 */
public interface DbHelper {
	public void cleanup(Connection connection);
}
