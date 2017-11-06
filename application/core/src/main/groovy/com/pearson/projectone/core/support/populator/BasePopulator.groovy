package com.pearson.projectone.core.support.populator

/**
 * An interface whose implementations will be used to populate
 * the database when in dev mode
 */
interface BasePopulator {
	void populate()

	static public final def DEVXML = new XmlParser().parseText(new File('../../tools/development/data/dev-bootstrap.xml').text)

}