package TA.panoramadel;
import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class CameraActivity extends Activity {

	private static final int CAPTURE_IMAGE_CAPTURE_CODE = 0;
	Intent i;
	private ImageButton ib;

	public static int cont = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		ib = (ImageButton) findViewById(R.id.buttonToast);
		
		ib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				//folder stuff
				File imagesFolder = new File(Environment.getExternalStorageDirectory(), "/Android/data/com.example.panoramasdcard/files");
				//imagesFolder.mkdirs();
				
				if (cont == 1){
					File image = new File(imagesFolder, "quito1_f.jpg");
					Uri uriSavedImage = Uri.fromFile(image);
					if (image.exists()) {
						i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
						startActivityForResult(i, CAPTURE_IMAGE_CAPTURE_CODE);
						}else{
							i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
							startActivityForResult(i, CAPTURE_IMAGE_CAPTURE_CODE);
						}
					cont++;
					//write the bytes in file
				}else{
					Intent reimages = new Intent(getApplicationContext(),Main.class);
					startActivity(reimages);
				}
			}
		});
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_CAPTURE_CODE) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, "Image Captured", Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}
}
