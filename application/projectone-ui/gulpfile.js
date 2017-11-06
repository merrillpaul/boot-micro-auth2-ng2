'use strict';

let plugins = require('gulp-load-plugins');
let gulp = require('gulp');
let ghPages = require('gulp-gh-pages');
let rimraf = require('rimraf');

// Load all Gulp plugins into one variable
plugins();

// Delete the "dist" folder
// This happens every time a build starts
function clean(done) {
  rimraf('./dist/', done);
} 

gulp.task('publish',
  gulp.series(publish, clean));

function publish() {
  return gulp.src('./dist/**/*')
    .pipe(ghPages({
        remoteUrl: 'https://github.com/pearca/P1UI'
    }));
};