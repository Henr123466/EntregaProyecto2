package clases;

import java.util.HashMap;

import importar.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Jugador extends ClasePadre {
	private HashMap<String, Animacion> animaciones;
	private String animacionActual;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private int puntuacion= 0;
	
	
	public Jugador(int x, int y, String indiceImagen, int velocidad, String animacionActual) {
		super(x, y, indiceImagen, velocidad);
		this.animacionActual = animacionActual;
		inicializarAnimaciones();
	}

	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}

		
		public void actualizarAnimacion(double t) {
			Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
			this.xImagen = (int)coordenadasActuales.getX();
			this.yImagen = (int)coordenadasActuales.getY();
			this.anchoImagen = (int)coordenadasActuales.getWidth();
			this.altoImagen = (int)coordenadasActuales.getHeight();
			
		}
		
		public void mover(){
			if (this.x>=1100)
				this.x = -100;
			if (Juego.derecha)
				this.x+=velocidad;
			
			if (Juego.izquierda)
				this.x-=velocidad;
			
			if (Juego.arriba)
				this.y-=velocidad;
			
			if (Juego.abajo)
				this.y+=velocidad;
		}
		
		
		public void pintar(GraphicsContext graficos) {
			graficos.drawImage(
					Juego.imagenes.get(this.indiceImagen), 
					this.xImagen, this.yImagen, 
					this.anchoImagen, this.altoImagen,
					this.x, this.y,
					this.anchoImagen, this.altoImagen
			);
			graficos.fillText("Puntuacion " + puntuacion, 10, 10);
		}
		
		public Rectangle obtenerRectangulo() {
			return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
		
		}
		public void inicializarAnimaciones() {
				animaciones = new HashMap<String, Animacion>();
				Rectangle coordenadasCorrer[]= {
						new Rectangle(14, 147,68,102),
						new Rectangle(84,147, 68,102),
						new Rectangle(160,147, 68,102),
						new Rectangle(230,147, 68,102),
						new Rectangle(297,147, 68,102),
						new Rectangle(365,147, 68,102)
					
				};
				
				Animacion animacionCorrer = new Animacion("correr",coordenadasCorrer,0.05);
				animaciones.put("correr",animacionCorrer);
				
				Rectangle coordenadasDescanso[] = {
						new Rectangle(37, 24, 59,102),
						new Rectangle(110,24, 59,102),
						new Rectangle(183,24, 59,102),
						new Rectangle(254,24, 59,102)
				};
				Animacion animacionDescanso = new Animacion("descanso",coordenadasDescanso,0.2);
				animaciones.put("descanso",animacionDescanso);
		}
	
		public void verificarColisiones(Item item) {
			if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
					System.out.println("Estan colisionando");
					if (!item.isCapturado())
						this.puntuacion++;
					item.setCapturado(true);				
			}
		}
	
}
