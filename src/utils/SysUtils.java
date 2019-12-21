package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 项目管理工具类
 * @author wq
 * @version 2014-6-11
 */
public class SysUtils {
	
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SysUtils.class);
	
	//项目路径
	public static String rootPath;
	//动态资源路径
	public static String dynamicPath;
	//公共资源路径
	public static String publicPath;
	//APM win 标准版目录路径
	public static String apmWinPath;
	
	private static Logger log=LoggerFactory.getLogger(SysUtils.class);
    public static String SUCCESS = "SUCCESS"; //上传用户头像类型
    public static SimpleDateFormat dateFm = new SimpleDateFormat(); 
	
	/**
	* 输入：无
	* 输出：返回系统类别，windows系列的系统返回win,其他系列的返回linux
	*  
	*/	
	public static String getSystemTypex() {
		Properties prop = System.getProperties();
		String systemType = "not";
		String os = prop.getProperty("os.name");
		// logger.info("操作系统是"+os);
		if(log.isInfoEnabled()){
			log.info("os is" + os);
		}
		//这段代码用于在linux调试，以后要删掉
		/**
		if (os.startsWith("win") || os.startsWith("Win")) {
			// logger.info("操作系统"+os+"属于windows系列");
			systemType = "win";
		} else {
			// logger.info("操作系统"+os+"不属于windows系列");
			systemType = "linux";
		}
		*/
		//这段代码用于在linux调试，以后要删掉
		systemType="win";
		return systemType;
	}

	
	
	/**
	 * apm win标准版目录(tomcat上一级)
	 * @return
	 */
	public static String getApmWinPath() {
		if(apmWinPath != null){
			return apmWinPath;
		}
		File file = new File(getRootPath());
		apmWinPath = file.getParentFile().getParentFile().getParent();
		return apmWinPath;
	 }
	
	/**
	 * 公共资源路径-- 获取项目路径上级路径(webapps级)
	 * @return
	 */
	public static String getPublicPath() {
		if(publicPath != null){
			return publicPath;
		}
		File file = new File(getRootPath());
		publicPath = file.getParent();
		return publicPath;
	 }
	
	/**
	 * 动态资源路径-- (webapps 中用于放置动态生成的文件)
	 * @return
	 */
	public static String getDynamicPath() {
		if(dynamicPath != null){
			return dynamicPath;
		}
		dynamicPath = getPublicPath()+File.separator+"dynamic";
		return dynamicPath;
	 }
	
	/**
	 * 获取项目路径
	 * @param windowsBlank
	 * @param linuxBlank
	 * @return
	 */
	public static String getRootPath() {
		if(rootPath != null){
			return rootPath;
		}
		rootPath = getRootPath(" ", " ");
		return rootPath;
	 }
	
	/**
	 * 获取项目路径
	 * @param windowsBlank
	 * @param linuxBlank
	 * @return
	 */
	public static String getRootPath(String windowsBlank,String linuxBlank) {
		//String classPath = ApmUtils.class.getClassLoader().getResource("/").getPath();
		String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		
		if(log.isDebugEnabled()){
			log.debug(classPath);
		}
		//logger.info("classPath:"+classPath);
		String rootPath = null;
		//windows下
		if("\\".equals(File.separator)){   
			rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("/", "\\").replace("%20",windowsBlank);
		}
		//linux下
		if("/".equals(File.separator)){   
			rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("\\", "/").replace("%20",linuxBlank);
		}
		return rootPath;
	}
	

}