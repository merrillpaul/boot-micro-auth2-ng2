package com.pearson.projectone.customer.service;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.customer.dto.sync.CheckAuthDTO;
import com.pearson.projectone.customer.dto.sync.GetReadyResultDTO;
import com.pearson.projectone.customer.dto.sync.SyncBatteryResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SyncService {

	@Autowired
	private JsonParser jsonParser;

	public boolean markAssessmentDownload(String assessmentId) {
		// TODO
		return false;
	}


	public GetReadyResultDTO getAssessmentsToSync(String platformVersion, AppUserDetails user) {
		// TODO impl
		return null;
	}

	/**
	 * Allow Assess to find out if any of the Assessment Id's it has refer to any that have been
	 * deleted.  This basically takes the list it is given, and returns the same list, sans the
	 * not-deleted ones.
	 * The format is:
	 * [
	 * '1993f5309a2d4cbdb7f8f2d700d332d8',
	 * '57b26b6d0b0c4477867509c2b0a55b2c',
	 * '86f250402a4945bfa5ed7a39677f06c7'
	 * ]
	 * <p>
	 * If the first one has been deleted, then the result is:
	 * [
	 * '1993f5309a2d4cbdb7f8f2d700d332d8'
	 * ]
	 *
	 * @param jsonString
	 * @return
	 */
	public List<String> getDeletedList(String jsonString) {
		List ids = jsonParser.parseList(jsonString);
		// TODO impl
		return null;
	}


	/**
	 * Auth information with subtest guids needed for iPad
	 *
	 * @return
	 */
	public CheckAuthDTO getCheckAuthResponse() {
		// TODO impl
		return null;
	}


	/**
	 * Store a file received from Assess (also known as 'Give')
	 *
	 * @param type
	 * @param fileName
	 * @param assessmentId
	 * @param file
	 * @return boolean
	 */
	public boolean storeFileFromAssess(String type, String fileName, String assessmentId, MultipartFile file) {
		// TODO impl
		return true;
	}


	/**
	 * Sync battery
	 *
	 * @param file
	 */
	public void markControlToCentral(MultipartFile file) {
		// TODO impl
	}

	public SyncBatteryResultDTO syncBatteryData(MultipartFile file) {
		// TODO impl
		return null;
	}

	public void syncPerformanceLog(AppUserDetails currentUser, String text, String fileName, String devicePlatform,
	                               String deviceModel) {
		// TODO impl
	}


	public void syncErrorLogFromAssess(String errorJson) {
		// TODO impl
	}

	public void syncLoginTimes(String loginJson) {
		// TODO impl
	}
}
