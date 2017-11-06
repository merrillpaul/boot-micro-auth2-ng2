
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;


/**
 * The Class FormOsaMapView.
 */
@JsonPropertyOrder({
		"id",
		"osaFormMapXml",
		"guid"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = FormOsaMapView.class
		, resolver = ObjectResolver.class)
public class FormOsaMapView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The osa form map xml.
	 */
	@JsonProperty("osaFormMapXml")
	private String osaFormMapXml;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;


	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id The id
	 */
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the osa form map xml.
	 *
	 * @return The osaFormMapXml
	 */
	@JsonProperty("osaFormMapXml")
	public String getOsaFormMapXml() {
		return osaFormMapXml;
	}

	/**
	 * Sets the osa form map xml.
	 *
	 * @param osaFormMapXml The osaFormMapXml
	 */
	@JsonProperty("osaFormMapXml")
	public void setOsaFormMapXml(String osaFormMapXml) {
		this.osaFormMapXml = osaFormMapXml;
	}

	/**
	 * Gets the guid.
	 *
	 * @return The guid
	 */
	@JsonProperty("guid")
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid The guid
	 */
	@JsonProperty("guid")
	public void setGuid(String guid) {
		this.guid = guid;
	}


}
