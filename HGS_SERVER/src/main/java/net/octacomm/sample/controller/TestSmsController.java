package net.octacomm.sample.controller;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.io.PrintStream;
import net.octacomm.sample.domain.ResponceGabia;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/test" })
@Controller
public class TestSmsController {
	
	@RequestMapping(value = { "/sms" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void sms() throws IOException {
		String text = "hgsstorage:ed70a05aecb5a80b34ca7913e4a5c90c";
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		System.out.println("인코딩 전 : " + text);
		System.out.println("인코딩 text : " + new String(encodedBytes));

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
		Request request = new Request.Builder().url("https://sms.gabia.com/oauth/token").post(body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "Basic " + new String(encodedBytes)).addHeader("cache-control", "no-cache")
				.build();

		OkHttpClient client = new OkHttpClient();
		Response response = null;
		response = client.newCall(request).execute();

		if (response.isSuccessful()) {
			ResponceGabia responceGabia = null;
			Gson gson = new Gson();
			responceGabia = (ResponceGabia) gson.fromJson(response.body().string(), ResponceGabia.class);
			test2(responceGabia);
		}
	}

	public void test2(ResponceGabia responceGabia) {
		System.err.println("responceGabia : " + responceGabia);
	}
}