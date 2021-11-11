package cu.juego.implementacion.cosechadora;


import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.android.AndroidJuego;

import android.app.Activity;

public class JuegoCosechadora extends AndroidJuego {

	@Override
	public Pantalla getStartScreen() {
		// TODO Auto-generated method stub
		return new LoadingScreen(this);
	}
  
	
	
}

























