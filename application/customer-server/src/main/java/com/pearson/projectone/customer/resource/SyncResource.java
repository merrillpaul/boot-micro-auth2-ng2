package com.pearson.projectone.customer.resource;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.service.user.CurrentUserService;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.customer.dto.sync.CheckAuthDTO;
import com.pearson.projectone.customer.dto.sync.GetReadyResultDTO;
import com.pearson.projectone.customer.dto.sync.SyncBatteryResultDTO;
import com.pearson.projectone.customer.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * Used exclusively from iPad/Assess
 */
@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYNC')")
public class SyncResource {

	@Autowired
	private SyncService syncService;

	@Autowired
	private CurrentUserService currentUserService;

	/**
	 * Informs the BE that an assessment has been successfully downloaded and changes the status.
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("assessmentDownloadSucceeded")
	public ResponseEntity markAssessmentDownloaded(@RequestParam("id") String id) {
		if (syncService.markAssessmentDownload(id)) {
			return ok("success");
		} else {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		}
	}

	@GetMapping("getReady")
	@ResponseStatus(HttpStatus.OK)
	public GetReadyResultDTO getReady(@RequestParam("platformVersion") String platformVersion) {

		return prepareSyncAssessments(platformVersion);
	}

	/**
	 * @param platformVersion
	 * @param json            with
	 *                        <code>
	 *                        {
	 *                        assessmentIds:  [
	 *                        '1993f5309a2d4cbdb7f8f2d700d332d8',
	 *                        '57b26b6d0b0c4477867509c2b0a55b2c',
	 *                        '86f250402a4945bfa5ed7a39677f06c7'
	 *                        ]
	 *                        }
	 *                        </code>
	 * @return
	 */
	@PostMapping("getReady")
	@ResponseStatus(HttpStatus.OK)
	public GetReadyResultDTO getReadyPost(@RequestParam("platformVersion") String platformVersion,
	                                      @RequestParam("json") String json) {
		GetReadyResultDTO resultDTO = getReady(platformVersion);
		resultDTO.setDeletedList(syncService.getDeletedList(json));
		return resultDTO;
	}

	@GetMapping("checkAuth")
	@ResponseStatus(HttpStatus.OK)
	public CheckAuthDTO checkAuth() {
		return syncService.getCheckAuthResponse();
	}

	@PostMapping("uploadFile")
	public ResponseEntity uploadFile(@RequestParam("type") String type, @RequestParam("fileName") String fileName,
	                                 @RequestParam("assessmentId") String assessmentId,
	                                 @RequestParam("data") MultipartFile file) {
		if (syncService.storeFileFromAssess(type, fileName, assessmentId, file)) {
			return ok("success");
		} else {
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		}
	}


	@PostMapping("syncBatteryData")
	public ResponseEntity syncBatteryData(@RequestParam("json") MultipartFile file) {
		SyncBatteryResultDTO result = syncService.syncBatteryData(file);
		return ok(result);
	}

	@PostMapping("returnControlToShare")
	public ResponseEntity returnControlToShare(@RequestParam("json") MultipartFile file) {
		syncService.markControlToCentral(file);
		return ok("success");
	}

	@PostMapping("syncPerformanceLog")
	public ResponseEntity syncPerformanceLog(@RequestParam("text") String text, @RequestParam("filename") String fileName,
	                                         @RequestParam("devicePlatform") String devicePlatform,
	                                         @RequestParam("deviceModel") String deviceModel) {
		AppUserDetails currentUser = currentUserService.getCurrentUser();
		syncService.syncPerformanceLog(currentUser, text, fileName, devicePlatform, deviceModel);
		return ok("success");
	}

	@PostMapping("errorlogData")
	public ResponseEntity errorlogData(@RequestParam("json") String json) {
		syncService.syncErrorLogFromAssess(json);
		return ok("success");
	}

	@PostMapping("syncLoginTimes")
	public ResponseEntity syncLoginTimes(@RequestParam("json") String json) {
		syncService.syncLoginTimes(json);
		return ok("success");
	}

	private GetReadyResultDTO prepareSyncAssessments(String platformVersion) {
		AppUserDetails currentUser = currentUserService.getCurrentUser();
		return syncService.getAssessmentsToSync(platformVersion, currentUser);
	}
}
