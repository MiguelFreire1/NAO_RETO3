import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SCOPUS {
	
	public static void main(String[] args) throws IOException, ParseException {
		ArrayList<String> UrlAutores = new ArrayList <String>();
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(35227147500)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(35231679900)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(55746045300)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(36077269600)&apiKey=1723d581deac1b94f476629c7a66c857");
	    UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(36066858500)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(14832325400)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(36062618800)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(13606062200)&apiKey=1723d581deac1b94f476629c7a66c857");
		UrlAutores.add("https://api.elsevier.com/content/search/scopus?query=AU-ID(35391040400)&apiKey=1723d581deac1b94f476629c7a66c857");
		
		for (int i = 0; i < UrlAutores.size(); i++) {
			String URlA= UrlAutores.get(i);
			//URl de la web y método de extracción de los datos GET
			URL url1 = new URL(URlA);
			HttpURLConnection cx=(HttpURLConnection) url1.openConnection();
			cx.setRequestMethod("GET");
		
		//Extraemos los datos
		InputStream strm=cx.getInputStream();
		//Leemos todos los bits 
		byte[] arrStream=strm.readAllBytes();
		
		String cntJson="";
		
		for(byte tmp:arrStream)
			cntJson+=(char)tmp;
		
		//realizamos lectura del JSON
		JSONParser parser = new JSONParser();
		JSONObject datosURL = (JSONObject) parser.parse(cntJson);
		
		
		JSONObject resultados = (JSONObject) datosURL.get("search-results");
		String document_count = (String) resultados.get("opensearch:totalResults");
		JSONObject query = (JSONObject) resultados.get("opensearch:Query");
		String ID = (String) query.get("@searchTerms");
		System.out.println("ID:"+ ID);
		
		JSONArray links = (JSONArray) resultados.get("link");
		JSONObject link= (JSONObject) links.get(0);
		String Url = (String) link.get("@href");
		System.out.println("Url:"+ Url);
		System.out.println("Document Count:"+ document_count);
		JSONArray Entrys = (JSONArray) resultados.get("entry");
		JSONObject entry= (JSONObject) Entrys.get(0);
		String Eid = (String) entry.get("eid");
		System.out.println("Eid:"+ Eid);
		JSONArray Affiliations = (JSONArray) entry.get("affiliation");
		JSONObject Affiliation= (JSONObject) Affiliations.get(0);
		 String affiliationName = (String) Affiliation.get("affilname");
		 String affiliationCity = (String) Affiliation.get("affiliation-city");	
		 String affiliationContry = (String) Affiliation.get("affiliation-country");
		 System.out.println("Affiliation Name:"+ affiliationName);
		 System.out.println("Affiliation City:"+ affiliationCity);
		 System.out.println("Affiliation Contry:"+ affiliationContry);}	
		}

		
}
	
					
	 

	

