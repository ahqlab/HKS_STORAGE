package net.octacomm.sample.controller;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/member", "/sms" })
@Controller
public class SnsController {
	private static final String SMS_KEY = "";

	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void login(Model model) throws IOException {
		System.err.println("sdasdkjahsduiahsdkjgh");

		String text = "ysc2019:eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvc21zLmdhYmlhLmNvbVwvIiwiYXVkIjoiXC9vYXV0aFwvdG9rZW4iLCJleHAiOjE1NjcyNzE4MzcsImNyZWF0ZWRfYXQiOjE1NjcyNjgyMzcsInVzZXJfaWQiOiJ5c2MyMDE5IiwiY2xpZW50X2lwIjoiMTEyLjE2Ny4xNzMuMTIifQ.UILpRXRn5I4woeWjU-PVRi_G8qnMmdoAhko3icU5dk_D-qsdKSAv8Gp5bdIjHl8Iub6pZVMO-s1qUpLn4eufj45ZvNUQwMl6rYQqI_-PhZ9eegT2gB78fC664DrmsiBsIRY4d_9b1qgFCdBq7JRgax1EDAk01BdcqK9P29KSPiq4BJYfGHuGH_PEO4dMGhKKs9dq5_u8p4n-89gWBqdZZwWb1p1kbhFhQIW0p_Xzoz008WwYcbj8uwKcpMuP4wh9ckSWFAgCULXLkhTXwvNwSZx31WfYicjQLqnx6AIqvlfdRT_pNfV0ITjf6aTZ2pSMS9_Qj39w7fwu0g_Qb7EUqw";
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());

		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		System.out.println("인코딩 전 : " + text);
		System.out.println("인코딩 text : " + new String(encodedBytes));

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
		Request request = new Request.Builder().url("https://sms.gabia.com/oauth/token").post(body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "Basic " + new String(encodedBytes)).addHeader("cache-control", "no-cache")
				.build();

		Response response = client.newCall(request).execute();
		System.err.println("response : " + response.body().toString());
	}

	@RequestMapping(value = { "/send" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void send() throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType,
				"phone=01091299312&callback=0552870513&message=SMS TEST MESSAGE&refkey=12132214");
		Request request = new Request.Builder().url("https://sms.gabia.com/api/send/sms").post(body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization",
						"Basic eXNjMjAxOTpleUowZVhBaU9pSktWMVFpTENKaGJHY2lPaUpTVXpJMU5pSjkuZXlKcGMzTWlPaUpvZEhSd2N6cGNMMXd2YzIxekxtZGhZbWxoTG1OdmJWd3ZJaXdpWVhWa0lqb2lYQzl2WVhWMGFGd3ZkRzlyWlc0aUxDSmxlSEFpT2pFMU5qY3lOekU0TXpjc0ltTnlaV0YwWldSZllYUWlPakUxTmpjeU5qZ3lNemNzSW5WelpYSmZhV1FpT2lKNWMyTXlNREU1SWl3aVkyeHBaVzUwWDJsd0lqb2lNVEV5TGpFMk55NHhOek11TVRJaWZRLlVJTHBSWFJuNUk0d29lV2pVLVBWUmlfRzhxbk1tZG9BaGtvM2ljVTVka19ELXFzZEtTQXY4R3A1YmRJakhsOEl1YjZwWlZNTy1zMXFVcExuNGV1Zmo0NVp2TlVRd01sNnJZUXFJXy1QaFo5ZWVnVDJnQjc4ZkM2NjREcm1zaUJzSVJZNGRfOWIxcWdGQ2RCcTdKUmdheDFFREFrMDFCZGNxSzlQMjlLU1BpcTRCSllmR0h1R0hfUEVPNGRNR2hLS3M5ZHE1X3U4cDRuLTg5Z1dCcWRaWndXYjFwMWtiaEZoUUlXMHBfWHpvejAwOFd3WWNiajh1d0tjcE11UDR3aDlja1NXRkFnQ1VMWExraFRYd3ZOd1NaeDMxV2ZZaWNqUUxxbng2QUlxdmxmZFJUX3BOZlYwSVRqZjZhVFoycFNNUzlfUWozOXc3Znd1MGdfUWI3RVVxdw==")
				.addHeader("cache-control", "no-cache").build();

		Response response = client.newCall(request).execute();
		System.err.println("response : " + response.body().toString());
		System.err.println("response : " + response.toString());
	}
}