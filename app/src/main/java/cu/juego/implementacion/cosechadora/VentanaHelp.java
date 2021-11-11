package cu.juego.implementacion.cosechadora;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VentanaHelp extends Activity {
	ImageView imgView1;
	ImageView imgView2;
	ImageView imgView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		Configuraciones.virgen = false;
		Configuraciones.guardar();
		//imgView1 = (ImageView) findViewById(R.id.imageView2);
		//imgView1.setVisibility(ImageView.VISIBLE);
		//imgView1.setBackgroundResource(R.drawable.animacion_a1);

		//imgView2 = (ImageView) findViewById(R.id.imageView3);
		//imgView2.setVisibility(ImageView.VISIBLE);
		//imgView2.setBackgroundResource(R.drawable.animacion_a2);

		//imgView3 = (ImageView) findViewById(R.id.imageView4);
		//imgView3.setVisibility(ImageView.VISIBLE);
		//imgView3.setBackgroundResource(R.drawable.animacion_a1);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				// Start the next activity
				//animate();
			}
		};

		// Simulate a long loading process on application startup.

		Timer timer = new Timer();
		timer.schedule(task, 1000);

	}

	public void clic(View v) {
		//animate();
	}

	private void animate() {

		AnimationDrawable frameAnimation1 = (AnimationDrawable) imgView1
				.getBackground();
		if (frameAnimation1.isRunning()) {
			frameAnimation1.stop();
		} else {
			frameAnimation1.stop();
			frameAnimation1.start();
		}

		AnimationDrawable frameAnimation2 = (AnimationDrawable) imgView2
				.getBackground();
		if (frameAnimation2.isRunning()) {
			frameAnimation2.stop();
		} else {
			frameAnimation2.stop();
			frameAnimation2.start();
		}
		AnimationDrawable frameAnimation3 = (AnimationDrawable) imgView3
				.getBackground();
		if (frameAnimation3.isRunning()) {
			frameAnimation3.stop();
		} else {
			frameAnimation3.stop();
			frameAnimation3.start();
		}
	}
	
	public void aceptar(View v){
		onBackPressed();
	}

}
