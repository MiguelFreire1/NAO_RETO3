package Controlador;

import Modelo.Autor;
import Vista.VistaCiclo3;

public class ControladorCiclo3 {
	//objetos vista y modelo
	private VistaCiclo3 vista;
	private Autor modelo;

	//constructor para inicializar el modelo y la vista
	public  ControladorCiclo3(Autor modelo, VistaCiclo3 vista) {
		this.modelo = modelo;
		this.vista = vista;
			}
	//getters y setters para el modelo
		public String getID() {
			return modelo.getID();
		}	
		public void setID(String ID) {
			this.modelo.setUrl(ID);
		}
		public String getUrl() {
			return modelo.getUrl();
		}	
		public void setUrl(String Url) {
			this.modelo.setUrl(Url);
		}
		public String getEid() {
			return modelo.getEid();
		}
		public void setEid(String Eid) {
			this.modelo.setEid(Eid);
		}
		public String getdocument_count() {
			return modelo.getdocument_count();
		}
		public void setdocument_count(String document_count) {
			this.modelo.setEid(document_count);
		}
		public String getaffiliationName() {
			return modelo.getaffiliationName();
		}
		public void setaffiliationName(String affiliationName) {
			this.modelo.setaffiliationName(affiliationName);
		}
		public String getaffiliationCity() {
			return modelo.getaffiliationCity();
		}
		public void setaffiliationCity(String affiliationCity) {
			this.modelo.setaffiliationCity(affiliationCity);
		}
		public String getaffiliationContry() {
			return modelo.getaffiliationContry();
		}
		public void setaffiliationContry(String affiliationContry) {
			this.modelo.setaffiliationCity(affiliationContry);
		}
		//pasa el modelo a la vista para presentar los datos
		public void actualizarVista() {
			vista.mostrardatosURL(modelo.getID(),modelo.getUrl(),modelo.getEid(),modelo.getdocument_count(),modelo.getaffiliationName(),modelo.getaffiliationCity(),modelo.getaffiliationContry());
		}
}
