package demo;

import config.AppConfig;
import lib.MessageMultiXSend;
import lib.VoiceMultiXSend;
import lib.VoiceXSend;
import net.sf.json.JSONObject;
import utils.ConfigLoader;

public class MessageMultiXsendDemo {
	public static void main(String[] args) {
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MessageMultiXSend submail = new MessageMultiXSend(config);
		submail.addProject("g8crk1");
		JSONObject json=new JSONObject();
		json.put("name", "张三");
		json.put("code", "1123");
		json.put("time", "1分钟");
		submail.addVars(json);
		submail.addMulti("15210278806");
		
		JSONObject json2=new JSONObject();
		json2.put("name", "老江");
		json2.put("code", "11244");
		json2.put("time", "1分钟");
		submail.addVars(json2);
		submail.addMulti("17600930915");
		String response=submail.multixsend();
		System.out.println("接口返回消息:"+response);
	}

}
