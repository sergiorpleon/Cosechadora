package cu.juego.implementacion.cosechadora;

public class Cannaveral {
	public static final int GANA = 10;
	public static final int TRANQUE = 11;
	public static final int JUEGA = 12;

	public static final int COMBINADA = 5;
	public static final int LIBRE = 0;
	public static final int CANNA1 = 1;
	public static final int ARBOL = 4;
	public static final int CANNA2 = 2;
	public static final int CERCA = -1;
	public static final int GARZA = 3;

	public static final int DERECHA = 1;
	public static final int IZQUIERDA = 2;
	public static final int ARRIBA = 3;
	public static final int ABAJO = 4;

	public int vuelo = 1;

	public int inix;
	public int iniy;
	public int finx;
	public int finy;

	public int[] arrCombX;
	public int[] arrCombY;

	public int[][] tablero;

	public int direccion;
	public int olddireccion;

	public int columna;
	public int fila;

	public Cannaveral() {
		tablero = new int[8][8];
		arrCombX = new int[3];
		arrCombY = new int[3];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tablero[i][j] = CANNA1;
			}
		}
		for (int i = 0; i < 8; i++) {
			tablero[i][0] = CERCA;
			tablero[i][7] = CERCA;
			tablero[0][i] = CERCA;
			tablero[7][i] = CERCA;
		}
		// tablero[2][3] = COMBINADA;
		// filaPerro = 2;
		// columnaPerro = 3;
		int nivCargar = (Configuraciones.mundo - 1) * 9 + Control.nivel;

		switch (nivCargar) {
		// campo 1

		case 0:
			arrCombX[0] = 3;
			arrCombY[0] = 3;
			arrCombX[1] = 5;
			arrCombY[1] = 1;
			arrCombX[2] = 1;
			arrCombY[2] = 5;
			break;

		case 1:
			arrCombX[0] = 5;
			arrCombY[0] = 5;
			arrCombX[1] = 2;
			arrCombY[1] = 5;
			arrCombX[2] = 4;
			arrCombY[2] = 2;
			tablero[2][2] = ARBOL;
			tablero[3][4] = ARBOL;

			break;

		case 2:
			arrCombX[0] = 2;
			arrCombY[0] = 5;
			arrCombX[1] = 1;
			arrCombY[1] = 1;
			arrCombX[2] = 4;
			arrCombY[2] = 2;
			tablero[6][3] = ARBOL;
			tablero[4][4] = ARBOL;
			break;
		case 3:
			arrCombX[0] = 2;
			arrCombY[0] = 3;
			arrCombX[1] = 4;
			arrCombY[1] = 4;
			arrCombX[2] = 5;
			arrCombY[2] = 5;
			tablero[2][2] = ARBOL;
			tablero[4][3] = ARBOL;
			tablero[3][5] = ARBOL;
			break;
			
		case 4:
			arrCombX[0] = 5;//3;
			arrCombY[0] = 2;//4;
			arrCombX[1] = 4;//5;
			arrCombY[1] = 2;//3;
			arrCombX[2] = 3;//5;
			arrCombY[2] = 5;//1;
			tablero[2][2] = ARBOL;  //[5][2] = ARBOL;
			tablero[4][3] = ARBOL; //[4][2] = ARBOL;
			tablero[6][4] = ARBOL;
			break;
	
		case 5:
			arrCombX[0] = 4;
			arrCombY[0] = 2;//3
			arrCombX[1] = 1;
			arrCombY[1] = 6;
			arrCombX[2] = 3;//4
			arrCombY[2] = 2;
			tablero[5][5] = ARBOL;
			tablero[3][4] = ARBOL;
			break;

		
		case 6:
			arrCombX[0] = 5;
			arrCombY[0] = 2;
			arrCombX[1] = 5;
			arrCombY[1] = 5;
			arrCombX[2] = 2;
			arrCombY[2] = 3;
			tablero[3][1] = ARBOL;
			tablero[2][5] = ARBOL;
			tablero[4][3] = ARBOL;
			tablero[4][4] = ARBOL;
			tablero[4][6] = ARBOL;
			tablero[5][6] = ARBOL;
			break;

		case 7:
			arrCombX[0] = 2;
			arrCombY[0] = 3;
			arrCombX[1] = 4;
			arrCombY[1] = 3;
			arrCombX[2] = 3;
			arrCombY[2] = 5;
			tablero[6][3] = ARBOL;
			tablero[6][4] = ARBOL;
			tablero[4][4] = ARBOL;

			break;
			
		case 8:
			arrCombX[0] = 5;
			arrCombY[0] = 5;
			arrCombX[1] = 2;
			arrCombY[1] = 2;
			arrCombX[2] = 3;
			arrCombY[2] = 5;
			tablero[6][3] = ARBOL;
			tablero[1][3] = ARBOL;
			tablero[2][5] = ARBOL;

			break;

		

		// campo2
		case 9:
			arrCombX[0] = 3;
			arrCombY[0] = 2;
			arrCombX[1] = 4;
			arrCombY[1] = 5;
			arrCombX[2] = 5;
			arrCombY[2] = 6;
			tablero[4][3] = CANNA2;
			tablero[4][4] = CANNA2;

			break;

		case 10:
			arrCombX[0] = 5;
			arrCombY[0] = 2;
			arrCombX[1] = 3;
			arrCombY[1] = 3;
			arrCombX[2] = 2;
			arrCombY[2] = 5;
			tablero[1][1] = ARBOL;
			tablero[1][2] = ARBOL;
			tablero[3][6] = ARBOL;
			tablero[4][5] = CANNA2;
			break;

		case 11:
			arrCombX[0] = 2;
			arrCombY[0] = 2;
			arrCombX[1] = 3;
			arrCombY[1] = 3;
			arrCombX[2] = 5;
			arrCombY[2] = 5;
			tablero[1][1] = ARBOL;
			tablero[1][2] = ARBOL;
			tablero[2][1] = ARBOL;
			tablero[5][2] = ARBOL;
			tablero[4][6] = ARBOL;
			tablero[3][2] = CANNA2;
			tablero[3][5] = CANNA2;
			break;

		case 12:
			arrCombX[0] = 4;
			arrCombY[0] = 3;
			arrCombX[1] = 2;
			arrCombY[1] = 2;
			arrCombX[2] = 5;
			arrCombY[2] = 5;
			tablero[1][5] = ARBOL;
			tablero[1][6] = ARBOL;
			tablero[2][6] = ARBOL;
			tablero[4][4] = ARBOL;
			tablero[2][4] = CANNA2;
			tablero[3][5] = CANNA2;
			break;

		case 13:
			arrCombX[0] = 4;
			arrCombY[0] = 2;
			arrCombX[1] = 3;
			arrCombY[1] = 1;
			arrCombX[2] = 5;
			arrCombY[2] = 5;
			tablero[5][1] = ARBOL;
			tablero[6][1] = ARBOL;
			tablero[3][4] = ARBOL;
			tablero[4][3] = CANNA2;
			tablero[4][4] = CANNA2;
			tablero[5][4] = CANNA2;
			// tablero[4][4] = CANNA2;
			tablero[6][4] = CANNA2;
			tablero[6][5] = CANNA2;
			break;

		case 14:
			arrCombX[0] = 6;
			arrCombY[0] = 1;
			arrCombX[1] = 4;
			arrCombY[1] = 2;
			arrCombX[2] = 5;
			arrCombY[2] = 5;
			tablero[2][2] = ARBOL;
			tablero[3][2] = ARBOL;
			tablero[2][3] = ARBOL;
			tablero[2][5] = ARBOL;
			tablero[5][6] = ARBOL;
			tablero[6][6] = ARBOL;
			tablero[3][4] = CANNA2;
			tablero[4][3] = CANNA2;
			tablero[4][4] = CANNA2;
			tablero[4][5] = CANNA2;
			break;

		case 15:
			arrCombX[0] = 4;
			arrCombY[0] = 3;
			arrCombX[1] = 4;
			arrCombY[1] = 6;
			arrCombX[2] = 3;
			arrCombY[2] = 1;
			tablero[3][3] = ARBOL;
			tablero[5][5] = ARBOL;
			tablero[2][4] = CANNA2;
			tablero[5][3] = CANNA2;
			break;

		case 16:
			arrCombX[0] = 5;
			arrCombY[0] = 5;
			arrCombX[1] = 3;
			arrCombY[1] = 2;
			arrCombX[2] = 1;
			arrCombY[2] = 2;
			tablero[5][2] = ARBOL;
			tablero[1][5] = ARBOL;
			tablero[1][6] = ARBOL;
			tablero[2][6] = ARBOL;

			tablero[2][4] = CANNA2;
			tablero[3][4] = CANNA2;
			tablero[3][5] = CANNA2;
			break;

		case 17:
			arrCombX[0] = 2;
			arrCombY[0] = 5;
			arrCombX[1] = 5;
			arrCombY[1] = 6;
			arrCombX[2] = 3;
			arrCombY[2] = 1;
			tablero[2][2] = ARBOL;
			tablero[3][2] = ARBOL;
			tablero[4][4] = ARBOL;
			tablero[4][5] = ARBOL;

			tablero[1][3] = CANNA2;
			tablero[1][4] = CANNA2;
			tablero[2][3] = CANNA2;
			tablero[3][3] = CANNA2;
			tablero[4][3] = CANNA2;
			tablero[5][3] = CANNA2;
			tablero[5][4] = CANNA2;
			break;

		// campo3 piedra
		case 18:
			arrCombX[0] = 1;
			arrCombY[0] = 3;
			arrCombX[1] = 4;
			arrCombY[1] = 5;
			arrCombX[2] = 6;
			arrCombY[2] = 3;
			tablero[2][5] = ARBOL;
			tablero[3][3] = ARBOL;

			tablero[4][1] = GARZA;
			break;

		case 19:
			arrCombX[0] = 4;
			arrCombY[0] = 3;
			arrCombX[1] = 5;
			arrCombY[1] = 1;
			arrCombX[2] = 3;
			arrCombY[2] = 3;
			tablero[1][4] = ARBOL;
			tablero[2][4] = ARBOL;

			tablero[1][1] = GARZA;
			tablero[3][1] = GARZA;
			tablero[4][5] = GARZA;
			tablero[6][2] = GARZA;
			tablero[5][6] = GARZA;
			break;
		case 20:
			arrCombX[0] = 4;
			arrCombY[0] = 1;
			arrCombX[1] = 3;
			arrCombY[1] = 3;
			arrCombX[2] = 6;
			arrCombY[2] = 5;
			tablero[2][2] = ARBOL;
			tablero[4][3] = ARBOL;
			tablero[2][3] = ARBOL;
			tablero[4][4] = ARBOL;

			tablero[1][5] = GARZA;
			tablero[6][2] = GARZA;
			break;
		case 21:
			arrCombX[0] = 3;
			arrCombY[0] = 2;
			arrCombX[1] = 4;
			arrCombY[1] = 4;
			arrCombX[2] = 6;
			arrCombY[2] = 6;
			tablero[3][6] = GARZA;
			tablero[4][1] = GARZA;
			tablero[6][2] = GARZA;
			tablero[6][4] = GARZA;
			break;
		case 22:
			arrCombX[0] = 3;
			arrCombY[0] = 3;
			arrCombX[1] = 4;
			arrCombY[1] = 5;
			arrCombX[2] = 4;
			arrCombY[2] = 2;
			tablero[1][2] = GARZA;
			tablero[4][1] = GARZA;
			tablero[6][1] = GARZA;
			tablero[5][6] = GARZA;
			break;
		case 23:
			arrCombX[0] = 3;
			arrCombY[0] = 3;
			arrCombX[1] = 6;
			arrCombY[1] = 5;
			arrCombX[2] = 5;
			arrCombY[2] = 2;
			tablero[2][1] = GARZA;
			tablero[3][1] = GARZA;
			tablero[4][6] = GARZA;
			tablero[1][2] = GARZA;
			tablero[1][4] = GARZA;
			tablero[2][6] = GARZA;
			tablero[4][6] = GARZA;
			break;
		case 24:
			arrCombX[0] = 4;
			arrCombY[0] = 3;
			arrCombX[1] = 2;
			arrCombY[1] = 5;
			arrCombX[2] = 5;
			arrCombY[2] = 5;
			tablero[5][1] = GARZA;
			tablero[3][3] = GARZA;
			tablero[6][3] = GARZA;

			break;
		case 25:
			arrCombX[0] = 3;
			arrCombY[0] = 4;
			arrCombX[1] = 2;
			arrCombY[1] = 5;
			arrCombX[2] = 4;
			arrCombY[2] = 4;
			tablero[1][2] = GARZA;
			tablero[2][1] = GARZA;
			tablero[3][2] = GARZA;
			tablero[4][1] = GARZA;
			tablero[3][3] = GARZA;
			tablero[5][3] = GARZA;

			break;
		case 26:
			arrCombX[0] = 5;
			arrCombY[0] = 3;
			arrCombX[1] = 3;
			arrCombY[1] = 5;
			arrCombX[2] = 3;
			arrCombY[2] = 4;
			tablero[1][2] = GARZA;
			tablero[1][4] = GARZA;
			tablero[2][1] = GARZA;
			tablero[2][3] = GARZA;
			tablero[2][6] = GARZA;
			tablero[3][1] = GARZA;
			tablero[6][5] = GARZA;

			break;
		// campo doble
		case 27:
			arrCombX[0] = 2;
			arrCombY[0] = 2;
			arrCombX[1] = 2;
			arrCombY[1] = 4;
			arrCombX[2] = 4;
			arrCombY[2] = 3;
			tablero[5][6] = GARZA;
			tablero[5][4] = CANNA2;
			tablero[4][4] = CANNA2;
			tablero[6][3] = ARBOL;
			tablero[3][5] = ARBOL;

			break;

		case 28:
			arrCombX[0] = 1;
			arrCombY[0] = 3;
			arrCombX[1] = 4;
			arrCombY[1] = 3;
			arrCombX[2] = 4;
			arrCombY[2] = 4;
			tablero[2][2] = GARZA;
			tablero[2][1] = GARZA;
			tablero[3][4] = CANNA2;
			tablero[6][1] = ARBOL;
			tablero[2][5] = ARBOL;

			break;
	
			
		case 29:
			arrCombX[0] = 5;
			arrCombY[0] = 4;
			arrCombX[1] = 3;
			arrCombY[1] = 2;
			arrCombX[2] = 2;
			arrCombY[2] = 4;
			tablero[5][1] = GARZA;
			tablero[1][2] = GARZA;
			tablero[2][6] = GARZA;
			tablero[5][3] = CANNA2;
			tablero[6][4] = ARBOL;

			break;

		case 30:
			arrCombX[0] = 1;
			arrCombY[0] = 2;
			arrCombX[1] = 5;
			arrCombY[1] = 6;
			arrCombX[2] = 4;
			arrCombY[2] = 4;
			tablero[5][2] = GARZA;
			tablero[2][6] = GARZA;
			tablero[2][2] = CANNA2;
			tablero[2][3] = CANNA2;
			tablero[1][1] = ARBOL;

			break;

		case 31:
			arrCombX[0] = 6;
			arrCombY[0] = 4;
			arrCombX[1] = 5;
			arrCombY[1] = 1;
			arrCombX[2] = 4;
			arrCombY[2] = 3;
			tablero[2][1] = GARZA;
			tablero[2][3] = GARZA;
			tablero[3][2] = CANNA2;
			tablero[3][3] = CANNA2;
			tablero[5][5] = CANNA2;
			tablero[3][5] = ARBOL;

			break;
		case 32:
			arrCombX[0] = 1;
			arrCombY[0] = 1;
			arrCombX[1] = 2;
			arrCombY[1] = 2;
			arrCombX[2] = 2;
			arrCombY[2] = 4;
			tablero[2][6] = GARZA;
			tablero[6][2] = GARZA;
			tablero[6][4] = GARZA;
			tablero[3][1] = GARZA;
			tablero[5][4] = GARZA;
			tablero[5][5] = GARZA;
			tablero[2][5] = CANNA2;
			tablero[3][5] = CANNA2;
			tablero[1][6] = ARBOL;

			break;
		case 33:
			arrCombX[0] = 3;
			arrCombY[0] = 6;
			arrCombX[1] = 5;
			arrCombY[1] = 4;
			arrCombX[2] = 1;
			arrCombY[2] = 1;
			tablero[5][2] = GARZA;
			tablero[3][2] = GARZA;
			tablero[2][4] = GARZA;
			tablero[2][5] = CANNA2;
			tablero[5][5] = CANNA2;
			tablero[6][6] = ARBOL;

			break;

		
		case 34:
			arrCombX[0] = 5;
			arrCombY[0] = 5;
			arrCombX[1] = 3;
			arrCombY[1] = 3;
			arrCombX[2] = 3;
			arrCombY[2] = 5;
			tablero[6][3] = GARZA;
			tablero[2][3] = GARZA;
			tablero[4][6] = GARZA;
			tablero[2][2] = CANNA2;
			tablero[3][2] = CANNA2;
			tablero[5][2] = CANNA2;
			tablero[5][3] = CANNA2;
			tablero[1][5] = ARBOL;
			tablero[1][6] = ARBOL;
			tablero[4][1] = ARBOL;
			tablero[1][1] = ARBOL;
			break;

		case 35:
			arrCombX[0] = 4;
			arrCombY[0] = 3;
			arrCombX[1] = 1;
			arrCombY[1] = 1;
			arrCombX[2] = 3;
			arrCombY[2] = 6;
			tablero[3][2] = GARZA;
			tablero[5][3] = GARZA;
			tablero[3][4] = CANNA2;
			tablero[3][5] = CANNA2;
			tablero[4][4] = CANNA2;
			tablero[4][5] = CANNA2;
			tablero[5][5] = CANNA2;
			tablero[6][6] = ARBOL;
			

			break;
		default:
			break;
		}

	}

	public boolean iniciar(int x, int y) {
		boolean resultado = false;
		int fil = (y - 40) / 50;
		int col = (x + 50) / 50;
		if (fil == arrCombX[0] && col == arrCombY[0]) {
			fila = fil;
			columna = col;
			resultado = true;
		}
		if (fil == arrCombX[1] && col == arrCombY[1]) {
			fila = fil;
			columna = col;
			if (!Configuraciones.ganado[Configuraciones.numeroNivel]) {
				if (Configuraciones.niveles[Configuraciones.numeroNivel] == 2) {
					Configuraciones.niveles[Configuraciones.numeroNivel] = 0;
					Configuraciones.guardarMundo();
					Configuraciones.cargarMundo();
				} else if(Configuraciones.niveles[Configuraciones.numeroNivel] != 0){
					Configuraciones.niveles[Configuraciones.numeroNivel] = 1;
					Configuraciones.guardarMundo();
					Configuraciones.cargarMundo();
				}
				
			}
			resultado = true;
		}
		if (fil == arrCombX[2] && col == arrCombY[2]) {
			fila = fil;
			columna = col;
			if (!Configuraciones.ganado[Configuraciones.numeroNivel]) {
				if (Configuraciones.niveles[Configuraciones.numeroNivel] == 1) {
					Configuraciones.niveles[Configuraciones.numeroNivel] = 0;
					Configuraciones.guardarMundo();
					Configuraciones.cargarMundo();
				} else if(Configuraciones.niveles[Configuraciones.numeroNivel] != 0){
					Configuraciones.niveles[Configuraciones.numeroNivel] = 2;
					Configuraciones.guardarMundo();
					Configuraciones.cargarMundo();
				}
			}
			resultado = true;
		}
		if (resultado) {
			if (tablero[fila][columna] == CANNA1) {
				tablero[fila][columna] = LIBRE;
			}
			if (tablero[fila][columna] == CANNA2) {
				tablero[fila][columna] = CANNA1;
			}
		}
		return resultado;
	}

	public void mover() {
		// distancia x mayor distancia y
		if (Math.abs(inix - finx) > Math.abs(iniy - finy)) {
			// mov horizontal
			if (Math.abs(inix - finx) > 40) {
				// mov derecha
				if (inix < finx) {
					Configuraciones.enmov = true;

					direccion(DERECHA);
				}
				// mov derecha
				if (inix > finx) {
					Configuraciones.enmov = true;

					direccion(IZQUIERDA);
				}
			}
		} else
		// distancia y mayor distancia x
		if (Math.abs(inix - finx) < Math.abs(iniy - finy)) {
			// mov vertical

			if (Math.abs(iniy - finy) > 40) {
				// mov arriba
				if (iniy < finy) {
					Configuraciones.enmov = true;
					direccion(ARRIBA);
				}
				// mov abajo
				if (iniy > finy) {
					Configuraciones.enmov = true;
					direccion(ABAJO);
				}
			}
		}
	}

	public void direccion(int dir) {
		int incx = 0;
		int incy = 0;
		switch (dir) {
		case ARRIBA:
			incx = 1;
			break;
		case ABAJO:
			incx = -1;
			break;
		case DERECHA:
			incy = 1;
			break;
		case IZQUIERDA:
			incy = -1;
			break;
		default:
			break;
		}
		if (incx != 0 || incy != 0) {
			if (tablero[fila + incx][columna + incy] == CANNA1
					|| tablero[fila + incx][columna + incy] == CANNA2) {

				olddireccion = direccion;
				direccion = dir;

				// este if de comienzo del update lo pase para aca
				if (olddireccion != direccion) {
					// caso de que se detenga poruna garza, cuando cambi de
					// direccion
					// quito la garza
					switch (olddireccion) {
					case DERECHA:
						if (tablero[fila][columna + 1] == GARZA)
							tablero[fila][columna + 1] = CANNA1;
						break;
					case IZQUIERDA:
						if (tablero[fila][columna - 1] == GARZA)
							tablero[fila][columna - 1] = CANNA1;
						break;
					case ARRIBA:
						if (tablero[fila + 1][columna] == GARZA)
							tablero[fila + 1][columna] = CANNA1;
						break;
					case ABAJO:
						if (tablero[fila - 1][columna] == GARZA)
							tablero[fila - 1][columna] = CANNA1;
						break;
					default:
						break;
					}

				}
			}
		}
	}

	public void update() {

		// mover segun direccion
		switch (direccion) {
		case DERECHA:
			if (tablero[fila][columna + 1] == CANNA1) {
				tablero[fila][columna + 1] = LIBRE;
				Configuraciones.enmov = true;
				columna++;
			} else if (tablero[fila][columna + 1] == CANNA2) {
				tablero[fila][columna + 1] = CANNA1;
				Configuraciones.enmov = true;
				columna++;
			} else {
				Configuraciones.enmov = false;
			}
			break;
		case IZQUIERDA:
			if (tablero[fila][columna - 1] == CANNA1) {
				tablero[fila][columna - 1] = LIBRE;
				Configuraciones.enmov = true;
				columna--;
			} else if (tablero[fila][columna - 1] == CANNA2) {
				tablero[fila][columna - 1] = CANNA1;
				Configuraciones.enmov = true;
				columna--;
			} else {
				Configuraciones.enmov = false;
			}
			break;
		case ARRIBA:
			if (tablero[fila + 1][columna] == CANNA1) {
				tablero[fila + 1][columna] = LIBRE;
				Configuraciones.enmov = true;
				fila++;
			} else if (tablero[fila + 1][columna] == CANNA2) {
				tablero[fila + 1][columna] = CANNA1;
				Configuraciones.enmov = true;
				fila++;
			} else {
				Configuraciones.enmov = false;
			}
			break;
		case ABAJO:
			if (tablero[fila - 1][columna] == CANNA1) {
				tablero[fila - 1][columna] = LIBRE;
				Configuraciones.enmov = true;
				fila--;
			} else if (tablero[fila - 1][columna] == CANNA2) {
				tablero[fila - 1][columna] = CANNA1;
				Configuraciones.enmov = true;
				fila--;
			} else {
				Configuraciones.enmov = false;
			}
			break;
		default:
			Configuraciones.enmov = false;
			break;
		}
	}

	public int sinMovimiento() {
		int resultado;
		if (seTranco()) {
			if (esGanador()) {
				resultado = GANA;
			} else {
				resultado = TRANQUE;
			}
		} else {
			resultado = JUEGA;
		}
		return resultado;
	}

	public boolean seTranco() {
		boolean resultado = true;
		if (tablero[fila + 1][columna] == CANNA1
				|| tablero[fila + 1][columna] == CANNA2) {
			resultado &= false;
		}
		if (tablero[fila - 1][columna] == CANNA1
				|| tablero[fila - 1][columna] == CANNA2) {
			resultado &= false;
		}
		if (tablero[fila][columna + 1] == CANNA1
				|| tablero[fila][columna + 1] == CANNA2) {
			resultado &= false;
		}
		if (tablero[fila][columna - 1] == CANNA1
				|| tablero[fila][columna - 1] == CANNA2) {
			resultado &= false;
		}
		return resultado;
	}

	public boolean esGanador() {
		boolean resultado = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tablero[i][j] == CANNA1 || tablero[i][j] == CANNA2) {
					resultado = false;
					break;
				}
			}
		}
		return resultado;
	}

	public boolean isdirecciongarza(int i, int j) {
		boolean resultado = false;
		switch (direccion) {
		case DERECHA:
			if (fila * 10 + columna + 1 == i * 10 + j) {
				resultado = true;
			}
			break;
		case IZQUIERDA:
			if (fila * 10 + columna - 1 == i * 10 + j) {
				resultado = true;
			}
			break;
		case ARRIBA:
			if ((fila + 1) * 10 + columna == i * 10 + j) {
				resultado = true;
			}
			break;
		case ABAJO:
			if ((fila - 1) * 10 + columna == i * 10 + j) {
				resultado = true;
			}
			break;
		default:
			break;
		}
		return resultado;
	}

	public boolean direcciongarza() {
		// TODO Auto-generated method stub
		boolean resultado = false;
		switch (direccion) {
		case DERECHA:
			if (tablero[fila][columna + 1] == GARZA) {
				if (vuelo == 1) {
					vuelo = 2;
				} else if (vuelo == 2) {
					vuelo = 1;
				}
				resultado = true;
			}
			break;
		case IZQUIERDA:
			if (tablero[fila][columna - 1] == GARZA) {
				if (vuelo == 1) {
					vuelo = 2;
				} else if (vuelo == 2) {
					vuelo = 1;
				}
				resultado = true;
			}
			break;
		case ARRIBA:
			if (tablero[fila + 1][columna] == GARZA) {
				if (vuelo == 1) {
					vuelo = 2;
				} else if (vuelo == 2) {
					vuelo = 1;
				}
				resultado = true;
			}
			break;
		case ABAJO:
			if (tablero[fila - 1][columna] == GARZA) {
				if (vuelo == 1) {
					vuelo = 2;
				} else if (vuelo == 2) {
					vuelo = 1;
				}
				resultado = true;
			}
			break;
		default:
			break;
		}
		return resultado;
	}

}
