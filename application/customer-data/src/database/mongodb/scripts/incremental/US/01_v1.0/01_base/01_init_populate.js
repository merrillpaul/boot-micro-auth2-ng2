(function () {
	var csv = (cat(pwd() + "/incremental/US/01_v1.0/01_base/examinee.csv"));

	csv.split("\n").forEach(function (line) {
		var fields = line.split(",");
		db.Examinee.insert({firstName: fields[0], lastName: fields[1], gender: fields[2]});
	})

})();