package demo;

import config.AppConfig;
import lib.MESSAGEXsend;
import lib.MessageSend;
import lib.VoiceSend;
import utils.ConfigLoader;

public class MessageSendDemo {
	public static void main(String[] args) {
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		String[] phonenum=new String[] {"15210278806","17600930915"};
		for (int i = 0; i < phonenum.length; i++) {
			MessageSend submail = new MessageSend(config);
			submail.addTo(""+phonenum[i]+"");
			submail.addContent("【提现消息】你好，你的验证码是3373");
			submail.send();
		}
	}

}
