package importar;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Item;
import clases.Jugador;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Juego extends Application {
	
     private Scene escena;
     private Group root;
     private Canvas canvas;
     private GraphicsContext graficos;
    // private int puntuacion = 0;
     public static boolean derecha=false;
 	 public static boolean izquierda=false;
 	 public static boolean arriba=false;
 	 public static boolean abajo=false;
 	 private Jugador jugador;
 	 public static HashMap<String, Image> imagenes;
 	 private ArrayList<Tile> tiles;
 	 private Item item;
 	 private Item item2; 
 	 private int[][] mapa = {
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2},
			{0,0,0,0,0,1,2,2,2}
	};
	public static void main(String[] args) {
              launch(args);
	}

	@Override
	public void start(Stage ventana) throws Exception {
	    inicializarComponentes();
	    graficos = canvas.getGraphicsContext2D();
	    root.getChildren().add(canvas);
	    ventana.setScene(escena);
		ventana.setTitle("Mi primer videojuego");
		ventana.show();
		gestionarEventos();
		cicloJuego();
	}
    public void inicializarComponentes() {
    	jugador = new Jugador(0,162,"jugador",10, "correr");
    	root= new Group();
    	escena= new Scene(root,1000,500);
    	canvas= new Canvas(1000,500);
    	imagenes = new HashMap<String,Image>();
    	item = new Item (100,200,0,0,"item");
		item2 = new Item(200,200,0,0,"item");
    	cargarImagenes();
    	cargarTiles();
    	
    }
    public void cargarImagenes() {
		imagenes.put("jugador", new Image("jugador.png"));
		imagenes.put("tilemap", new Image("mapa.png"));
		imagenes.put("item", new Image("item.png"));
	}
   public void pintar() {

	    graficos.setFill(Color.WHITE);
		graficos.fillRect(0, 0, 1000, 500);
		graficos.setFill(Color.BLACK);
		
	    jugador.pintar(graficos);
	    	for (int i=0;i<tiles.size();i++)
	    			tiles.get(i).pintar(graficos);
	    item.pintar(graficos);
		item2.pintar(graficos);
	
}
   public void cargarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<mapa.length; i++) {
			for(int j=0; j<mapa[i].length; j++) {
				if (mapa[i][j]!=0)
					tiles.add(new Tile(mapa[i][j], i*51, j*52, "tilemap",0));
			}
		}
	}
		 
	
   
   public void gestionarEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
					switch (evento.getCode().toString()) {
						case "RIGHT": 
							derecha=true;
							break;
						case "LEFT": 
							izquierda=false;
						break;
						case "UP":
							arriba=true;
							break;
						case "DOWN":
							abajo=true;
							break;
						
					}
			   }			
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				switch (evento.getCode().toString()) {
				case "RIGHT": 
					derecha=false;
					break;
				case "LEFT": 
					izquierda=false;
				break;
				case "UP":
					arriba=false;
					break;
				case "DOWN":
					abajo=false;
					break;
				
			}
				
			}
			
		});
   }
		public void cicloJuego() {
			long tiempoInicial = System.nanoTime();
			AnimationTimer animationTimer = new AnimationTimer() {
				@Override
				public void handle(long tiempoActualNanoSegundos) {
					double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
					pintar();
					actualizar(t);
				    }
			};
			animationTimer.start(); 
			
		}
		public void actualizar(double t){
			jugador.mover();
			jugador.actualizarAnimacion(t);
			jugador.verificarColisiones(item);
			jugador.verificarColisiones(item2);
	}
}

