/**
 * Created by zhuxh on 15-12-2.
 */

var webapp = '../src/main/webapp',

    static = '../static';

var js = {
        src: static + '/js',
        dest: webapp + '/js'
    };

var css = {
        src: static + '/css',
        dest: webapp + '/css'
    };

var html = {
        src: static + '/html',
        dest: webapp + '/html'
    };

var img = {
        src: static + '/images',
        dest: webapp + '/images'
    };

var theme = {
    src: static + '/theme/src',
    dest: static + '/theme/build'
};

// 登录页css
css.css_login = [
        css.src + '/login/login.css',
        css.src + '/lib/poshytip/tip-yellow.css'
    ];

// 登录页js
js.js_login = [
        js.src + '/lib/jquery/jquery-1.11.3.min.js',
        js.src + '/lib/jquery/jquery.poshytip.min.js',
        js.src + '/base/util/JTipsUtil.js',
        js.src + '/login/login.js'
    ];

//忘记密码css
css.css_forgetPwd = [
        css.src + '/login/forgetPwd.css',
        css.src + '/lib/poshytip/tip-yellow.css'
];

//忘记密码js
js.js_forgetPwd = [
        js.src + '/lib/jquery/jquery-1.11.3.min.js',
        js.src + '/lib/jquery/jquery.poshytip.min.js',
        js.src + '/base/util/JTipsUtil.js',
        js.src + '/base/util/StringUtil.js',
        js.src + '/login/forgetPwd.js'
];

// 栓验js
js.js_hint = js.src + '/**/*.js';

// 首页公共样式css/lib
css.css_index_lib = [
        css.src + '/lib/jquery-ui/jquery-ui.css',
        css.src + '/lib/poshytip/tip-yellow.css',
        css.src + '/lib/bootstrap/bootstrap.min.css',
        css.src + '/lib/datetimepicker/jquery.datetimepicker.css',
        css.src + '/lib/select2/select2.min.css',
        css.src + '/lib/ngDialog/ngDialog.css',
        css.src + '/lib/ngDialog/ngDialog-theme-default.css'
    ];

// 首页样式
css.css_index = [
        css.src + '/index/**/*.css',
        css.src + '/base/**/*.css'
    ];

// 首页公共脚本js/lib
js.js_index_lib = [
        js.src + '/lib/jquery/jquery-1.11.3.min.js',
        js.src + '/lib/jquery/jquery.form.min.js',
        js.src + '/lib/jquery/jquery-ui.min.js',
        js.src + '/lib/jquery/jquery.poshytip.min.js',
        js.src + '/lib/jquery/jquery.datetimepicker.js',
        js.src + '/lib/angular/angular.min.js',
        js.src + '/lib/angular/angular-route.min.js',
        js.src + '/lib/angular/ng-file-upload.min.js',
        js.src + '/lib/angular/ngDialog.js',
        js.src + '/lib/bootstrap/ui-bootstrap-tpls-0.13.3.min.js',
        js.src + '/lib/select2/select2.min.js',
        js.src + '/lib/echarts.min.js'
    ];

// 首页脚本
js.js_index = [
        js.src + '/index/index/index.js',
        js.src + '/base/**/*.js',
        js.src + '/index/**/*.js'
    ];

// angular模板目录
html.html_template = html.src + '/template/**/*.html';

// 错误html文件
html.html_error = html.src + '/error/**/*.html';

var now = new Date();
var version = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate() + ' ' + now.getHours() + ':' + now.getMinutes() + ':' + now.getSeconds();
var banner = ['/**',
    ' @Copyright 爱WiFi TOE项目组',
    ' @version v' + version,
    ' */',
    '\n'].join('');

module.exports = {
    name: "awifi_toe",
    webapp: webapp,
    banner: banner,
    js: js,
    css: css,
    html: html,
    img: img,
    theme: theme
};