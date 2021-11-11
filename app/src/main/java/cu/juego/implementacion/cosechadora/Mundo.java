package cu.juego.implementacion.cosechadora;

import java.util.Random;

import android.util.Log;
import android.widget.Toast;

public class Mundo {
	static final int MUNDO_ANCHO = 7;
	static final int MUNDO_ALTO = 7;
	static final int INCREMENTO_PUNTUACION = 10;
	static final float TICK_INICIAL = 0.3f;
	static final float TICK_DECREMENTO = 0.05f;

	//public boolean enMov;

	public Cannaveral cannaveral;
	// public Locomotora locomotora;
	// public Bagon bagon;
	public static int finalJuego = 0;
	// public int puntuacion = 0;

	// boolean[][] campo = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
	// Random random = new Random();
	float tiempoTick = 0;
	static float tick = TICK_INICIAL;

	public Mundo() {
		Configuraciones.enmov = false;
		cannaveral = new Cannaveral();
		finalJuego = 0;
		// locomotora = new Locomotora();
		tiempoTick = 0;
		tick = TICK_INICIAL;

	}

	public void update(float deltaTime) {
		if (finalJuego!=0) {
			return;
		}

		tiempoTick += deltaTime;

		while (tiempoTick > tick) {

			tiempoTick -= tick;

			// locomotora.avance();
			// if (locomotora.comprobarChoque()) {
			// Log.d("Entro", "Choque BOOM");
			// finalJuego = true;
			// return;
			// }

			// Log.d("Entro", "BOT " + botin.x + " _ " + botin.y);

			// Tripulacion head = locomotora.partes.get(0);
			// if (head.x == bagon.x && head.y == bagon.y) {
			// Log.d("Entro", "Botin facho");
			// puntuacion += INCREMENTO_PUNTUACION;
			// locomotora.abordaje();
			// if (locomotora.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
			// finalJuego = true;
			// return;
			// } else {
			// }

			// if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
			// tick -= TICK_DECREMENTO;
			// }
			// }

			// programar movimiento de combinada
			cannaveral.update();
			if (cannaveral.sinMovimiento() == Cannaveral.GANA) {
				// gano
				finalJuego  = 1;
			} else if (cannaveral.sinMovimiento() == Cannaveral.TRANQUE) {
				// perdio
				finalJuego  = 2;
			}

		}

	}

}
