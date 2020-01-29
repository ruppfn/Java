package com.example.scraping;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App{
	
    public static void main( String[] args ){
        
    	String url = "https://old.reddit.com/";
    	
    	int statusCode = JSoupService.getStatusConnectionCode(url);
    	
    	if(statusCode == 200) {
    		Document doc = JSoupService.getHtmlDocument(url);
    		
    		// thing = contenedor / title = titulo / tagline = contenido / promoted = ad
    		Elements elems = doc.select("div.thing").not("div.promoted");
    		
    		System.out.println("Encontrados: " + elems.size());
    		
    		for(Element elem : elems) {
    			String titulo = elem.getElementsByClass("title").get(1).text();
    			String contenido = elem.getElementsByClass("tagline").text();
    			
    			
    			System.out.println("\n\n" + titulo + "\n\t" + contenido);
    			
    		}
    	} else {
    		System.out.println("El codigo es " + statusCode);
    	}
    	
    }
    
}
