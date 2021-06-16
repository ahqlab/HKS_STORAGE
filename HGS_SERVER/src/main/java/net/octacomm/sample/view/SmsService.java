package net.octacomm.sample.view;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.RealResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.octacomm.sample.domain.Email;
import net.octacomm.sample.domain.ResponceGabia;

/**
 * 가비아 문자 API 연동 서비스
 * 
 * @author hgko
 *
 */
/*
 * @Slf4j
 * 
 * @Service
 */
public class SmsService {
	
	private String tokenType;
	private String token = "hgsstorage:";
	//private String token = "hgsstorage:";
	
	//private final String message = "캠퍼스형방과후학교-대기신청하신 과목의 수강등록이 완료되었습니다. 수강을 원하지않으시면 취소신청을 꼭 해주시기바랍니다.";
	//private final String message = "캠퍼스형방과후 수강대기중인 과목의 수강이 승인되었습니다. 전화 연락 부탁드립니다.";
	//private final String callback = "0552740518"; //0552870513
	private final String callback = "01091299312"; //0552870513
	private final String API_ID = "	hgsstorage";
	//private final String API_ID = "	hgsstorage";
	private final String API_KEY = "ed70a05aecb5a80b34ca7913e4a5c90c";
	
	public void init() throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		
		String text = "hgsstorage:ed70a05aecb5a80b34ca7913e4a5c90c";  
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		
		/* base64 decoding */ 
		//byte[] decodedBytes = Base64.decodeBase64(encodedBytes); 
		//System.out.println("인코딩 전 : " + text); 
		System.out.println("인코딩 text : " + new String(encodedBytes));

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
		Request request = new Request.Builder()
		.url("https://sms.gabia.com/oauth/token")
		.post(body)
		.addHeader("Content-Type", "application/x-www-form-urlencoded")
		.addHeader("Authorization", "Basic aGdzc3RvcmFnZTplZDcwYTA1YWVjYjVhODBiMzRjYTc5MTNlNGE1YzkwYw==")
		.addHeader("cache-control", "no-cache")
		.build();

		
		Response response = client.newCall(request).execute();
		//log.debug("init response : " + response.toString());
		
		if (response.isSuccessful()) {
			Gson gson = new Gson();
	        ResponceGabia responceGabia = gson.fromJson(response.body().string(), ResponceGabia.class);
			//System.err.println("ResponceGabia : " + responceGabia);
			
			String accessToken = responceGabia.getAccess_token();
			tokenType = responceGabia.getToken_type();
			token += accessToken;
			//System.out.println("token : " + token);
		}
	}

	/**
	 * 메시지 전송
	 * @param phone
	 * @throws IOException
	 */
	/*
	 * public void send(Email mail ,String phone, String sendNumber) throws
	 * IOException { String messeage = "[" + mail.getStatus() + "]" +
	 * mail.getStoreageName() + " : " + mail.getBody(); init();
	 * System.err.println("messeage : " + messeage); phone = phone.replaceAll("-",
	 * "");
	 * 
	 * MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
	 * //RequestBody body = RequestBody.create(mediaType, "phone=" + phone +
	 * "&callback=" + callback + "&message=" + messeage + "&refkey=12132214");
	 * RequestBody body = RequestBody.create(mediaType, "phone=" + phone +
	 * "&callback=" + sendNumber + "&message=" + messeage + "&refkey=12132214");
	 * Request request = new Request.Builder()
	 * .url("https://sms.gabia.com/api/send/sms") .post(body)
	 * .addHeader("Content-Type", "application/x-www-form-urlencoded")
	 * .addHeader("Authorization", tokenType + " " + new
	 * String(Base64.encodeBase64(token.getBytes()))) .addHeader("cache-control",
	 * "no-cache") .build();
	 * 
	 * OkHttpClient client = new OkHttpClient(); Response response =
	 * client.newCall(request).execute(); System.err.println("response : " +
	 * response.toString()); //log.debug("send response : " + response.toString());
	 * }
	 */

	public void send2(Email mail ,String phone, String sendNumber) throws IOException {
		phone = phone.replace("-", "");
		sendNumber = sendNumber.replace("-", "");
		String messeage = "[" + mail.getStatus() + "]" + mail.getStoreageName() + " : " + mail.getBody();
		init();
		System.err.println("messeage : " + messeage);
		phone = phone.replaceAll("-", "");

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		//RequestBody body = RequestBody.create(mediaType, "phone=" + phone + "&callback=" + callback + "&message=" + messeage + "&refkey=12132214");
		RequestBody body = RequestBody.create(mediaType, "phone=" + phone + "&callback=" + sendNumber + "&message=" + messeage + "&refkey=12132214");
		Request request = new Request.Builder()
		.url("https://sms.gabia.com/api/send/sms")
		.post(body)
		.addHeader("Content-Type", "application/x-www-form-urlencoded")
		.addHeader("Authorization", tokenType + " " + new String(Base64.encodeBase64(token.getBytes())))
		.addHeader("cache-control", "no-cache")
		.build();

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		System.err.println("response : " + response.toString());
		
	}
	
}
