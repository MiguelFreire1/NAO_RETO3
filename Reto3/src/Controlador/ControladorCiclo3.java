package Controlador;

import Modelo.ModeloCiclo3;
import Vista.VistaCiclo3;

public class ControladorCiclo3 {
	//objetos vista y modelo
	private VistaCiclo3 vista;
	private ModeloCiclo3 modelo;

	//constructor para inicializar el modelo y la vista
	public  ControladorCiclo3(ModeloCiclo3 modelo, VistaCiclo3 vista) {
		this.modelo = modelo;
		this.vista = vista;
			}
	//getters y setters para el modelo
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
		public String getTitulo() {
			return modelo.getTitulo();
		}
		public void setTitulo(String Titulo) {
			this.modelo.setTitulo(Titulo);
		}
		public String getCreador() {
			return modelo.getCreador();
		}
		public void setCreador(String Creador) {
			this.modelo.setCreador(Creador);
		}
		public String getFecha() {
			return modelo.getFecha();
		}
		public void setFecha(String Fecha) {
			this.modelo.setFecha(Fecha);
		}
		public String getCuenta() {
			return modelo.getCuenta();
		}
		public void setCuenta(String Cuenta) {
			this.modelo.setCuenta(Cuenta);
		}
		public String getaffiliationName() {
			return modelo.getaffiliationName();
		}
		public void setaffiliationName(String affiliationName) {
			this.modelo.setaffiliationName(affiliationName);
		}
		public String getTipo() {
			return modelo.getTipo();
		}
		public void setTipo(String Tipo) {
			this.modelo.setTipo(Tipo);
		}
		//pasa el modelo a la vista para presentar los datos
		public void actualizarVista() {
			vista.mostrardatosURL(modelo.getUrl(),modelo.getEid(),modelo.getTitulo(),modelo.getCreador(),modelo.getFecha(),modelo.getCuenta(),modelo.getaffiliationName(),modelo.getTipo());
		}
}
