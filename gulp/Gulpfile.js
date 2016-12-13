/**
 * Created by zhuxh on 15-12-2.
 */

'use strict';

/*
gulp.task(name, fn) - 定义任务，第一个参数是任务名，第二个参数是任务内容
gulp.src(path) - 选择文件，传入参数是文件路径
gulp.dest(path) - 输出文件
gulp.pipe() - 管道，你可以暂时将 pipe 理解为将操作加入执行队列

var uglify = require("gulp-uglify");  // js混淆压缩模块
var minify_css = require("gulp-minify-css");  // css混淆压缩模块
var minify_html = require("gulp-minify-html");  // html混淆压缩模块
var gulp_concat = require('gulp-concat');  // 文件合并模块
var rename = require('gulp-rename');  // 文件重命名模板

====
插件
====

----
# gulp-minify-html
----
 Options

 All options are false by default.

 empty - do not remove empty attributes
 cdata - do not strip CDATA from scripts
 comments - do not remove comments
 conditionals - do not remove conditional internet explorer comments
 spare - do not remove redundant attributes
 quotes - do not remove arbitrary quotes
 loose - preserve one whitespace

var minifyHTML = require('gulp-minify-html');
gulp.task('minify-html', function() {
    var opts = {
        conditionals: true,
        spare:true
    };
    return gulp.src('./static/html/*.html')
        .pipe(minifyHTML(opts))
        .pipe(gulp.dest('./dist/'));
});

----
# gulp-minify-css
----

var gulp = require('gulp');
var minifyCss = require('gulp-minify-css');
gulp.task('minify-css', function() {
    return gulp.src('styles/*.css')
        .pipe(minifyCss({compatibility: 'ie8'}))
        .pipe(gulp.dest('dist'));
});

*/

var gulp = require('gulp'),
    plug = require('gulp-load-plugins')(),
    config = require('./config');

var pkg_name = config.name,
    theme_src = config.theme.src,
    theme_dest = config.theme.dest

// js文件压缩配置
var uglify_option = {
    mangle: false, // 类型：Boolean 默认：true 是否修改变量名
    compress: false //类型：Boolean 默认：true 是否完全压缩
};

// html文件压缩配置
var minifyHtml_option = {
    empty: true,  // KEEP empty attributes
    cdata: false,  // KEEP CDATA from scripts
    comments: false,  // KEEP comments
    ssi: false,  // KEEP Server Side Includes
    conditionals: false,  // KEEP conditional internet explorer comments
    spare: true,  // KEEP redundant attributes
    quotes: true,  // KEEP arbitrary quotes
    loose: false  // KEEP one whitespace
};

gulp.task('default', function () {
    console.log('toe_admin default');
    console.log(config);
});

// 验证js文件是否符合jshint
gulp.task('jshint', function () {
    gulp.src(config.js.js_hint)
    // asi => 值为false时，如果代码末尾省略了分号，则JSHint会给出警告；值为true时，则不会
    // eqnull => 值为false时，如果代码中使用"=="来比较变量与null，则JSHint会给出警告；值为true时，则不会
    // sub => 值为true时，允许用obj['name']和obj.name两种方式访问对象的属性；值为false时，不允许使用obj['name']方式，除非只能使用这种方式访问
        .pipe(plug.jshint({asi: true, eqnull: true, sub: true})) // 检查js
        .pipe(plug.jshint.reporter()); // 输出检查结果信息
});

// login.js 文件压缩
gulp.task('js_login', function () {
    gulp.src(config.js.js_login)
        .pipe(plug.concat('login.js')) // 多文件合并成1个文件
        //.pipe(plug.jshint())
        //.pipe(plug.jshint.reporter())
        .pipe(plug.uglify(uglify_option))
        .pipe(plug.rename({extname: '.min.js'}))
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.js.dest)); // 输出到此目录
});

// login.css 文件压缩
gulp.task('css_login', function () {
    gulp.src(config.css.css_login)
        .pipe(plug.concat('login.css')) // 多文件合并成1个文件
        .pipe(plug.minifyCss()) // 文件压缩
        .pipe(plug.rename({suffix: '.min'})) // 文件重命名为min.css
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.css.dest)); // 输出到此目录
});


//forgetPwd.js 文件压缩
gulp.task('js_forgetPwd', function(){
    gulp.src(config.js.js_forgetPwd)
        .pipe(plug.concat('forgetPwd.js'))
        .pipe(plug.uglify(uglify_option))
        .pipe(plug.rename({extname: '.min.js'}))
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.js.dest));
});

