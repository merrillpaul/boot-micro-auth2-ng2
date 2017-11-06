package com.pearson.projectone.core.support.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface for retrieving part , full or a if supported by DB
 * a pipeline of commands to retrieve part of the document.
 * All methods return Map
 */
public interface DatabaseJsonFieldRetriever<T> {

	/**
	 * Returns a part of the json document. If the json is
	 * {
	 *  "assessment": {
	 *      "name": "Some Name",
	 *      "id": "someid"
	 *      "subtests": [
	 *          {
	 *
	 *          }
	 *      ]
	 *  }
	 * }
	 * a projection to get the id would be `assessment.id` and for subtests would be `assessment.subtests`
	 * @param entity Any entity object
	 * @param projection A projection string to interrogate the json content
	 * @param <S>
	 * @param <O>
	 * @return
	 */
	<S extends DocumentEntity, O extends Serializable> Map<String, O> retrieveDocument(S entity, String projection);

	/**
	 * Retrieves whole or part based on the the ID of the entity
	 * @param id
	 * @param projection
	 * @param <O>
	 * @return
	 */
	<O extends Serializable> Map<String, O> retrieveDocument(String id, String projection);

	/**
	 * Uses commands to retrieve part of JSON if DB supports
	 * @param entity
	 * @param jsonCommands
	 * @param <S>
	 * @param <O>
	 * @return
	 */
	<S extends DocumentEntity, O extends Serializable> List<Map<String, O>> retrieveDocumentByCommands(S entity, String... jsonCommands);

	/**
	 * Uses entity ID to run json commands
	 * @param id
	 * @param jsonCommands
	 * @param <O>
	 * @return
	 */
	<O extends Serializable> List<Map<String, O>> retrieveDocumentByCommands(String id, String... jsonCommands);
}
