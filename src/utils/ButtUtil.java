package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


public class ButtUtil {
	
	
	
	public static final String DOWNLOADPATH=SysUtils.getRootPath("", "")+"/upload/";
	
	//根据日期获取星期几
	public static int getDayofweek(String date){ 
		Calendar cal = Calendar.getInstance(); 
		if (date.equals("")) { 
			cal.setTime(new Date(System.currentTimeMillis())); 
		}else { 
			cal.setTime(new Date(getDateByStr2(date).getTime()));
		} 
		return cal.get(Calendar.DAY_OF_WEEK); 
		} 
		
	public static Date getDateByStr2(String dd) { 
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date; try { date = sd.parse(dd); 
		} catch (Exception e) { 
			date = null; e.printStackTrace(); 
		} 
		return date; 
		}
	
	
	
	//自定义时间
	public static boolean isBelongzdy (String thetime,String begin,String end){ 
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			//设置日期格式
			Date now =null; 
			Date beginTime =null; 
			Date endTime =null;
			try{ 
				now = df.parse(thetime); 
				beginTime = df.parse(begin);
				endTime = df.parse(end); 
			}catch(Exception e) { 
				e.printStackTrace();
			}
		Boolean flag = belongCalendar(now, beginTime, endTime);
		return flag; 
		}
	//根据当前时间
	public static boolean isBelong (String begin,String end){ 
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			//设置日期格式
			Date now =null; 
			Date beginTime =null; 
			Date endTime =null;
			try{ 
				now = df.parse(df.format(new Date())); 
				beginTime = df.parse(begin);
				endTime = df.parse(end); 
			}catch(Exception e) { 
				e.printStackTrace();
			}
		Boolean flag = belongCalendar(now, beginTime, endTime);
		return flag; 
		}
	
