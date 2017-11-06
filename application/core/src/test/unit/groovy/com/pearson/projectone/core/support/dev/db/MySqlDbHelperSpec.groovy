package com.pearson.projectone.core.support.dev.db

import com.pearson.projectone.core.support.dev.db.impl.MySqlDbHelper
import org.springframework.stereotype.Component
import spock.lang.Specification

import java.sql.Connection
import java.sql.DatabaseMetaData
import java.sql.ResultSet
import java.sql.Statement

class MySqlDbHelperSpec extends Specification {

	def "should have component annotation"() {
		expect:
		MySqlDbHelper.getAnnotation(Component).value() == 'mysqlHelper'
	}


	def "should batch drop tables with cascaded constraints"() {
		given:
		def resultCloseCalled = false
		def tables = [
				'TABLE1',
				'TABLE2',
				'TABLE3'
		].iterator()
		def batches = []
		def currentTable = ''
		def resultSet = Stub(ResultSet) {
			next() >> {
				if (tables.hasNext()) {
					currentTable = tables.next()
					return true
				} else {
					return false
				}
			}

			getString(_) >> {
				currentTable
			}

			close() >> {
				resultCloseCalled = true
			}
		}
		def statement = Stub(Statement) {
			addBatch(_) >> { args ->
				batches << args[0]
			}
		}

		def metadata = Stub(DatabaseMetaData) {
			getTables(_, _, _, _) >> {
				resultSet
			}
		}
		def connection = Stub(Connection) {

			createStatement() >> {
				statement
			}

			getMetaData() >> {
				metadata
			}
		}

		def helper = new MySqlDbHelper()

		when:
		helper.cleanup(connection)

		then:
		resultCloseCalled
		batches == [
				"SET FOREIGN_KEY_CHECKS=0",
				"drop table TABLE1",
				"drop table TABLE2",
				"drop table TABLE3",
				"SET FOREIGN_KEY_CHECKS=1"
		]
	}

}
