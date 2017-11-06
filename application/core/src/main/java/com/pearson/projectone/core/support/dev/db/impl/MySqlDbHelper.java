package com.pearson.projectone.core.support.dev.db.impl;


import com.pearson.projectone.core.support.dev.db.DbHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementation for MySQL DB for cleanup
 */
@Component("mysqlHelper")
public class MySqlDbHelper implements DbHelper {

	private static final Log logger = LogFactory.getLog(MySqlDbHelper.class);

	@Override
	public void cleanup(Connection connection) {

		Statement statement = null;

		try {
			statement = connection.createStatement();
			statement.addBatch("SET FOREIGN_KEY_CHECKS=0");

			// clean all tables
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while (rs.next()) {
				String tableName = rs.getString(3);
				logger.info("drop table " + tableName);
				statement.addBatch("drop table " + tableName);
			}
			rs.close();
			statement.addBatch("SET FOREIGN_KEY_CHECKS=1");
			statement.executeBatch();
		} catch (SQLException sqe) {
			logger.error("error in mysqlhelper dropping tables", sqe);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("exception in statement close", e);
				}
			}
		}
	}
}
