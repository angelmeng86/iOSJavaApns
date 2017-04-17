import java.util.Date;
import java.util.Map;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;


public class PushMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String certificatePath = "/Users/lwz/Documents/workspace/com_maple_push.p12";//推送证书
        String certificatePassword = "111111";//此处注意导出的证书密码不能为空因为空密码会报错
		
		 ApnsService service =
			     APNS.newService()
			     .withCert(certificatePath, certificatePassword)
			     //.withSandboxDestination() //开发环境推送证书使用
                 .withAppleDestination(true) //发布版环境
			     .build();
		 
//		 String payload = APNS.newPayload().alertBody("Can't be simpler than this!").build();
		 String payload = APNS.newPayload()
		            .badge(3)//图标气泡
		            .sound("default")//默认铃声
		            .alertBody("新闻资讯，你将收到该推送信息！")//通知栏显示信息
		            .customField("action", "mymessage")//自定义数据
		            .customField("uid", "123")//自定义数据
		            .build();
		 String token = "7bf31876f785822b2f23fb06f2391dd08b917f11646fa161b783b324b493380b";//目标设备令牌
		 
		 System.out.println("payload:" + payload);//json格式
		 
		 service.push(token, payload);
		 
		 Map<String, Date> inactiveDevices = service.getInactiveDevices();
		 for (String deviceToken : inactiveDevices.keySet()) {
		     Date inactiveAsOf = inactiveDevices.get(deviceToken);
		     System.out.println("token:" + deviceToken + " Date:" + inactiveAsOf);
		 }
		 
		 System.out.println("Done.");
	}

}
