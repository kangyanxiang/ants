/**
 * Created by zhuxuehuang on 16-5-27.
 */

indexApp.service('DateService', function () {
    var service = {};

    var dt = new Date();
    var dt_year = dt.getFullYear();
    var dt_month = (dt.getMonth() + 1) > 0 && (dt.getMonth() + 1) < 10 ? '0' + (dt.getMonth() + 1) : dt.getMonth() + 1;
    var dt_date = dt.getDate() > 0 && dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate();

    service.monthEarly = function () {
        // 月初
        return dt_year + '-' + dt_month + '-' + '01';
    };

    service.getToday = function () {
        // 今天
        return dt_year + '-' + dt_month + '-' + dt_date;
    };

    service.getYesterday = function () {
        // 昨天
        var dt = new Date();
        dt.setDate(dt.getDate() - 1);
        var dt_year = dt.getFullYear();
        var dt_month = (dt.getMonth() + 1) > 0 && (dt.getMonth() + 1) < 10 ? '0' + (dt.getMonth() + 1) : dt.getMonth() + 1;
        var dt_date = dt.getDate() > 0 && dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate();
        return dt_year + '-' + dt_month + '-' + dt_date;
    };

    return service;
});