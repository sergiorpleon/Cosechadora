package cu.juego.implementacion.cosechadora;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;
import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.Pixmap;
import cu.juego.implementacion.Input.TouchEvent;

public class PantallaJuego extends Pantalla {

	enum EstadoJuego {
		Preparado, Ejecutandose, Over, FinJuego
	}

	EstadoJuego estado = EstadoJuego.Preparado;
	Mundo mundo;

	public PantallaJuego(Juego juego) {
		super(juego);
		mundo = new Mundo();

	}

	public void updateReady(List<TouchEvent> touchEvents) {

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			// poner a falaso el enMov
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.x < 370 && event.x > -50) {
					if (event.y > 50 && event.y < 470) {
						if (mundo.cannaveral.iniciar(event.x, event.y)) {
							Configuraciones.enmov = true;
							estado = EstadoJuego.Ejecutandose;
						}
					}
				}
				// mundo.enMov(false, event.x, event.y);
			}

			// si sentido es o poner izq, der abaj o arrib
			// chequear posicion y mover si sentido ok y distancia con perro ok
			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				if (event.x < 370 && event.x > -50) {
					if (event.y > 50 && event.y < 470) {
						// mundo.moverPerro(event.x, event.y);
					}
				}
			}
			// verificar que cailla es del perro y poner enMov = true
			if (event.type == TouchEvent.TOUCH_DOWN) {
				// if (event.x < 64 && event.y > 416) {
				if (event.x < 370 && event.x > -50) {
					if (event.y > 50 && event.y < 470) {
						// mundo.enMov(true, event.x, event.y);
						// guardar punto de presion
						// mundo.cannaveral.inix = event.x;
						// mundo.cannaveral.iniy = event.y;

					}
				}

			}
		}
	}

	public void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// Graficos g =juego.getGraficos();
		// List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		// juego.getInput().getkeyEvents();

		if (Configuraciones.enmov == false) {
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);

				// poner a falaso el enMov
				if (event.type == TouchEvent.TOUCH_UP) {
					if (event.x > 260) {
						if (event.y < 60) {
							if (Configuraciones.soundEnabled) {
								Assets.clic.play(1);
							}
							mundo = new Mundo();
							estado = EstadoJuego.Preparado;

						}
					}

					// boton abajo
					if (event.x < 370 && event.x > -50) {
						if (event.y > 50 && event.y < 470) {
							mundo.cannaveral.finx = event.x;
							mundo.cannaveral.finy = event.y;
							mundo.cannaveral.mover();
						}
					}
				}

				// si sentido es o poner izq, der abaj o arrib
				// chequear posicion y mover si sentido ok y distancia con perro
				// ok
				if (event.type == TouchEvent.TOUCH_DRAGGED) {
					if (event.x < 370 && event.x > -50) {
						if (event.y > 50 && event.y < 470) {
							// mundo.moverPerro(event.x, event.y);
						}
					}
				}
				// verificar que cailla es del perro y poner enMov = true
				if (event.type == TouchEvent.TOUCH_DOWN) {
					// if (event.x < 64 && event.y > 416) {
					if (event.x < 370 && event.x > -50) {
						if (event.y > 50 && event.y < 470) {
							// mundo.enMov(true, event.x, event.y);
							// guardar punto de presion
							mundo.cannaveral.inix = event.x;
							mundo.cannaveral.iniy = event.y;

						}
					}
				}
			}
		}

		mundo.update(deltaTime);

		if (mundo.finalJuego != 0) {
			estado = EstadoJuego.FinJuego;
		}

	}

	public void updatePause(List<TouchEvent> touchEvents) {
		// Graficos g =juego.getGraficos();
		// List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		// juego.getInput().getkeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.y > 0 && event.y <= 480) {
					if (Configuraciones.soundEnabled) {
						Assets.clic.play(1);
					}
					mundo = new Mundo();
					estado = EstadoJuego.Preparado;
					return;
				}

			}
		}
	}

	public void updateGameOver(List<TouchEvent> touchEvents) {
		// Graficos g =juego.getGraficos();
		// List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		// juego.getInput().getkeyEvents();

		if (mundo.finalJuego == 1) {
			Configuraciones.nuevoNivel = false;
			// if (Configuraciones.descubierto == Control.nivel) {
			// Configuraciones.descubierto++;
			// Configuraciones.nuevoNivel = true;
			// }
			if (Configuraciones.niveles[Configuraciones.numeroNivel] == 4) {
				// Comprobar numero de estrellas
				Configuraciones.niveles[Configuraciones.numeroNivel] = 3;
			}
			Configuraciones.guardarMundo();
			
			
			juego.nuevo(Configuraciones.nuevoNivel);
			mundo.finalJuego = 0;

		} else if (mundo.finalJuego == 2) {
			estado = EstadoJuego.Over;
			mundo.finalJuego = 0;
		}
	}

	@Override
	public void update(float deltaTime) {
		// Graficos g =juego.getGraficos();
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getkeyEvents();

		if (Control.isback) {
			synchronized (this) {
				Control.isback = false;
			}
			juego.setScreen(new MainMenuScreen(juego));
			if (Configuraciones.soundEnabled) {
				Assets.clic.play(1);
			}
		} else {

			if (estado == EstadoJuego.Preparado) {
				updateReady(touchEvents);
			}
			if (estado == EstadoJuego.Ejecutandose) {
				updateRunning(touchEvents, deltaTime);
			}
			if (estado == EstadoJuego.Over) {
				updatePause(touchEvents);
			}
			if (estado == EstadoJuego.FinJuego) {
				updateGameOver(touchEvents);
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.fondo, 0, 0);
		g.drawPixmap(Assets.recorte, 10, 125);
		g.drawPixmap(Assets.reset, 270, 10);
		// Log.d("texto ",(String)juego.getContext().getText(R.string.level));
		g.drawPixmap(Assets.level, 10, 10);
		if ((Configuraciones.numeroNivel + 1) < 10) {
			g.drawPixmap(Assets.numero, 170, 10,
					40 * (Configuraciones.numeroNivel + 1), 0, 40, 40);
		} else if ((Configuraciones.numeroNivel + 1) < 20) {
			g.drawPixmap(Assets.numero, 170, 10, 40, 0, 40, 40);
			g.drawPixmap(Assets.numero, 200, 10,
					40 * ((Configuraciones.numeroNivel + 1) - 10), 0, 40, 40);
		} else {
			g.drawPixmap(Assets.numero, 170, 10, 80, 0, 40, 40);
			g.drawPixmap(Assets.numero, 200, 10,
					40 * ((Configuraciones.numeroNivel + 1) - 20), 0, 40, 40);
		}

		if (estado == EstadoJuego.Preparado) {
			drawWorldInicial(mundo);
			drawReadyUI();
		}
		if (estado == EstadoJuego.Ejecutandose) {
			drawWorld(mundo);
			drawRunningUI();
		}
		if (estado == EstadoJuego.Over) {
			drawWorld(mundo);
			drawPausedUI();
		}
		if (estado == EstadoJuego.FinJuego) {
			drawWorld(mundo);
			drawGameOverUI();
		}

	}

	private void drawWorld(Mundo mundo) {
		Graficos g = juego.getGraficos();
		Cannaveral cannaveral = mundo.cannaveral;
		Pixmap stainPixmap = null;
		int x;
		int y;
		// g.drawPixmap(stainPixmap, x, y);

		int[][] tablero = cannaveral.tablero;
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				y = i * 50 + 50;
				x = j * 50 + -40;
				if (i == cannaveral.fila && j == cannaveral.columna) {
					switch (mundo.cannaveral.direccion) {
					case Cannaveral.ARRIBA:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					case Cannaveral.ABAJO:
						g.drawPixmap(Assets.combinadaarriba, x, y);
						break;
					case Cannaveral.DERECHA:
						g.drawPixmap(Assets.combinadaderecha, x, y);
						break;
					case Cannaveral.IZQUIERDA:
						g.drawPixmap(Assets.combinadaizquierda, x, y);
						break;
					default:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					}
				} else {
					switch (cannaveral.tablero[i][j]) {
					// cerca
					case -1:
						// g.drawPixmap(Assets.cerca, x, y);
						break;
					// libre
					case 0:
						g.drawPixmap(Assets.libre, x, y);

						break;
					// establo
					case 1:
						g.drawPixmap(Assets.canna1, x, y);

						break;
					// obeja
					case 2:
						g.drawPixmap(Assets.canna2, x, y);

						break;
					// perro
					case 3:
						// g.drawPixmap(Assets.garza, x, y);
						if (mundo.cannaveral.isdirecciongarza(i, j)) {
							if (mundo.cannaveral.direcciongarza()) {
								if (mundo.cannaveral.vuelo == 1) {
									g.drawPixmap(Assets.garza, x, y);
								} else if (mundo.cannaveral.vuelo == 2) {
									g.drawPixmap(Assets.garza2, x, y);
								} else {
									g.drawPixmap(Assets.garza, x, y);
								}
							} else {
								g.drawPixmap(Assets.garza, x, y);
							}
						} else {
							g.drawPixmap(Assets.garza, x, y);
						}
						break;
					// arbol
					case 4:
						g.drawPixmap(Assets.arbol, x, y);

						break;
					case 5:
						switch (mundo.cannaveral.direccion) {
						case Cannaveral.ARRIBA:
							g.drawPixmap(Assets.combinadaabajo, x, y);
							break;
						case Cannaveral.ABAJO:
							g.drawPixmap(Assets.combinadaarriba, x, y);
							break;
						case Cannaveral.DERECHA:
							g.drawPixmap(Assets.combinadaderecha, x, y);
							break;
						case Cannaveral.IZQUIERDA:
							g.drawPixmap(Assets.combinadaizquierda, x, y);
							break;
						default:
							g.drawPixmap(Assets.combinadaabajo, x, y);
							break;
						}

						break;
					default:
						break;
					}
				}

			}
		}

		Pixmap headPixmap = null;
	}

	private void drawWorldInicial(Mundo mundo) {
		Graficos g = juego.getGraficos();
		Cannaveral cannaveral = mundo.cannaveral;

		Pixmap stainPixmap = null;

		int x;
		int y;

		int[][] tablero = cannaveral.tablero;
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				y = i * 50 + 50;
				x = j * 50 + -40;
				if (i == cannaveral.arrCombX[0] && j == cannaveral.arrCombY[0]) {
					switch (mundo.cannaveral.direccion) {
					case Cannaveral.ARRIBA:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					case Cannaveral.ABAJO:
						g.drawPixmap(Assets.combinadaarriba, x, y);
						break;
					case Cannaveral.DERECHA:
						g.drawPixmap(Assets.combinadaderecha, x, y);
						break;
					case Cannaveral.IZQUIERDA:
						g.drawPixmap(Assets.combinadaizquierda, x, y);
						break;
					default:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					}
				} else if (i == cannaveral.arrCombX[1]
						&& j == cannaveral.arrCombY[1]) {
					switch (mundo.cannaveral.direccion) {
					case Cannaveral.ARRIBA:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					case Cannaveral.ABAJO:
						g.drawPixmap(Assets.combinadaarriba, x, y);
						break;
					case Cannaveral.DERECHA:
						g.drawPixmap(Assets.combinadaderecha, x, y);
						break;
					case Cannaveral.IZQUIERDA:
						g.drawPixmap(Assets.combinadaizquierda, x, y);
						break;
					default:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					}
				} else if (i == cannaveral.arrCombX[2]
						&& j == cannaveral.arrCombY[2]) {
					switch (mundo.cannaveral.direccion) {
					case Cannaveral.ARRIBA:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					case Cannaveral.ABAJO:
						g.drawPixmap(Assets.combinadaarriba, x, y);
						break;
					case Cannaveral.DERECHA:
						g.drawPixmap(Assets.combinadaderecha, x, y);
						break;
					case Cannaveral.IZQUIERDA:
						g.drawPixmap(Assets.combinadaizquierda, x, y);
						break;
					default:
						g.drawPixmap(Assets.combinadaabajo, x, y);
						break;
					}
				} else {
					switch (cannaveral.tablero[i][j]) {
					// cerca
					case -1:
						// g.drawPixmap(Assets.cerca, x, y);
						break;
					// libre
					case 0:
						g.drawPixmap(Assets.libre, x, y);

						break;
					// establo
					case 1:
						g.drawPixmap(Assets.canna1, x, y);

						break;
					// obeja
					case 2:
						g.drawPixmap(Assets.canna2, x, y);

						break;
					// perro
					case 3:
						if (mundo.cannaveral.direcciongarza()) {
							if (mundo.cannaveral.vuelo == 1) {
								g.drawPixmap(Assets.garza, x, y);
							} else if (mundo.cannaveral.vuelo == 2) {
								g.drawPixmap(Assets.garza2, x, y);
							} else {
								g.drawPixmap(Assets.garza, x, y);
							}
						} else {
							g.drawPixmap(Assets.garza, x, y);
						}

						break;
					// arbol
					case 4:
						g.drawPixmap(Assets.arbol, x, y);

						break;
					case 5:
						switch (mundo.cannaveral.direccion) {
						case Cannaveral.ARRIBA:
							g.drawPixmap(Assets.combinadaabajo, x, y);
							break;
						case Cannaveral.ABAJO:
							g.drawPixmap(Assets.combinadaarriba, x, y);
							break;
						case Cannaveral.DERECHA:
							g.drawPixmap(Assets.combinadaderecha, x, y);
							break;
						case Cannaveral.IZQUIERDA:
							g.drawPixmap(Assets.combinadaizquierda, x, y);
							break;
						default:
							g.drawPixmap(Assets.combinadaabajo, x, y);
							break;
						}
						break;
					default:
						break;
					}
				}

			}
		}
		Pixmap headPixmap = null;
	}

	private void drawReadyUI() {
		Graficos g = juego.getGraficos();

	}

	private void drawRunningUI() {
		Graficos g = juego.getGraficos();

	}

	private void drawPausedUI() {
		Graficos g = juego.getGraficos();

		g.drawPixmap(Assets.over, 20, 200);
		// g.drawLine(0, 416, 480, 416, Color.BLACK);

	}

	private void drawGameOverUI() {
		Graficos g = juego.getGraficos();

	}

	private void drawText(Graficos g, String line, int x, int y) {
		int len = line.length();

		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			if (character == ' ') {
				x += 20;
				continue;
			}

			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}

			// g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;

		}

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if (estado == EstadoJuego.Ejecutandose) {
			// estado = EstadoJuego.Pausado;
		}

		if (mundo.finalJuego != 0) {
			// Configuraciones.addScore(mundo.puntuacion);
			Configuraciones.saved(juego.getFileIO());
		}

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
