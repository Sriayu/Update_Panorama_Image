package TA.panoramadel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;

/**
 * This example shows how to resize an image
 * @author FaYnaSoft Labs
 *
 */
public class Main extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		BitmapFactory.Options options = new BitmapFactory.Options();//sudah
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;//sudah
		Matrix matrix = new Matrix();//sudah

		int newWidth = 1024;//sudah
		int newHeight = 1024;//sudah
		
		String _path = Environment.getExternalStorageDirectory()+"/DCIM/Camera/quito1_b.jpg";
		Bitmap bitmap = BitmapFactory.decodeFile(_path, options);
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		
		matrix.postScale(scaleWidth, scaleHeight);

		// create the new Bitmap object
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
				height, matrix, true);

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

		//you can create a new file name "test.jpg" in sdcard folder.
		File newf = new File(Environment.getExternalStorageDirectory()+"/DCIM/Camera/quito1_bas.jpg");
		try {
			newf.createNewFile();
			FileOutputStream fo = new FileOutputStream(newf);
			fo.write(bytes.toByteArray());

			// remember close de FileOutput
			fo.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}