package com.expertrec.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.expertrec.service.generators.ContentWriter;

public class ExpertrecHttpServiceRequest {

	private String url;
	private String method;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void processRequest(String args[]) {
		if (args.length == 0) {
			this.url = "https://www.expertrec.com/";
			this.method = "get";
		} else if (args.length == 1) {
			this.url = args[0];
			this.method = "get";
		} else {
			this.url = args[0];
			this.method = args[1];
		}

		// Printing the method used
		System.out.println("Request Type: " + this.method);
		System.out.println("Request URL: " + this.url);
		if (this.method.equalsIgnoreCase("get"))
			sendGetRequest();
		else if (this.method.equalsIgnoreCase("post"))
			sendGetRequest();
		else
			System.out.println("Method(second argument) should be GET or POST");
	}

	private void sendGetRequest() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(this.url);

		// Executing the Get request
		HttpResponse httpresponse = null;
		try {
			httpresponse = httpclient.execute(httpget);
			processResponse(httpresponse);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendPostRequest() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpget = new HttpPost(this.url);

		// Executing the post request
		HttpResponse httpresponse = null;
		try {
			httpresponse = httpclient.execute(httpget);
			processResponse(httpresponse);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processResponse(HttpResponse httpresponse) throws UnsupportedOperationException, IOException {
		Header contentHeader = httpresponse.getFirstHeader("content-type");
		String contentType = contentHeader.getValue();
		System.out.println("Content type is " + contentType);
		HttpEntity entity = httpresponse.getEntity();
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());

		Header[] headers = httpresponse.getAllHeaders();
		// print headers
		System.out.println("-----------HEADERS--------------");
		printHeaders(headers);
		System.out.println("-----------END-OF-HEADERS--------------");

		ContentWriter.writeContentToFile(httpresponse);
		// Printing the status line
		System.out.println(httpresponse.getStatusLine());

	}

	private void printHeaders(Header[] headers) {
		for (Header header : headers) {
			System.out.println("	" + header.getName() + " : " + header.getValue());
		}
		System.out.println();
	}

	

}
