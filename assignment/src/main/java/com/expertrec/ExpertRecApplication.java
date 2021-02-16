package com.expertrec;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.expertrec.service.ExpertrecHttpServiceRequest;
/**
 * @author tarun
 *
 */
public class ExpertRecApplication {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		ExpertrecHttpServiceRequest expertrecHttpServiceRequest = new ExpertrecHttpServiceRequest();
	    expertrecHttpServiceRequest.processRequest(args);
	}

}
 