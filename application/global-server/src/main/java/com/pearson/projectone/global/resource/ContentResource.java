package com.pearson.projectone.global.resource;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.service.user.CurrentUserService;
import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.core.utils.RangedDownload;
import com.pearson.projectone.data.entity.global.library.content.ContentType;
import com.pearson.projectone.global.dto.content.ContentTypesDTO;
import com.pearson.projectone.global.dto.content.ContentVersionDTO;
import com.pearson.projectone.global.dto.content.CreateContentTypeCommandDTO;
import com.pearson.projectone.global.dto.content.CreateNewVersionResultDTO;
import com.pearson.projectone.global.dto.content.GiveAllZipDTO;
import com.pearson.projectone.global.service.ContentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This mimics the content controller from QI which the Ipad uses
 */
@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class ContentResource {

	@Autowired
	private ContentService contentService;

	@Autowired
	private CurrentUserService currentUserService;

	@GetMapping("giveAll")
	@PreAuthorize("hasAuthority('ROLE_CLINICIAN')")
	public void giveAll(@RequestParam(required = false) String branch,
	                    @RequestParam(required = false) String config,
	                    @RequestParam(required = false) String hash,
	                    HttpServletResponse response, HttpServletRequest request) throws IOException {
		branch = ObjectUtils.defaultIfNull(branch, "master");
		config = ObjectUtils.defaultIfNull(config, "dev");
		GiveAllZipDTO dto = contentService.getAllGiveZipFile(branch, config);
		if (dto == null || dto.getFile() == null || !dto.getFile().exists()) {
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "File not found");
		} else if (hash != null && hash == dto.getHash()) {
			response.sendError(HttpStatus.NOT_MODIFIED.value());
		} else {
			// proceed to download
			renderTarFile(dto.getFile(), request, response);
		}

	}

	@GetMapping("getVersion")
	@PreAuthorize("hasAuthority('ROLE_CLINICIAN')")
	public void getVersion(@RequestParam String id, HttpServletResponse response, HttpServletRequest request) throws IOException {
		renderTarFile(contentService.getFileByVersionId(id), request, response);
	}

	@PostMapping("queryVersion")
	@PreAuthorize("hasAuthority('ROLE_CLINICIAN')")
	public ResponseEntity queryVersion(
			@RequestParam(required = false) String query,
			@RequestParam(required = false) String branch,
			@RequestParam(required = false) String config,
			@RequestParam(required = false) String hash,
			@RequestParam(required = false) String interfaceManifest,
			@RequestParam(required = false) String fileType) {

		ResponseEntity responseEntity = null;

		if (!StringUtils.isEmpty(query)) {
			AppUserDetails userDetails = currentUserService.getCurrentUser();
			responseEntity = new ResponseEntity(contentService.queryAllVersions(
					userDetails, query, branch, config, interfaceManifest
			), HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity(contentService.queryVersion(fileType, branch, config, interfaceManifest, hash
			), HttpStatus.OK);
		}
		return responseEntity;

	}

	@PostMapping("queryLatestVersionsForTransfer")
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	@ResponseStatus(HttpStatus.OK)
	public List<String> queryLatestVersionsForTransfer(
			@RequestParam String branch,
			@RequestParam String config) {
		return contentService.queryLatestVersionsForTransfer(branch, config);
	}


	@PostMapping("giveBuildUpload")
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	@ResponseStatus(HttpStatus.OK)
	public void giveBuildUpload(
			@RequestParam("file") MultipartFile file,
			@RequestParam(required = false) String giveContentType,
			@RequestParam String branch,
			@RequestParam String config) {
		if (StringUtils.isEmpty(giveContentType)) {
			giveContentType = "all";
		}
		// TODO handle errors
		CreateNewVersionResultDTO result = contentService.createNewVersion(giveContentType, branch, config, file);
	}

	@PostMapping("createContentTypes")
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	@ResponseStatus(HttpStatus.OK)
	public void createContentTypes(
			@RequestParam String contentTypes) {
		// TODO impl
	}

	@GetMapping("contentTypes")
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	@ResponseStatus(HttpStatus.OK)
	public List<ContentTypesDTO> contentTypes() {
		return contentService.getContentTypes();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	@ResponseStatus(HttpStatus.OK)
	public void uploadContent(
			@RequestParam("file") MultipartFile file,
			@RequestParam(required = false) String giveContentType,
			@RequestParam String branch,
			@RequestParam String config) {
		if (StringUtils.isEmpty(giveContentType)) {
			giveContentType = "all";
		}
		// TODO handle errors
		CreateNewVersionResultDTO result = contentService.createNewVersion(giveContentType, branch, config, file);
	}

	@PutMapping("contentType")
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	public ResponseEntity<BaseDTO> createContentType(@RequestBody CreateContentTypeCommandDTO createContentTypeCommand) {
		// TODO handle errors
		ContentType result = contentService.createNewContentType(createContentTypeCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(result.getId(), result.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping("list")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	public Page<ContentVersionDTO> listAssessments(@RequestBody PageableSearchRequestDTO searchRequest) {
		return contentService.listContent(searchRequest);
	}

	@PostMapping("{id}/cleanup")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CONTENT_UPLOAD')")
	public void cleanup(@PathVariable String id) {
		contentService.cleanup(id);
	}

	private void renderTarFile(File file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		RangedDownload.sendFile(file, "application/x-tar", request, response);
	}

}
