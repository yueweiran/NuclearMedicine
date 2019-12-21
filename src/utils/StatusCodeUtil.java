package utils;

public class StatusCodeUtil {
	public static String getConByCode(String code){
		String str="";
		switch (code) {
		case "200":
			str="请求处理成功";
			break;
		case "201":
			str="资源被成功创建";
			break;
		case "204":
			str="请求处理成功，但没有返回结果";
			break;
		case "400":
			str="错误的请求";
			break;
		case "401":
			str="授权失败";
			break;
		case "403":
			str="无权限请求特定的资源";
			break;
		case "404":
			str="请求的资源不存在";
			break;
		case "422":
			str="数据验证失败";
			break;
		case "500":
			str="服务器内部错误";
			break;

		default:
			break;
		}
		return str;
	}
}
