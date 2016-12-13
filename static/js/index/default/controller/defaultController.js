/**
 * Created by zhuxh on 16-1-25.
 */

indexApp.controller('defaultController', function($scope, defaultService) {
    // 新老用户占比
    function userChartPie(user_new, user_old) {
        var user_chart_pie = echarts.init(document.getElementById('user_chart_pie'));
        var user_chart_pie_option = {
            tooltip: {
                trigger: 'item',
                // formatter: "{a} <br/>{b}: {c} ({d}%)"
                formatter: function (item) {
                    return item.seriesName + "<br />" + item.name + '：' + (item.value ? item.value : '0') + '(' + (item.percent ? item.percent : '0') + '%)';
                }
            },
            legend: {
                orient: 'vertical',
                y: 'bottom',
                data: ['新用户', '老用户']
            },
            series: [{
                name: '新老用户占比',
                type: 'pie',
                radius: ['45%', '60%'],
                data: [{
                    value: user_new,
                    name: '新用户',
                    itemStyle: {
                        normal: {
                            color: '#D18381'
                        }
                    }
                }, {
                    value: user_old,
                    name: '老用户',
                    itemStyle: {
                        normal: {
                            color: '#B13432'
                        }
                    }
                }]
            }]
        };

        user_chart_pie.setOption(user_chart_pie_option);
    }

    // 用户趋势
    function userChartLine(hour_data, today_data, yesterday_data) {
        var user_chart_line = echarts.init(document.getElementById('user_chart_line'));

        var user_chart_line_option = {
            tooltip: {
                trigger: 'axis',
                formatter: function (item) {
                    var itemt = item[0],
                        itemy = item[1];
                    var xHour = itemy ? itemy.name : itemt.name;
                    var tips =  (xHour ? xHour : 0) + "时<br />";
                    if (itemt && itemt.value && xHour < new Date().getHours()) {
                        tips += itemt.seriesName + '：' + (itemt.value ? itemt.value : '0') + '<br />';
                    }
                    if (itemy && itemy.value) {
                        tips += itemy.seriesName + '：' + (itemy.value ? itemy.value : '0');
                    }
                    return tips;
                }
            },
            legend: {
                orient: 'horizontal',
                x: 'right',
                data: [{
                    name: '今日用户认证数',
                    textStyle: {
                        color: '#66CBFF'
                    }
                }, {
                    name: '昨日用户认证数',
                    textStyle: {
                        color: '#66CC66'
                    }
                }]
            },
            grid: {
                left: '3%',
                right: '1%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                data: hour_data //['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24']
            }],
            yAxis: [{
                type: 'value'
                // minInterval: 1, // 自动计算的坐标轴最小间隔大小
                // interval: 1, // 强制设置坐标轴分割间隔
                // splitNumber: 1 // 坐标轴的分割段数
            }],
            series: [{
                name: '今日用户认证数',
                type: 'line',
                smooth: true, // 曲线圆滑显示
                symbolSize: 1, // 标记的大小
                showSymbol: false, // 不显示标记
                lineStyle: {
                    normal: {
                        color: '#66CBFF',
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: 'rgba(102, 203, 255, 0.70)'
                    }
                },
                data: today_data //[120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
            }, {
                name: '昨日用户认证数',
                type: 'line',
                smooth: true, // 曲线圆滑显示
                symbolSize: 1, // 标记的大小
                showSymbol: false, // 不显示标记
                lineStyle: {
                    normal: {
                        color: '#66CC66',
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: 'rgba(102, 204, 102, 0.70)'
                    }
                },
                data: yesterday_data //[220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 11, 44]
            }]
        };

        user_chart_line.setOption(user_chart_line_option);
    }

    function init() {
        // 项目统计
        defaultService.projectCountAPI($scope);

        // 设备统计
        defaultService.deviceCountAPI($scope);

        // 用户占比饼形图
        defaultService.userCountAPI($scope, userChartLine, userChartPie);
    }

    init();
});