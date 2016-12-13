indexApp.factory('pageDao',function($http, $compile){
    var dao = {};
    //初始化页码
    var initPageNo = function($scope, pageNo, totalPage){
        var html = '';

        html += '<ul>';
            //1. 判断是否有 上一页
            if(pageNo != 1){
                html += '<li class="pre_page">上一页</li>';
            }

            var maxPreNo = 5;
            var hasPreNo = (totalPage > 8) && pageNo > maxPreNo;//有前省略号

            var maxSuffixNo = totalPage - 3;
            var hasSuffixNo = (totalPage > 8) && (pageNo < maxSuffixNo);//有后省略号

            //2 判断是否有前后省略号
            //2.1 只有前省略号
            if(hasPreNo && !hasSuffixNo){
                //alert('2.1 只有前省略号');
                html += '<li class="page_one">1</li>';
                html += '<li id="pn-break">…</li>';
                var startIndex = totalPage - 6;
                for(var i=startIndex; i<=totalPage; i++){
                    if(i <= 0)
                        continue;
                    html += '<li ';
                    if(pageNo == i){
                        html += 'id="page_selected" ';
                    }
                    html += 'class="page_one">'+ i +'</li>';
                }
            }
            //2.2 既有前省略号又有后省略号
            else if(hasPreNo && hasSuffixNo){
                //alert('2.2 既有前省略号又有后省略号');
                html += '<li class="page_one">1</li>';
                html += '<li id="pn-break">…</li>';
                var startIndex = pageNo - 3;
                var endIndex = pageNo + 2;
                for(var i=startIndex; i<=endIndex; i++){
                    if(i <= 0)
                        continue;
                    html += '<li ';
                    if(pageNo == i){
                        html += 'id="page_selected" ';
                    }
                    html += 'class="page_one">'+ i +'</li>';
                }
                html += '<li id="pn-break">…</li>';
                html += '<li class="page_one">'+ totalPage +'</li>';
            }
            //2.3 只有后省略号
            else if(!hasPreNo && hasSuffixNo){
                //alert('2.3 只有后省略号');
                var maxSize = maxPreNo + 2;
                for(var i=1; i<=maxSize; i++){
                    html += '<li ';
                    if(pageNo == i){
                        html += 'id="page_selected" ';
                    }
                    html += 'class="page_one">'+ i +'</li>';
                }
                html += '<li id="pn-break">…</li>';
                html += '<li class="page_one">'+totalPage+'</li>';
            }
            //2.4 没有前后省略号
            else if(!hasPreNo && !hasSuffixNo){
                //alert('2.4 没有前后省略号');
                for(var i=1; i<=totalPage; i++){
                    html += '<li ';
                    if(pageNo == i){
                        html += 'id="page_selected" ';
                    }
                    html += 'class="page_one">'+ i +'</li>';
                }
            }

            //4. 判断是否有 下一页
            var lastPageNo = totalPage - 1;
            if(pageNo != totalPage && pageNo != totalPage){
                html += '<li class="next_page">下一页</li>';
            }

        html += '</ul>';
        var $pageNoBar = $('.right_footer_page');
        $pageNoBar.empty().append(html);

    };
    //初始化每页分页数量
    var initPageNum = function(pageSize){
        $('.show_page').each(function(){
            var $this = $(this);
            var num = $this.attr('value');
            if(num == pageSize){
                $this.attr('id', 'page_selected');
            }
        });
    };
    //绑定事件
    var bindPageEvent= function($scope){
        var $pageNoBar = $('.right_footer_page');
        // 绑定 上一页 单击事件
        $pageNoBar.find('.pre_page').bind('click', function(){
            var $curPageNo = $pageNoBar.find('#page_selected');//当前页码
            var $prevPageNo = $curPageNo.prev();//上一个页码
            var prePageNo = $prevPageNo.text();
            if(prePageNo === '上一页'){
                return;
            }
            $curPageNo.attr('id','');//清除选中样式
            $prevPageNo.attr('id','page_selected');//将上一个页码的样式设置为选中
            $scope.list();
        });
        // 绑定 普通页码 单击事件
        $pageNoBar.find('.page_one').bind('click', function(){
            // 清除旧的选中样式
            $pageNoBar.find('#page_selected').attr('id','');
            var $this = $(this);
            // 将当前页码设置为选中样式
            $this.attr('id','page_selected');
            // 重新加载数据
            $scope.list();
        });
        // 绑定 下一页 单击事件
        $pageNoBar.find('.next_page').bind('click', function(){
            var $curPageNo = $pageNoBar.find('#page_selected');//当前页码
            var $nextPageNo = $curPageNo.next();//下一个页码
            var nextPageNo = $nextPageNo.text();
            if(nextPageNo === '下一页'){
                return;
            }
            $curPageNo.attr('id','');//清除选中样式
            $nextPageNo.attr('id','page_selected');//将下一个页码的样式设置为选中
            $scope.list();
        });

        // 绑定 每页记录数 单击事件
        var $pageNumBar = $('.page_num');
        var $show_page = $pageNumBar.find('.show_page');
        $show_page.off();//移除单击事件，防止重复绑定
        $show_page.on('click', function(){
            //清除选中样式
            $pageNumBar.find('#page_selected').attr('id','');
            var $this = $(this);
            //重新添加选中样式
            $this.attr('id','page_selected');
            //重置每页记录数
            var paramKey = 'page.pageSize';
            var paramValue = $this.val();
            var url = '/sysconfig/setvaluebykey?paramKey=' + paramKey + '&paramValue=' + paramValue;
            $http.get(url)
                .success(function(data, status, headers, config){
                    if(data.result == 'FAIL'){
                    	jDialog.alert(data.message);
                        return;
                    }
                    $scope.list();
                })
                .error(function(data, status, headers, config){
                    //alert('error：' + data);
                });
        });
    };
    //获取当前页码
    dao.getPageNo = function(){
        var pageNo = $('.right_footer_page  li[id="page_selected"]').text();
        if(pageNo === null || pageNo === undefined || $.trim(pageNo) === '')//为空时，默认为1
            pageNo = 1;
        return pageNo;
    };
    //初始化
    dao.init = function($scope, data){
        var pageNo = data.pageNo;//当前页码
        var totalPage = data.totalPage;//总页数
        var pageSize = data.pageSize;//每页记录数
        var totalRecord = data.totalRecord;//总数量
        $scope.showPageBar = totalRecord > 0 ? true : false;
        initPageNo($scope, pageNo, totalPage); //初始化页码
        initPageNum(pageSize);//初始化每页分页数量
        bindPageEvent($scope);//绑定事件
    };

    // 切换页面显示条数
    dao.changePageSize = function ($scope, callback) {
        var $show_page = $('#page_num2 .show_page');
        $show_page.off().on('click', function () {
            // 当前第1页
            $scope.pageNo2 = 1;
            //清除选中样式
            $show_page.removeProp('id');
            var $this = $(this);
            //重新添加选中样式
            $this.prop('id','page_selected');
            //重置每页记录数
            var paramKey = 'page.pageSize';
            var paramValue = $this.val();
            var url = '/sysconfig/setvaluebykey?paramKey=' + paramKey + '&paramValue=' + paramValue;
            $http.get(url)
                .success(function(data, status, headers, config){
                    if(data.result == 'FAIL'){
                        jDialog.alert(data.message);
                        return;
                    }
                    callback();
                })
                .error(function(data, status, headers, config){
                    //alert('error：' + data);
                });
        });
    };

    // 分页提示
    dao.pageTips = function ($scope) {
        var $show_page = $('#page_num2 .show_page');
        $show_page.each(function () {
            var $this = $(this);
            if ($this.val() == $scope.pageSize2.toString()) {
                $this.prop('id','page_selected');
            }
        });

        $('#page_tips').html(
            '第'+$scope.pageNo2+'页/共'+$scope.pageTotal2+'页 共总'+$scope.rowTotal2+'条'
        );
    };

    // 上下页事件
    dao.pageBindEvent = function ($scope, callback) {
        var that = this;
        // 上一页click
        $('.pre_page2').off().click(function () {
            if ($scope.pageNo2 <= 1) {
                jDialog.alert('提示', '没有上一页了');
                return ;
            } else {
                $scope.pageNo2 -= 1;
                callback();
                that.pageTips($scope);
            }
        });

        // 下一页click
        $('.next_page2').off().click(function () {
            if ($scope.pageNo2 >= $scope.pageTotal2) {
                jDialog.alert('提示', '没有下一页了');
                return ;
            } else {
                $scope.pageNo2 += 1;
                callback();
                that.pageTips($scope);
            }
        });
    };

    // dialog分页
    dao.dialogPageInit = function ($scope, data, callback) {
        $scope.rowTotal2 = data.totalRecord; // 总记录数
        $scope.pageSize2 = data.pageSize; // 每页记录数
        $scope.pageTotal2 = Math.ceil($scope.rowTotal2 / $scope.pageSize2);
        $scope.showPageBar2 = $scope.rowTotal2 > 0 ? true : false;

        this.changePageSize($scope, callback);
        this.pageBindEvent($scope, callback);
        this.pageTips($scope);
    };

    return dao;
});