//forgetPwd.css 文件压缩
gulp.task('css_forgetPwd', function(){
    gulp.src(config.css.css_forgetPwd)
        .pipe(plug.concat('forgetPwd.css'))
        .pipe(plug.minifyCss())
        .pipe(plug.rename({suffix: '.min'}))
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.css.dest));
});


// js/index 文件压缩
gulp.task('js_index', function () {
   gulp.src(config.js.js_index)
       .pipe(plug.concat(config.name + '.js')) // 多文件合并成1个文件
       .pipe(plug.uglify(uglify_option))// 文件压缩:
       .pipe(plug.rename({extname: '.min.js'})) // 文件重命名为min.js
       .pipe(plug.header(config.banner))
       .pipe(gulp.dest(config.js.dest)); // 输出到此目录
});

// js/lib 文件压缩
gulp.task('js_index_lib', function () {
    gulp.src(config.js.js_index_lib)
        .pipe(plug.concat(config.name + '_lib.js')) // 多文件合并成1个文件
        .pipe(plug.uglify(uglify_option))// 文件压缩:
        .pipe(plug.rename({extname: '.min.js'})) // 文件重命名为min.js
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.js.dest)); // 输出到此目录
});

// css/index 文件压缩
gulp.task('css_index', function () {
    gulp.src(config.css.css_index)
        .pipe(plug.concat(config.name + '.css')) // 多文件合并成1个文件
        .pipe(plug.minifyCss()) // 文件压缩
        .pipe(plug.rename({extname: '.min.css'})) // 文件重命名为min.css
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.css.dest)); // 输出到此目录
});

// css/index-lib
gulp.task('css_index_lib', function () {
    gulp.src(config.css.css_index_lib)
        .pipe(plug.concat(config.name + '_lib.css')) // 多文件合并成1个文件
        .pipe(plug.minifyCss()) // 文件压缩
        .pipe(plug.rename({extname: '.min.css'})) // 文件重命名为min.css
        .pipe(plug.header(config.banner))
        .pipe(gulp.dest(config.css.dest)); // 输出到此目录
});


// index.html 文件压缩
gulp.task('html_index', function () {
    gulp.src(config.html.src + '/index.html')
        .pipe(plug.minifyHtml(minifyHtml_option))
        .pipe(gulp.dest(config.webapp))
});

// html/template 文件压缩
gulp.task('html_template', function () {
    gulp.src(config.html.html_template)
        .pipe(plug.minifyHtml(minifyHtml_option))
        .pipe(gulp.dest(config.html.dest + '/template'))
});

// html/error
gulp.task('html_error', function () {
    gulp.src(config.html.html_error)
        .pipe(plug.minifyHtml(minifyHtml_option))
        //.pipe(plug.minifyCss())
        .pipe(gulp.dest(config.html.dest + '/error'))
});

// 删除js
gulp.task('clean_js', function () {
    return gulp.src(config.js.dest + '/*.js')
        .pipe(plug.clean({force: true}));
});

// 删除html
gulp.task('clean_html', function () {
    return gulp.src([config.html.dest, '../index.html'])
        .pipe(plug.clean({force: true}));
});

// 图片复制
gulp.task('img_copy', function () {
    return gulp.src(config.img.src + '/**/*')
        .pipe(gulp.dest(config.img.dest))
});

//theme 皮肤文件包复制
gulp.task('theme_copy', function(){
    return gulp.src([theme_src + '/**/*', '!' + theme_src + '/**/test', '!' + theme_src + '/**/test/*.*'])
        .pipe(gulp.dest(theme_dest))
});

//theme CSS压缩
gulp.task('theme_css_min', function(){
    return gulp.src(theme_dest + '/**/*.css')
        .pipe(plug.minifyCss())
        .pipe(gulp.dest(theme_dest))
});

// login 任务
gulp.task('login', ['css_login', 'js_login']);

// forgetPwd 任务
gulp.task('forgetPwd', ['css_forgetPwd', 'js_forgetPwd']);

// index 任务
gulp.task('index', ['css_index', 'js_index']);

// html 任务
gulp.task('html', ['html_index', 'html_template']);

// clean 任务
//gulp.task('clean', ['clean_css', 'clean_js', 'clean_html']);

// build 任务
gulp.task('build', ['build_init', 'login', 'forgetPwd', 'index', 'html', 'img_copy']);

// build init
gulp.task('build_init', ['html_error', 'css_index_lib', 'js_index_lib']);

//theme 皮肤文件包打包服务
gulp.task('build_theme', ['theme_copy'], function(){
    gulp.run(['theme_css_min']);
});