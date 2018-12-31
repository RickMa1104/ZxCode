package com.example.zxcode;

import java.io.File;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

public class ZxImg {

	private String getFileRoot(Context context) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File external = context.getExternalFilesDir(null);
			if (external != null) {
				return external.getAbsolutePath();
			}
		}
		return context.getFilesDir().getAbsolutePath();
	}

	public synchronized void loadZxing(String url, Context ctx, final ImageView img, int width) {
		final String filePath = getFileRoot(ctx) + File.separator + "qr_" + System.currentTimeMillis() + ".jpg";
		boolean success = QRCodeUtil.createQRImage(url, width, width, null, filePath);
		if (success) {
			img.setImageBitmap(BitmapFactory.decodeFile(filePath));
		}
	}

}