	public static boolean isBelong1 (String begin,String end){ 
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			//设置日期格式
			Date now =null; 
			Date beginTime =null; 
			Date endTime =null;
			try{ 
				now = df.parse(df.format(new Date())); 
				beginTime = df.parse(begin);
				endTime = df.parse(end); 
			}catch(Exception e) { 
				e.printStackTrace();
			}
		Boolean flag = belongCalendar1(now, beginTime, endTime);
		return flag; 
		}
	/** * 判断时间是否在时间段内 * @param nowTime * @param beginTime * @param endTime * @return */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) { 
		Calendar date = Calendar.getInstance(); 
		date.setTime(nowTime); 
		Calendar begin = Calendar.getInstance(); 
		begin.setTime(beginTime); 
		Calendar end = Calendar.getInstance(); 
		end.setTime(endTime);
		if(date.equals(begin) && date.before(end)){
			return true; 
		}else if(date.after(begin) && date.before(end)) {
			return true; 
		}else if(date.after(begin) && date.equals(end)) {
			return true; 
		}else{
			return false; 
		} 
	}
	/** * 判断时间是否在时间段内 * @param nowTime * @param beginTime * @param endTime * @return */
	public static boolean belongCalendar1(Date nowTime, Date beginTime, Date endTime) { 
		Calendar date = Calendar.getInstance(); 
		date.setTime(nowTime); 
		Calendar begin = Calendar.getInstance(); 
		begin.setTime(beginTime); 
		Calendar end = Calendar.getInstance(); 
		end.setTime(endTime);
		if(date.equals(begin) && date.before(end)){
			return true; 
		}else if(date.after(begin) && date.before(end)) {
			return true; 
		}else if(date.after(begin) && date.equals(end)) {
			return false; 
		}else{
			return false; 
		} 
	}
	
	
	//根据时刻点返回对应时分秒格式数据
	public static String getWholeTime(String amOrpm ,String time){
		String sj="";
		if("am".equals(amOrpm)){
			switch (time) {
			case "1":
				sj="01:00:00";
				break;
			case "2":
				sj="02:00:00";
				break;
			case "3":
				sj="03:00:00";
				break;
			case "4":
				sj="04:00:00";
				break;
			case "5":
				sj="05:00:00";
				break;
			case "6":
				sj="06:00:00";
				break;
			case "7":
				sj="07:00:00";
				break;
			case "8":
				sj="08:00:00";
				break;
			case "9":
				sj="09:00:00";
				break;
			case "10":
				sj="10:00:00";
				break;
			case "11":
				sj="11:00:00";
				break;
			case "12":
				sj="12:00:00";
				break;
			}
		}
		if("pm".equals(amOrpm)){
			switch (time) {
			case "1":
				sj="13:00:00";
				break;
			case "2":
				sj="14:00:00";
				break;
			case "3":
				sj="15:00:00";
				break;
			case "4":
				sj="16:00:00";
				break;
			case "5":
				sj="17:00:00";
				break;
			case "6":
				sj="18:00:00";
				break;
			case "7":
				sj="19:00:00";
				break;
			case "8":
				sj="20:00:00";
				break;
			case "9":
				sj="21:00:00";
				break;
			case "10":
				sj="22:00:00";
				break;
			case "11":
				sj="23:00:00";
				break;
			case "12":
				sj="00:00:00";
				break;
			}
		}
		return sj;
	}
	
	
	//整数相除 保留一位小数(百分比)
    public static String division(int a ,int b){
        String result = "";
        float num =(float)a/b;
        num=num*100;
        DecimalFormat df = new DecimalFormat("0.0");

        result = df.format(num);

        return result;

    }
    /*字符串转换为日期 */
    public static Date StringToDate(String string, String pattern) throws ParseException { 
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern); 
    	Date date = simpleDateFormat.parse(string);
    	return date; 
    	}
    
    /*日期 转换为字符串*/
    public static String DateToString(Date date, String pattern) { 
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern); 
    	String string = simpleDateFormat.format(date); 
    	return string; 
    	}
    
    
    //获取时分秒
    public static String getTime(String timestr) throws ParseException { 
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	Date date = df.parse(timestr);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	String time="";
    	if(calendar.get(Calendar.SECOND)==0){
    		time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":00";
    	}else{
    		time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
    	}
    	return time;
    }
    
    //判断当前时间年月日是否在某个时间段内
    public static int belongDate( String beginTime, String endTime) throws ParseException { 
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      
            Date dt1 = df.parse(beginTime);
            Date dt2 = df.parse(endTime);
            Date thisday=new Date();
            if (dt1.getTime()<thisday.getTime() && thisday.getTime()<dt2.getTime()) {
                return 1;
            }else {
                return 0;
            }
       
	}
    
    
    public static String getWeekName(int day){
    	String weekname="";
    	switch (day) {
		case 1:
			weekname="星期一";
			break;
		case 2:
			weekname="星期二";
			break;
		case 3:
			weekname="星期三";
			break;
		case 4:
			weekname="星期四";
			break;
		case 5:
			weekname="星期五";
			break;
		case 6:
			weekname="星期六";
			break;
		case 7:
			weekname="星期日";
			break;
    	}
    	return weekname;
    }
    
    //数字转中文数字
    public static String toChinese(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }
    
    //获取某个时刻后面几分钟之后的时刻
    public static String getMinutesAfter(String startDate,String minutes) throws ParseException{
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
     
    	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          Date date = df.parse(startDate);
    	  Calendar gc =new GregorianCalendar();
          gc.setTime(date);
          gc.add(GregorianCalendar.MINUTE,Integer.parseInt(minutes));
         String str= formatter.format(gc.getTime());
         return str;
        
   }
   public static void main(String[] args) throws ParseException {
    	/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	String time="2018-06-27 17:55:23.0";
    	Date date = df.parse(time);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.get(Calendar.YEAR);
    	calendar.get(Calendar.MONTH);
    	calendar.get(Calendar.DAY_OF_YEAR);
    	calendar.get(Calendar.HOUR_OF_DAY);
    	calendar.get(Calendar.MINUTE);
    	calendar.get(Calendar.SECOND);*/
	   
	   
	  /* int i= belongDate("2018-05-01", "2018-09-30");
	   System.out.println("i=="+i);*/
	   
	   
	   Scanner scanner = new Scanner(System.in);
       System.out.println("输入字符串：");
       String str = scanner.next();
       // 将字符串数字转化为汉字
      System.out.println(toChinese(str)); 
       }
   /**
    * 将文件数组排序，目录放在上面，文件在下面
    * @param file
    * @return
    */
   public static ArrayList<File> sort(File[] files){
       ArrayList<File> list = new ArrayList<>();
       for (File file2 : files) {

           list.add(file2);
       }
       
       Collections.sort(list, new Comparator<File>() {
           @Override
           public int compare(File o1, File o2) {
               if (o1.isDirectory() && o2.isFile())
                   return -1;
               if (o1.isFile() && o2.isDirectory())
                   return 1;
               Integer f = f(o1.getName());
               Integer f2 = f(o2.getName());
               return Integer.compare(f, f2);
           }
       });
	return list;
   }
   
   public static Integer f(String filename) {
       int x = filename.indexOf(".");
       String string2 = filename.substring(0,x);
       char[] cs = string2.toCharArray();
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i < cs.length; i++) {
           if(Character.isDigit(cs[i])) {
               builder.append(cs[i]);
           }
       }
       return Integer.parseInt(builder.toString());
   }
}   
