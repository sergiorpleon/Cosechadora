package cu.juego.implementacion.cosechadora;

import cu.juego.implementacion.Sonido;
import cu.juego.implementacion.android.AndroidAudio;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class VentanaMundo extends Activity {
	AndroidAudio audio;
	Sonido clic1;
	
	Button sonido;
	
	ImageView uno, dos, tres, cuatro;
	
	TextView estrellaview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mundos);

		//int ww = (getWindowManager().getDefaultDisplay().getWidth());
		//ww = (int) ((0.6) * ww);

		//LayoutParams lp = new LayoutParams(ww, ww);

		uno = (ImageView) findViewById(R.id.numerouno);
		//uno.setLayoutParams(lp);

		dos = (ImageView) findViewById(R.id.numerodos);
		//dos.setLayoutParams(lp);

		tres = (ImageView) findViewById(R.id.numerotres);
		//tres.setLayoutParams(lp);

		cuatro = (ImageView) findViewById(R.id.numerocuatro);
		//cuatro.setLayoutParams(lp);
		
		sonido = (Button) findViewById(R.id.sonido);
		
		audio = new AndroidAudio(this);
		clic1 = audio.nuevoSonido("clic.ogg");
		
		String nombrePrefer = "Cosechadora";
		SharedPreferences miPrefer = this.getSharedPreferences(nombrePrefer,
				MODE_PRIVATE);
		Configuraciones.miPrefer = miPrefer;
		
		Configuraciones.cargar();
		dibujarCampos();
		
		//poner numero de estrellas
		estrellaview = (TextView) findViewById(R.id.estrellas);
		estrellaview.setText(numeroEstrella()+"");
		//estrellaview.setTextSize((getResources().getConfiguration().screenHeightDp)/20);
		
		if (Configuraciones.virgen) {
			Intent i = new Intent().setClass(VentanaMundo.this,
					VentanaHelp.class);
			// i.putExtra("variable1", texto);
			Configuraciones.virgen = false;
			Configuraciones.guardar();
			startActivity(i);
			// overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}

	}
	
	

	private void dibujarCampos() {
		// TODO Auto-generated method stub
		uno.setBackgroundResource(R.drawable.libre);
		if(Configuraciones.descubierto <=1){
			dos.setBackgroundResource(R.drawable.candado);
		}else{
			dos.setBackgroundResource(R.drawable.libre);
		}
		if(Configuraciones.descubierto <=2){
			tres.setBackgroundResource(R.drawable.candado);
		}else{
			tres.setBackgroundResource(R.drawable.libre);
		}
		if(Configuraciones.descubierto <=3){
			cuatro.setBackgroundResource(R.drawable.candado);
		}else{
			cuatro.setBackgroundResource(R.drawable.libre);
		}
		
		
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Configuraciones.cargar();
		dibujarCampos();
	}



	public void ir(View v) {
		switch (v.getId()) {
		case R.id.numerouno:
			Configuraciones.mundo = 1;
			break;
		case R.id.numerodos:
			Configuraciones.mundo = 2;
			
			break;
		case R.id.numerotres:
			Configuraciones.mundo = 3;
			
			break;
		case R.id.numerocuatro:
			Configuraciones.mundo = 4;
			
			break;
		default:
			break;
		}
		if (Configuraciones.mundo <= Configuraciones.descubierto) {

			if (Configuraciones.soundEnabled) {
				clic1.play(1);
			}
			Log.d("son", "ok");
			Configuraciones.guardar();
			Configuraciones.cargarMundo();
			Intent i = new Intent(getBaseContext(), VentanaNiveles.class);
			// i.putExtra("variable1", texto);
			startActivity(i);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			finish();
		}
	}
	
	
	
	public void sonido(View v){
		if(Configuraciones.soundEnabled){
			sonido.setText("Sound");
			Configuraciones.soundEnabled = false;
			Configuraciones.guardar();
		}else{
			sonido.setText("Silence");
			Configuraciones.soundEnabled = true;
			Configuraciones.guardar();
		}
	}
	
	public void ayuda(View v){
		Intent i = new Intent(getBaseContext(), VentanaHelp.class);
		// i.putExtra("variable1", texto);
		startActivity(i);
		// overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	public void reset(View v){
		
	for(int i=1; i<5; i++){
		Configuraciones.mundo = i;
		for(int j = 0; j<9; j++){
			
			Configuraciones.niveles[j] = 4;
			Configuraciones.ganado[j] = false;
		}
		Configuraciones.guardarMundo();
		Configuraciones.cargarMundo();
	}
	Configuraciones.descubierto = 1;
	Configuraciones.guardar();
	dibujarCampos();
	estrellaview.setText(0+"");
		
	}
	
	public int numeroEstrella() {
		// lisP = new ArrayList<Integer>();
		int numeroEstrellas = 0;
		for (int j = 0; j < 4; j++) {
		for (int i = 0; i < 9; i++) {
			if( Configuraciones.miPrefer
					.getBoolean(j + "ganado" + i, false)){
				
				numeroEstrellas += Configuraciones.miPrefer
						.getInt(j + "nivel" + i, 4);
			}
		}
		}
		return numeroEstrellas;
		// Collections.sort(lisP);
	}

	
	
}
