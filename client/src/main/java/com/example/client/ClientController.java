package com.example.client;

import java.io.File;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Objects;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

	public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}
 
	@GetMapping
	public ResponseEntity<?> index() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		medto m = new medto();
		m.setName("khalid");
		HttpEntity<medto> reqeust = new HttpEntity<medto>(m, headers);

		
		try {
			ResponseEntity<medto> responseEntity;
			responseEntity = restTemplate().postForEntity("https://localhost:8443/server", reqeust, medto.class);
			return new  ResponseEntity<medto>(responseEntity.getBody(),HttpStatus.OK);
		} catch (Exception e) {
			return new  ResponseEntity<String>("ERROR " + e.getMessage(),HttpStatus.I_AM_A_TEAPOT);
		}

	}
}

class medto {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}







// RestTemplate restTemplate = new RestTemplate();
//try {
//KeyStore keyStore = KeyStore.getInstance("jks");
//
//File keyFile = new File("C:\\apache-tomcat-9.0.40\\keystores\\my-release-key.keystore");
//FileSystemResource fileSystemResource = new FileSystemResource(keyFile);
//
//InputStream inputStream = fileSystemResource.getInputStream();
//keyStore.load(inputStream,
//        Objects.requireNonNull("changeit").toCharArray());
//
//SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder()
//        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
//        .loadKeyMaterial(keyStore,
//                "changeit".toCharArray()).build(),
//        NoopHostnameVerifier.INSTANCE);
//
//HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).setRedirectStrategy(new LaxRedirectStrategy())
//        .build();
//
//HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//restTemplate.setRequestFactory(requestFactory);
//}catch (Exception e) {
//	System.out.println("======= ERROR"+e.getMessage());
//}