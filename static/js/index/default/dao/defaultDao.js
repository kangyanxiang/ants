/**
 * Created by zhuxh on 16-1-25.
 */

indexApp.factory('defaultDao', function ($http) {
    // 初始化Dao对象
    var dao = {};

    // 格式化数字千分位
    function commafy(num) {
        // num = num.toFixed(2) + '';
        num = num + '';
        var re = /(-?\d+)(\d{3})/;
        while (re.test(num)) {
            num = num.replace(re, "$1,$2");
        }
        return num;
    }

    //用户数据接品
    dao.userCountAPI = function ($scope, funLine, funPie) {
        var url = '/report/userTotal';
        $http.get(url)
            .success(function (resp) {
                if (resp.result === 'OK') {
                    //resp.totalListYsday = [{"x":0,"y":7531},{"x":1,"y":4031},{"x":2,"y":2725},{"x":3,"y":2126},{"x":4,"y":2026},{"x":5,"y":3894},{"x":6,"y":11500},{"x":7,"y":32514},{"x":8,"y":60218},{"x":9,"y":50935},{"x":10,"y":46000},{"x":11,"y":44206},{"x":12,"y":45797},{"x":13,"y":39365},{"x":14,"y":44639},{"x":15,"y":40974},{"x":16,"y":39623},{"x":17,"y":36021},{"x":18,"y":32823},{"x":19,"y":29569},{"x":20,"y":25435},{"x":21,"y":21559},{"x":22,"y":15877},{"x":23,"y":11894}];
                    //resp.totalListToday = [{"x":0,"y":8134},{"x":1,"y":4385},{"x":2,"y":2252},{"x":3,"y":226},{"x":4,"y":1611},{"x":5,"y":4054},{"x":6,"y":11971},{"x":7,"y":33044},{"x":8,"y":61162},{"x":9,"y":51071},{"x":10,"y":45232},{"x":11,"y":43009},{"x":12,"y":45222}];
                    // 昨日用户认证数
                    $scope.user_today_count = commafy(resp.todayAthorUser);
                    // 今日用户认证数
                    $scope.user_yesterday_count = commafy(resp.yesterdayAthorUser);
                    // 累计用户UV数
                    $scope.user_uv_count = commafy(resp.totalUser);
                    // 新用户占比
                    $scope.user_new = resp.newUser;
                    // 老用户占比
                    $scope.user_old = resp.oldUser;
                    // 新老用户占比饼形图
                    funPie($scope.user_new, $scope.user_old);
                    // 用户趋势初始值
                    var hour_data = [],
                        chart_today_data = [],
                        chart_yesterday_data = [];
                    // 今天最后一个值
                    var totalListTodayArray = resp.totalListToday;
                    var totalListTodayLast = totalListTodayArray.pop();
                    var totalListTodayLastX = totalListTodayLast["x"] || 0;
                    for (var i=0; i<totalListTodayLastX; i++) {
                        chart_today_data.push(0);
                    }
                    for (var i=0; i<=23; i++) {
                        hour_data.push(i);
                        // chart_today_data.push(0);
                        chart_yesterday_data.push(0);
                    }
                    // 用户趋势今天
                    var datas_today = resp.totalListToday;
                    for (var i = 0, len = datas_today.length; i < len; i++) {
                        var item = datas_today[i];
                        chart_today_data[parseInt(item.x)] = item.y;
                    }
                    // 用户趋势昨天
                    var datas_yesterday = resp.totalListYsday;
                    for (var i = 0, len = datas_yesterday.length; i < len; i++) {
                        var item = datas_yesterday[i];
                        chart_yesterday_data[parseInt(item.x)] = item.y;
                    }

                    funLine(hour_data, chart_today_data, chart_yesterday_data);
                }
            });
    };

    // 项目总数
    dao.projectCountAPI = function ($scope) {
        var url = '/project/count';
        $http.get(url)
            .success(function(resp) {
                if (resp.result === 'OK') {
                    $scope.project_count = commafy(resp.data);
                }
            });
    };

    //设备总数
    dao.deviceCountAPI = function ($scope) {
        var url = '/report/devTotal';
        $http.get(url)
            .success(function (resp) {
                if (resp.result === 'OK') {
                    $scope.device_counts = commafy(resp.device_num);
                    $scope.device_online_count = commafy(resp.online_device_num);
                    $scope.device_offline_count = commafy(parseInt(resp.device_num) - parseInt(resp.online_device_num));
                }
            });
    };

    return dao;
});