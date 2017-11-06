var createDBMainTables = function () {
	db.createCollection("DBMaintain");
};

var getPreviousFailed = function () {
	var files = [];
	db.DBMaintain.find({succeeded: false}).forEach(function (it) {
		files.push(it.fileName);
	});
	return files;
};

var checkAndRun = function (file, md5, mode) {
	var nameSuffix = file.split(baseDir + "/incremental/")[1],
			dbObj, existingFile

	existingFile = db.DBMaintain.findOne({fileName: nameSuffix});
	if (existingFile != null && existingFile.checksum !== md5 && existingFile.succeeded === true) {
		throw "The already executed file " + nameSuffix + " has been changed. This is not ideal. " +
		"You would need to create another incremental script";
	}

	if (existingFile == null) {

		dbObj = {
			fileName: nameSuffix,
			executedAt: Date.now(),
			checksum: md5,
			lastModified: Date.now(),
			succeeded: false
		};


		db.DBMaintain.insert(dbObj);
		try {
			print("running file " + nameSuffix);
			load(file);
			db.DBMaintain.updateOne({"fileName": nameSuffix},
					{$set: {"succeeded": true}}
			);
		} catch (e) {
			print(e);
			throw e;
		}
	}

};

var runIncrementals = function (files) {
	files.forEach(function (file) {
		checkAndRun(file.name, file.md5, "incremental");
	});
};

var runRepeatables = function (files) {
	files.forEach(function (file) {
		checkAndRun(file.name, file.md5, "repeatable");
	});
};

var run = function () {
	var prevFailed = [];
	createDBMainTables();
	print("Running Mongo DBMaintain");

	prevFailed = getPreviousFailed();
	if (prevFailed.length > 0) {
		print("There are failed scripts. Fix them manually or run markup2date");
		printjson(prevFailed);
		throw "There are failed repeatables. Fix them manually or run markup2date ";
	}

	runIncrementals(incrementals);
	runRepeatables(repeatables);

};

run();