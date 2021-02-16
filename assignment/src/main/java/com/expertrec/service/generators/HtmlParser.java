package com.expertrec.service.generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HtmlParser {

	private static final Pattern TITLE_TAG = Pattern.compile("\\<title>(.*)\\</title>",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public static void printPageTitle(String fileName) throws FileNotFoundException {
		File myObj = new File("httpResponseContent/" + fileName);
		Scanner myReader = new Scanner(myObj);
		StringBuilder content = new StringBuilder();
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			content.append(data);
		}
		myReader.close();
		Matcher matcher = TITLE_TAG.matcher(content);
		if (matcher.find()) {
			/*
			 * replace any occurrences of whitespace (which may include line feeds and other
			 * uglies) as well as HTML brackets with a space
			 */
			System.out.println("The TITLE of the HTML response is ----");
			String title = matcher.group(1).replaceAll("[\\s\\<>]+", " ").trim();
			System.out.println("	"+title.toUpperCase());
		}
	}

}
