package com.expertrec.service.generators;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class ContentWriter {

	public static void writeContentToFile(HttpResponse httpresponse) throws UnsupportedOperationException, IOException {

		HttpEntity entity = httpresponse.getEntity();
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileName = "response-"+timestamp.getTime()+".txt";
		File file = new File("httpResponseContent/"+fileName);
		PrintWriter writer = new PrintWriter(file);
		while (sc.hasNext()) {
			writer.println(sc.nextLine());
		}
		System.out.println();
		writer.close();
		
		Header contentHeader = httpresponse.getFirstHeader("content-type");
		String contentType = contentHeader.getValue();
		System.out.println("The response is written in httpResponseContent/"+fileName);
		if(contentType.contains("html"))
			HtmlParser.printPageTitle(fileName);
	}
}
