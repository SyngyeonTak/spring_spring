package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
  자바 스탠다드에서는 Socket(stateful: 연결지속 됨)통신뿐만 아니라
  웹서버와 HTTP(stateless: 연결이 끊김) 통신도 지원한다!!!
 */
public class HttpTest {
	URL url;
	HttpURLConnection con;//HTTP 통신 객체
	OutputStream os;
	public HttpTest() {
		try {
			url = new URL("http://localhost:7777/rest/member");//요청 주소
			con= (HttpURLConnection)url.openConnection();//커낵션 객체 생성
			con.setRequestMethod("POST");//post 방식
			con.setRequestProperty("Content-Type", "application/json;utf-8");//http 통신시 헤더 정보 구성
			//웹서버에 요청 시작 (출력 스트림을 만들어 출력을 해야함)
			con.setDoOutput(true);
			os = con.getOutputStream();
			os.write(0);
			os.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(os != null) os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new HttpTest();
	}
}
