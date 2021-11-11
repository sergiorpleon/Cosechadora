package cu.juego.implementacion.cosechadora;

import java.util.List;


import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.Input.TouchEvent;

public class PantallaMaximasPuntuaciones extends Pantalla{
String[] lines = new String[5];

	public PantallaMaximasPuntuaciones(Juego juego){
		super(juego);
		
		for (int i = 0; i < 5; i++) {
			//lines[i] = "" + (i + 1) + ". " + Configuraciones.highscores[i];
		}
	}
	
	public void dibujarTexto(Graficos g, String line, int x, int y){
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			
			int srcX = 0;
			int srcWidth = 0;
			if(character=='.'){
				srcX = 200;
				srcWidth = 10;
			}else{
				srcX = (character - '0')*20;
				srcWidth = 20;
			}
			
			//g.drawPixmap(Assets.numeros,x, y, srcX, 0, srcWidth, 32);
			x+= srcWidth;
			
		}
		
		
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g =juego.getGraficos();
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getkeyEvents();
		
		
		if(Control.isback){
			synchronized (this) {
				Control.isback = false;
			}
			juego.setScreen(new MainMenuScreen(juego));
			if(Configuraciones.soundEnabled){
				Assets.clic.play(1);
			}
		}else{
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
			//if(event.x < 64 && event.y > 416){
			if(event.x < 84 && event.y > 400){
				juego.setScreen(new MainMenuScreen(juego));
				if(Configuraciones.soundEnabled){
					Assets.clic.play(1);
				}
			}
			}

		}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g = juego.getGraficos();
		
		g.drawPixmap(Assets.fondo, 0, 0);
		//g.drawPixmap(Assets.lineas2, 0, 0);
		//g.drawPixmap(Assets.menuprincipal, 64, 20, 0, 42, 196, 42);
		
		int y=100;
		for (int i = 0; i < 5; i++) {
			//x = 20
			dibujarTexto(g, lines[i], 60, y);
			y+=50;
		}
		//x = 0 , y = 416
		//g.drawPixmap(Assets.botones1, 20, 400, 0, 0, 64,64);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}
