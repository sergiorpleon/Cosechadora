package cu.juego.implementacion.cosechadora;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import android.content.SharedPreferences;

import cu.juego.implementacion.FileIO;

public class Configuraciones {
	public static int mundo = 1;
	//
	public static boolean enmov = false;
	//
	public static boolean virgen = true;
	public static boolean nuevoNivel = false;
	public static int numeroNivel = 0;
	public static int descubierto = 1;
	public static SharedPreferences miPrefer;
	public static ArrayList<Integer> lisP;

	public static boolean soundEnabled = true;
	public static boolean[] ganado = new boolean[] { false, false, false, false, false, false, false, false, false };
	public static int[] niveles = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4 };


	
	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.leerArchivo(".obe")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for (int i = 0; i < 5; i++) {
				//highscores[i] = Integer.parseInt(in.readLine());
			}

		} catch (IOException e) {
			// TODO: handle exception
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void saved(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.escreibirArchivo(".obe")));
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			for (int i = 0; i < 5; i++) {
				//out.write(Integer.toString(highscores[i]));
				out.write("\n");
			}

		} catch (IOException e) {
			// TODO: handle exception
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void addScore(int score) {
		for (int i = 0; i < 5; i++) {
			//if (highscores[i] < score) {
				for (int j = 4; j > i; j--) {
			//		highscores[j] = highscores[j - 1];
				}
			//	highscores[i] = score;
				break;
			//}
		}
	}

	// **************************************************************

	public static void adicionar(Integer p) {
		// TODO Auto-generated method stub
		lisP.add(p);
		Collections.sort(lisP);
		lisP.remove(0);

	}

	// carga y ordena
	public static void cargar() {
		// lisP = new ArrayList<Integer>();
		virgen = miPrefer.getBoolean("virgen", true);
		soundEnabled = miPrefer.getBoolean("sound", true);
		descubierto = miPrefer.getInt("descubierto", 1);
		// Collections.sort(lisP);
	}

	public static void guardar() {
		// --- Creacion del editor donde se salvara los valores
		SharedPreferences.Editor editor = miPrefer.edit();
		// --- Guardado de los valores
		editor.putBoolean("virgen", virgen);
		editor.putBoolean("sound", soundEnabled);
		editor.putInt("descubierto", descubierto);
		editor.commit();
	}

	public static void cargarMundo() {
		// lisP = new ArrayList<Integer>();

		for (int i = 0; i < 9; i++) {
			niveles[i] = miPrefer
					.getInt(Configuraciones.mundo + "nivel" + i, 4);
			ganado[i] = miPrefer
					.getBoolean(Configuraciones.mundo + "ganado" + i, false);
		}
		// Collections.sort(lisP);
	}

	public static void guardarMundo() {
		// --- Creacion del editor donde se salvara los valores
		SharedPreferences.Editor editor = miPrefer.edit();
		// --- Guardado de los valores
		for (int i = 0; i < 9; i++) {
			// mov1 = lisP.get(i);
			editor.putInt(Configuraciones.mundo + "nivel" + i, niveles[i]);
			editor.putBoolean(Configuraciones.mundo + "ganado" + i, ganado[i]);
		}
		editor.commit();
	}

	public static int numeroEstrellas() {
		// TODO Auto-generated method stub
		int sumEstrellas = 0;
		for (int i = 0; i < 9; i++) {
			switch (niveles[i]) {
			case 0:
				sumEstrellas +=1;
				break;
			case 1:
				sumEstrellas +=2;
				break;
			case 2:
				sumEstrellas +=2;
				break;
			case 3:
				sumEstrellas +=3;
				break;
			case 4:
				sumEstrellas +=0;
				break;
			default:
				break;
			}
		}
		return sumEstrellas;
	}

}
