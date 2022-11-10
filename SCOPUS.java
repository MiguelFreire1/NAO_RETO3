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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SCOPUS {
	
	public static void main(String[] args) throws IOException, ParseException {
		//URl de la web y método de extracción de los datos GET
		URL url1 = new URL("https://api.elsevier.com/content/search/scopus?query=35227147500&apiKey=1723d581deac1b94f476629c7a66c857 ");
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
		System.out.println("La url contiene los siguientes datos: "+ datosURL);
		
		JSONObject resultados = (JSONObject) datosURL.get("search-results");
		JSONArray Entrys = (JSONArray) resultados.get("entry");
		System.out.println("Entrys:");
		for(Object Entry: Entrys) {
			mostrarInformacionEntrys ((JSONObject) Entry);
		}
		
	}
		
		

	private static void mostrarInformacionEntrys(JSONObject entry) {
		
		String Url = (String) entry.get("prism:url");
		System.out.println("Url:"+ Url);
		
		String Eid = (String) entry.get("eid");
		System.out.println("Eid:"+ Eid);
		
		String Titulo = (String) entry.get("dc:title");
		System.out.println("Titulo:"+ Titulo);
		
		String Creador = (String) entry.get("dc:creator");
		System.out.println("Creador:"+ Creador);
		
		String Fecha = (String) entry.get("prism:coverDate");
		System.out.println("Fecha:"+ Fecha);
		
		String Cuenta = (String) entry.get("citedby-count");
		System.out.println("Citedby-count:"+ Cuenta);
		
		JSONArray Affiliations = (JSONArray) entry.get("affiliation");
		for(Object Affiliation: Affiliations) {
			mostrarInformacionAffiliation ((JSONObject) Affiliation);
		}
		
		String Tipo = (String) entry.get("prism:aggregationType");
		System.out.println("Tipo:"+ Tipo);
		

	}


	private static void mostrarInformacionAffiliation(JSONObject affiliation) {
		
		
		String affiliationName = (String) affiliation.get("affilname");
		System.out.println("Affiliation Name:"+ affiliationName);
		
		
	}
	

	
}
