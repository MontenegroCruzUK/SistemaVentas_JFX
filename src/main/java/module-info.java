module com.ventas.sistemaventas_jfx {
	requires javafx.controls;
	requires javafx.fxml;
	
	
	opens com.ventas.sistemaventas_jfx to javafx.fxml;
	exports com.ventas.sistemaventas_jfx;
	exports com.ventas.sistemaventas_jfx.controller;
	opens com.ventas.sistemaventas_jfx.controller to javafx.fxml;
}