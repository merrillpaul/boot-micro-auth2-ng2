(function () {
	var json = JSON.parse(cat(pwd() + "/incremental/all/01_v1.0/01_base/examinee.json"));

	json.forEach(function (it) {
		print("Inserting " + it.firstName);
		db.Examinee.insert(it);
	});


})();