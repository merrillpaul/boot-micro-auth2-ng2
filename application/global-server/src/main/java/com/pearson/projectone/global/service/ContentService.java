package com.pearson.projectone.global.service;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.data.dao.global.library.content.ContentVersionDao;
import com.pearson.projectone.data.entity.global.library.content.ContentType;
import com.pearson.projectone.global.dto.content.ContentTypesDTO;
import com.pearson.projectone.global.dto.content.ContentVersionDTO;
import com.pearson.projectone.global.dto.content.CreateContentTypeCommandDTO;
import com.pearson.projectone.global.dto.content.CreateNewVersionResultDTO;
import com.pearson.projectone.global.dto.content.GiveAllZipDTO;
import com.pearson.projectone.global.dto.content.QueryVersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ContentService {

	@Autowired
	private ContentVersionDao contentVersionDao;

	/**
	 * Gets information about the latest version of the Give zip file that doesn't have an interface manifest
	 *
	 * @param branch - the Give branch name
	 * @param config - the Give configuration name
	 * @return map with keys: file, hash
	 */
	public GiveAllZipDTO getAllGiveZipFile(String branch, String config) {
		// TODO impl
		return null;
	}

	/**
	 * Gets a File object representing the specified version
	 *
	 * @param versionId The ID of the version to get
	 * @return A File
	 */
	public File getFileByVersionId(String versionId) {
		// TODO impl
		return null;
	}

	/**
	 * Returns information about the current versions of all content types in the system
	 *
	 * @param user             - The business entity to fetch versions for
	 * @param query            versions - Map describing content types that the client has. Keys are type names
	 *                         and the value corresponding to each key is the hash of the version of that type that the client has.
	 * @param branch           - The branch the client was built on
	 * @param config           - The configuration the client was built for
	 * @param providedManifest - A JSON document describing the interface that the client's platform
	 *                         provides to content.
	 * @return - List of maps describing the status of each version.
	 */
	public List<QueryVersionDTO> queryAllVersions(AppUserDetails user, String query, String branch, String config,
	                                              String providedManifest) {
		// TODO impl
		return null;
	}

	/**
	 * Returns information about the current versions of the specified content type.
	 *
	 * @param contentType      - The name of the content type to query
	 * @param branch           - The branch the client was built on
	 * @param config           - The configuration the client was built for
	 * @param providedManifest - A JSON document describing the interface that the client's platform
	 *                         provides to content.
	 * @param currentHash      - The hash of the client's current copy of the requested content type
	 * @return - Map describing the status of the latest version.
	 */
	public QueryVersionDTO queryVersion(String contentType, String branch, String config,
	                                    String providedManifest, String currentHash) {
		// TODO impl
		return null;
	}


	public List<String> queryLatestVersionsForTransfer(String branch, String config) {
		// TODO impl
		return null;
	}

	/**
	 * Creates a new version of the file
	 *
	 * @param contentTypeName - The name of the content type to create a new version for
	 * @param branch          - The branch the client was built on
	 * @param config          - The configuration the client was built for
	 * @param uploadedFile    - The file that was uploaded
	 * @return - map with keys: [success: true if the file and GiveContentVersion were saved successfully,
	 * message: suggested flash messages,
	 * errors: user friendly formatted errors to be used as flash errors]
	 */
	public CreateNewVersionResultDTO createNewVersion(String contentTypeName, String branch, String config,
	                                                  MultipartFile uploadedFile) {
		// TODO impl
		return null;
	}

	public ContentType createNewContentType(CreateContentTypeCommandDTO createContentTypeCommand) {
		// TODO impl
		return null;
	}


	public Page<ContentVersionDTO> listContent(PageableSearchRequestDTO searchRequest) {
		// TODO impl
		return null;
	}

	public void cleanup(String id) {
		// TODO impl
	}


	public List<ContentTypesDTO> getContentTypes() {
		// TODO impl
		return null;
	}
}
