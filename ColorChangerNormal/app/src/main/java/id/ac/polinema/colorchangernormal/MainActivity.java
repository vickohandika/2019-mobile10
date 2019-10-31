package id.ac.polinema.colorchangernormal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	private ConstraintLayout rootView;
	private Button btnChangeColor;
	//Tambahkan class ColorViewModel
    ColorViewModel colorViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rootView = findViewById(R.id.layout_main);
		btnChangeColor = findViewById(R.id.change_color);

		//Instansiasi ViewModel pada onCreate()
        colorViewModel = ViewModelProviders.of(this).get(ColorViewModel.class);

		//Tambahkan event click pada block method onCreate() seperti pada kode berikut
		btnChangeColor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int color = generateRandomColor();
				rootView.setBackgroundColor(color);
				//Update logic pada event click, tambahkan proses untuk menyimpan warna pada ViewModel
				colorViewModel.setColor(color);
			}
		});
		//Ganti background, tetapi gunakan informasi warna yang disimpan dalam ViewModel
		rootView.setBackgroundColor(colorViewModel.getColor());
	}

	private int generateRandomColor(){
		Random rnd = new Random();
		return Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
	}

}
