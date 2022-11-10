package Main;

import Modelo.ModeloCiclo3;

import Vista.VistaCiclo3;
import Controlador.ControladorCiclo3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ciclo3Main {
	private static String Url;
	private static String Eid;
	private static String Titulo;
	private static String Creador;
	private static String Cuenta;
	private static String Fecha;
	private static String affiliationName;
	private static String Tipo;

	public static void main(String[] args) throws ParseException, IOException {	
			// objeto vista, y modelo creado con el método estático 
			ModeloCiclo3 modelo= llenarDatos();
			VistaCiclo3 vista= new VistaCiclo3();
			
			//se crea un objeto controlador y se le pasa el modelo y la vista
			ControladorCiclo3 controlador= new ControladorCiclo3(modelo, vista);
				
			// se muestra los datos 
			controlador.actualizarVista();
			
			//Actualizar más entradas
			controlador.setUrl(Url);
			controlador.actualizarVista();
					
			}
			//método estático que retorna el cliente con sus datos
			private static ModeloCiclo3  llenarDatos() throws IOException, ParseException {
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
				 
				try{JSONParser parser = new JSONParser();
				
				JSONObject datosURL = (JSONObject) parser.parse(cntJson);
				
						
				JSONObject resultados = (JSONObject) datosURL.get("search-results");
				JSONArray Entrys = (JSONArray) resultados.get("entry");
				for(Object entry: Entrys) {
				mostrarInformacionEntrys ((JSONObject) entry);
				}
				}
				catch(ParseException e) {
				e.printStackTrace();
				}
					ModeloCiclo3 entrada = new ModeloCiclo3();
					entrada.setUrl(Url);
					entrada.setEid(Eid);
					entrada.setTitulo(Titulo);
					entrada.setCreador(Creador);
					entrada.setFecha(Fecha);
					entrada.setCuenta(Cuenta);
					entrada.setTipo(Tipo);
					return entrada;
				}
			
				
			

				public static  void mostrarInformacionEntrys(JSONObject entry) {
					
					Url = (String) entry.get("prism:url");
				
					Eid = (String) entry.get("eid");

					Titulo = (String) entry.get("dc:title");
						
					Creador = (String) entry.get("dc:creator");
						
					Fecha = (String) entry.get("prism:coverDate");
						
					Cuenta = (String) entry.get("citedby-count");
					
					JSONArray Affiliations = (JSONArray) entry.get("affiliation");
					for(Object Affiliation: Affiliations) {
						mostrarInformacionAffiliation ((JSONObject) Affiliation);
					}
					
					Tipo = (String) entry.get("prism:aggregationType");
				
				
					
				}


				private static void mostrarInformacionAffiliation(JSONObject affiliation) {
								
				String affiliationName = (String) affiliation.get("affilname");		}	
			

}
