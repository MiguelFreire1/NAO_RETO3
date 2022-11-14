package Main;

import Modelo.Autor;

import Vista.VistaCiclo3;
import Controlador.ControladorCiclo3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ciclo3Main {
	private static String ID;
	private static String Url;
	private static String Eid;
	private static String document_count;
	private static String affiliationName;
	private static String affiliationCity;
	private static String affiliationContry;

	public static void main(String[] args) throws ParseException, IOException {	
			// objeto vista, y modelo creado con el método estático 
			Autor modelo= llenarDatos();
			VistaCiclo3 vista= new VistaCiclo3();
			
			//se crea un objeto controlador y se le pasa el modelo y la vista
			ControladorCiclo3 controlador= new ControladorCiclo3(modelo, vista);
				
			// se muestra los datos 
			controlador.actualizarVista();		
			}
			//método estático que retorna el cliente con sus datos
			private static Autor  llenarDatos() throws IOException, ParseException { 
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
				document_count = (String) resultados.get("opensearch:totalResults");
				JSONObject query = (JSONObject) resultados.get("opensearch:Query");
			    ID = (String) query.get("@searchTerms");
				
				JSONArray links = (JSONArray) resultados.get("link");
				JSONObject link= (JSONObject) links.get(0);
				Url = (String) link.get("@href");
				JSONArray Entrys = (JSONArray) resultados.get("entry");
				JSONObject entry= (JSONObject) Entrys.get(0);
				Eid = (String) entry.get("eid");
				JSONArray Affiliations = (JSONArray) entry.get("affiliation");
				JSONObject Affiliation= (JSONObject) Affiliations.get(0);
			    affiliationName = (String) Affiliation.get("affilname");
			    affiliationCity = (String) Affiliation.get("affiliation-city");	
				affiliationContry = (String) Affiliation.get("affiliation-country");
				
				Autor entrada = new Autor();
				entrada.setUrl(ID);
				entrada.setUrl(Url);
				entrada.setEid(Eid);
				entrada.setdocument_count(document_count);
				entrada.setaffiliationName(affiliationName);
				entrada.setaffiliationCity(affiliationCity);
				entrada.setaffiliationContry(affiliationContry);
				return entrada;
				}
				
			}
}
