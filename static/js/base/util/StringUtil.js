/**
 *当字符串为空时，默认为 defaultStr
 * @author 许小满
 */
function defaultIfEmpty(str,defaultStr){
    if(str === null || str === undefined)
        str = defaultStr;
    return str;
}/**
 *当字符串为空时，默认为 defaultStr
 * @author 许小满
 */
function defaultString(str){
    if(str === null || str === undefined)
        str = '';
    return str;
}
/**
 * 判断字符串是否为空
 * @auth 许小满
 */
function isBlank(str){
    if(str === null || str === undefined || $.trim(str) === '')
        return true;
    return false;
}
/**
 * 判断字符串是否不为空
 * @auth 许小满
 */
function isNotBlank(str){
    if(str === null || str === undefined || $.trim(str) === '')
        return false;
    return true;
}
/**
 * 判断字符串是否为空
 * @auth 许小满
 * @date 2015-8-21
 */
function isNull(str){
    if(str === null || str === undefined)
        return true;
    return false;
}
//字符串规则
var defrules = {
    customerName : '^[0-9a-zA-Z\u4e00-\u9fa5_\(\),\.（），。@]{1,50}$', //客户名称，1-50位字符可由中英文、数字及“_”、()、（）、,、，、.、。、@组成，客户名称唯一，客户id唯一 (zhuxh 20160627)
    account : '^[0-9a-zA-Z\_]{4,20}$',//账号，4-20位字母、下划线、数字
    contact : '^[a-zA-Z\u4e00-\u9fa5]{1,20}$',//联系人，1-20位汉字、字母，不含特殊字符
    contactNew : '^[a-zA-Z\u4e00-\u9fa5]{0,20}$',//联系人，0-20位汉字、字母，不含特殊字符
    contactWay: '^(1[3|4|5|7|8][0-9][0-9]{8})?$',//联系方式，11位以1开头符合手机号码规则的数字
    projectName : '^[0-9a-zA-Z\u4e00-\u9fa5\_]{1,50}$',//项目名称，1-50位字符，包括汉字、字母、数字、下划线，不含特殊字符
    userName: '^[0-9a-zA-Z\-\_]{1,50}$',//1-50位字符，包括字母、数字、下划线、连接符(用户名)
    cellphone: '^(1[0-9]{10})?$',//手机号，11位以1开头符合手机号码规则的数字
    realName: '^[a-zA-Z\u4e00-\u9fa5]{1,20}$',//1-20位字符，包括字母、汉字(姓名/真实姓名)
    deptName: '^[0-9a-zA-Z\u4e00-\u9fa5]{1,20}$',//1-20位汉字、字母、数字(部门)
    newPwd: '^[^\u4e00-\u9fa5]{4,20}$',//静态用户新密码
    qNewPwd: '^[^\u4e00-\u9fa5]{4,20}$',//静态用户确认新密码

    wifiPlatformName : '^[0-9a-zA-Z\u4e00-\u9fa5\_]{1,50}$', //平台名称，1-50位字符，包括汉字、字母、数字、下划线，不含特殊字符
    portalStrategyName : '^[0-9a-zA-Z\u4e00-\u9fa5\_]{1,50}$', //客户名称，1-50位字符，包括汉字、字母、数字、下划线，不含特殊字符
    wifiProjectName : '^[0-9a-zA-Z\u4e00-\u9fa5\_]{1,50}$', //项目名称，1-50位字符，包括汉字、字母、数字、下划线，不含特殊字符
    passwordNew : '^[0-9a-zA-Z_@\$\-]{1,50}$',//1-50位字符，包含字母、数字、下划线、连接符、@、$
    passport : '^[0-9a-zA-Z]{1,20}$',//护照，1-20位字母、数字
    identityCard : '^[0-9]{17}([0-9]|X){1}$', //身份证，18位数字或17位数字最后一位字母X

    //策略类别：编码
    typeCode : '^[0-9a-zA-Z]{1,20}$',//1-20位字母、数字

    domain: '^[a-z0-9]{1,}([-\.]{1}[a-z0-9]{1,}){0,}[\.]{1}[a-z]{1,4}$',
    email: '^[a-z0-9_\.]{1,}@[a-z0-9]{1,}([-\.]{1}[a-z0-9]{1,}){0,}[\.]{1}[a-z]{1,4}$',
    //userName: '^[0-9a-zA-Z\_]{1,20}$',//1-20位字符，包括字母、数字、下划线(用户名)
    //password 表达式有重名
    //password   : '^[0-9a-zA-Z\\x21-\\x7e]{6,20}$',//6-20位字符，包括字母、数字、下划线、特殊符号(密码)
    //projectName: '^[0-9a-zA-Z\_\u4e00-\u9fa5]{4,20}$',//5-20位字符，包括字母、数字、下划线
    password: '^[0-9a-zA-Z\_\u4e00-\u9fa5]{4,20}$',//4-20位字符，包括字母、数字、下划线
    chinese: '^[\u4e00-\u9fa5]{0,}$',
    wchars: '^[^\x00-\xff]{0,}$',
    phone: '^(([0-9]{3,4}[-]{1})?[0-9]{7,8})?$',
    //cellphone: '^(1[3|4|5|7|8][0-9][0-9]{8})?$',//手机号
    zip: '^([1-9][0-9]{5})?$',
    integer: '^([-]?[0-9]+)?$',
    positiveInteger: '^(0|[1-9][0-9]*)$',//正整数
    number: '^([-]?[0-9]{1,}([.][0-9]{1,})?)?$',
    idcard: '^([1-9]{1}[0-9]{14}([0-9]{2}[0-9x])?)?$',
    //realName: '^[a-zA-Z\u4e00-\u9fa5]{1,20}$',//1-20位字符，包括字母、汉字(姓名/真实姓名)
    industryName: '^[A-Za-z\u4e00-\u9fa5]{1,20}$',//1-20位字符，包括字母、汉字
    //deptName: '^[0-9a-zA-Z\u4e00-\u9fa5]{1,20}$',//1-20位汉字、字母、数字(部门)
    orderNo: '^[0-9]*$',//纯数字
    atlas: '^[0-9a-zA-Z\_\u4e00-\u9fa5]{1,50}$',//地理位置 1-50位汉字、字母、下划线、数字
    contactPer: '^[a-zA-Z\_\u4e00-\u9fa5]{1,20}$' //联系人：1-20位汉字、字母、下划线Contact person
};

//正则检测字符串
function chkString(str, pattern){
    var re = new RegExp(pattern, 'gi');
    return re.test(str);
}

function chkStringNew(str, pattern){
    var re = new RegExp(pattern);
    return re.test(str);
}

// 检测字符串长度
function checkLengthFail(str, length) {
    if(str.length > length) {
        return true;
    } else {
        return false;
    }
}