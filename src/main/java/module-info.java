module com.ventas.sistemaventas_jfx {
	requires javafx.controls;
	requires javafx.fxml;
	
	requires org.controlsfx.controls;
	requires MaterialFX;
	requires java.sql;
	
	
	opens com.ventas.sistemaventas_jfx to javafx.fxml;
	exports com.ventas.sistemaventas_jfx;
	exports com.ventas.sistemaventas_jfx.controller;
	opens com.ventas.sistemaventas_jfx.controller to javafx.fxml;
}