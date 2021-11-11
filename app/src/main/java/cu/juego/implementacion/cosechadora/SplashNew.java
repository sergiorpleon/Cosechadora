package cu.juego.implementacion.cosechadora;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashNew extends Activity {
	// Set the duration of the splash screen
	ImageView imgNivel;
	TextView msgtext;
	private static final long SPLASH_SCREEN_DELAY = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set portrait orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Hide title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Set portrait orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Hide title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_new);
		
		//imgNivel = (ImageView) findViewById(R.id.numeroX);
		msgtext = (TextView) findViewById(R.id.mensaje);
		
		msgtext.setText(R.string.nuevo_nivel);
		//imgNivel.setBackgroundResource(R.drawable.logo);
		
		
		//if (Configuraciones.descubierto == 20) {
		//	msgtext.setText(R.string.ganador);

		//}
		
		//int ww = (getWindowManager().getDefaultDisplay().getWidth()/2);
		//LinearLayout layoutlogo = (LinearLayout) findViewById(R.id.LayoutLogo1);
		//LayoutParams lp=new LayoutParams(ww, ww);
		//layoutlogo.setLayoutParams(lp);
		//layoutlogo.setGravity(Gravity.CENTER);
		
		if (Configuraciones.mundo == Configuraciones.descubierto) {
			if (Configuraciones.numeroEstrellas() >= 23) {
				Configuraciones.descubierto++;
				Configuraciones.guardar();
				//llamar();
			}
		}
		
		
		if (Configuraciones.soundEnabled) {
			Assets.felicidades.play(1);
		}

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				// Start the next activity
				Intent mainIntent = new Intent().setClass(SplashNew.this,
						VentanaNiveles.class);
				startActivity(mainIntent);
				overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

				// Close the activity so the user won't able to go back this
				// activity pressing Back button
				finish();
			}
		};

		// Simulate a long loading process on application startup.
		
		Timer timer = new Timer();
		timer.schedule(task, SPLASH_SCREEN_DELAY);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
	
	public void llamar(){
		Toast mensaje = Toast.makeText((Context) this, "Unlocker", Toast.LENGTH_SHORT);
		int offsetX = 50;
		int offsetY = 25;
		mensaje.setGravity(Gravity.CENTER | Gravity.TOP, offsetX, offsetY);
		mensaje.show();
	}
}
