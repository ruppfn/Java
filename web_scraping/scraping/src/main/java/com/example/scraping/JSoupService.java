package com.example.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import org.jsoup.Connection.Response;

public class JSoupService {

	
	public static int getStatusConnectionCode(String url) {
		
		Response response = null;
		
		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(5000).ignoreHttpErrors(true).execute();
		} catch (IOException e) {
			System.out.println("Error para obtener el status " + e.getMessage());
		}
		
		return response != null ? response.statusCode() : 0;
	}
	
	public static Document getHtmlDocument(String url) {
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(80000).get();
		} catch (IOException e) {
			System.out.println("Error al obtener la pagina " + e.getMessage());
		}
		
		return doc;
	}
}
