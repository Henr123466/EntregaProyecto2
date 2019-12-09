package clases;

import importar.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item {
     private boolean capturado;
     private int x;
 	 private int y;
 	 private String indiceImagen;
	
	
 	public Item(int x, int y, int ancho, int alto, String indiceImagen) {
		super();
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
	}

	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(Juego.imagenes.get(indiceImagen), this.x, this.y);
		
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 18, 18);
	}


	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
}
