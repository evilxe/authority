package com.fzw.authority.util;


/**
 * @Description: 通用错误码
 */


public class BaseResultStatus {

	@ResultMessage("SUCCESS")
	public static final int  SUCCESS= 0;

	@ResultMessage("操作失败")
	public static final int  FAILDE= 1;

	@ResultMessage("系统异常，请联系管理员")
	public static final int  ERROR= 2;

	@ResultMessage("数据不存在")
	public static final int NOTEXIST = 4;

	@ResultMessage("用户未登录")
	public static final Integer NOTLOGIN = 10000;

	@ResultMessage("登录错误")
	public static final Integer LOGINERROR = 10001;

	@ResultMessage("没有操作权限")
	public static final Integer NOPERMISSION = 10002;

	@ResultMessage("接口认证错误")
	public static final Integer APIAUTHERROR = 10003;

	@ResultMessage("接口认证失败")
	public static final Integer APIAUTHDEFEATED = 10004;

	@ResultMessage("登录账号不能为空")
	public static  final Integer LOG_IN_ACCOUNT  = 10005;

	@ResultMessage("登录密码不能为空")
	public static  final Integer LOG_IN_PASSWORD  = 10006;

	@ResultMessage("账号不存在")
	public static  final Integer ADMIN_USER_IS_NULL  = 10007;

	@ResultMessage("该账号已经被禁用")
	public static  final Integer ADMIN_IS_DISABLED = 10008;

	@ResultMessage("密码错误")
	public static  final Integer PASSWORD_ERROR  = 10009;

	@ResultMessage("账号不能为空")
	public static  final  Integer ACCOUNT_IS_NULL = 10010;

	@ResultMessage("密码不能为空")
	public static  final  Integer PASSWORD_IS_NULL = 10011;

	@ResultMessage("确认密码不能为空")
	public static  final  Integer CONFIRM_PASSWORD_IS_NULL = 11002;

	@ResultMessage("真实姓名不能为空")
	public static  final  Integer REAL_NAME_IS_NULL = 10013;

	@ResultMessage("性别不能为空")
	public static  final  Integer SEX_IS_NULL = 10013;

	@ResultMessage("手机号不能为空")
	public static  final  Integer MOBILE_IS_NULL = 10015;

	@ResultMessage("邮箱地址不能为空")
	public static  final  Integer EMAIL_IS_NULL = 10016;

	@ResultMessage("该账号已经存在，不允许重复创建")
	public static  final  Integer ACCOUNT_ALREADY_EXIST = 10017;

	@ResultMessage("该手机号已经存在，不允许重复添加")
	public static  final  Integer MOBILE_ALREADY_EXIST = 10018;

	@ResultMessage("用户主键ID不能为空")
	public static  final  Integer USER_ID_IS_NULL = 10019;

	@ResultMessage("角色名称不能为空")
	public static  final  Integer ROLE_NAME_IS_NULL = 10020;

	@ResultMessage("角色描述不能为空")
	public static  final  Integer ROLE_DESCRBE_IS_NULL = 10021;

	@ResultMessage("该角色已经存在，不允许重复添加")
	public static  final  Integer ROLE_ALREADY_EXIST = 10023;

	@ResultMessage("角色主键ID不能为空")
	public static  final  Integer ROLE_ID_IS_NULL = 10024;

	@ResultMessage("菜单名称不能为空")
	public static  final  Integer MENU_NAME_IS_NULL = 10025;

	@ResultMessage("菜单描述不能为空")
	public static  final  Integer MENU_DESCRIBE_IS_NULL = 10026;

	@ResultMessage("菜单地址不能为空")
	public static  final  Integer MENU_URL_IS_NULL = 10027;

	@ResultMessage("菜单类型不能为空")
	public static  final  Integer MENU_TYPE_IS_NULL = 10028;

	@ResultMessage("菜单父级id不能为空")
	public static  final  Integer MENU_PARENT_ID_IS_NULL = 10029;

	@ResultMessage("菜单权限标识不能为空")
	public static  final  Integer MENU_PERMS_IS_NULL = 10030;

	@ResultMessage("菜单主键ID不能为空")
	public static  final  Integer MENU_ID_IS_NULL = 10031;

	@ResultMessage("该菜单已经存在，不允许重复添加")
	public static  final  Integer MENU_ALREADY_EXIST  = 10034;

	@ResultMessage("无菜单信息")
	public static  final  Integer MENU_IS_NULL = 10035;

	@ResultMessage("该角色不存在")
	public static  final  Integer ROLE_IS_NULL = 10036;

	@ResultMessage("菜单排序不能为空")
	public static  final  Integer MENU_ORDER_NUM_IS_NULL = 10037;

	@ResultMessage("菜单排序不能为空")
	public static  final  Integer MENU_COMPONENT_IS_NULL = 10038;

	@ResultMessage("分页参数异常")
	public static  final  Integer PAGE_ERROR = 10039;

	@ResultMessage("请勿添加空菜单")
	public static  final  Integer MENU_LIST_IS_NULL = 10040;


}
