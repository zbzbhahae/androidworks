package com.zb.review.acts.camera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;
import com.zb.review.utils.T;
import com.zb.review.utils.UriUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class TakePictureActivity extends BaseActivity {

    @BindView(R.id.camera_image)
    ImageView imageView;
    @BindView(R.id.camera_button1)
    Button button1;
    @BindView(R.id.camera_button2)
    Button button2;
    @BindView(R.id.camera_button3)
    Button button3;
    @BindView(R.id.camera_button4)
    Button button4;


    private static final int REQUEST_CAMERA_CAPTURE = 101;
    private static final int REQUEST_GALLERY_PICK = 102;
    private static final int REQUEST_CROP_PICTURE = 103;
    private static final int REQUEST_CAMERA_VIDEO_CAPTURE = 104;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @OnClick({R.id.camera_button1, R.id.camera_button2,
            R.id.camera_button3, R.id.camera_button4})
    public void onClick(Button b) {
        switch (b.getId()) {
            case R.id.camera_button1:
                goCapture();
                break;
            case R.id.camera_button2:
                goPickPicture();
                break;
            case R.id.camera_button3:
                goCaptureVideo();
                break;
            case R.id.camera_button4:
                goCompressVideo();
                break;
        }
    }

    private void goCompressVideo() {
        Intent i = new Intent(this, CompressActivity.class);
        startActivity(i);
    }

    void goPickPicture() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, REQUEST_GALLERY_PICK);
    }

    void goCapture() {
        boolean hasCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        if(!hasCamera) {
            T.t(this, "手机没有摄像头~");
            return;
        }
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(cameraIntent.resolveActivity(getPackageManager()) != null)
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null != photoFile) {
            Uri photoURI = FileProvider.getUriForFile(this, "com.zb.review.fileProvider", photoFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(cameraIntent, REQUEST_CAMERA_CAPTURE);
        }

    }

    void goCaptureVideo() {
        boolean hasCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        if(!hasCamera) {
            T.t(this, "手机没有摄像头~");
            return;
        }
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
//        if(cameraIntent.resolveActivity(getPackageManager()) != null)
        File videoFile = null;
        try {
            videoFile = createVideoFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null != videoFile) {
            Uri videoUri = FileProvider.getUriForFile(this, "com.zb.review.fileProvider", videoFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            startActivityForResult(cameraIntent, REQUEST_CAMERA_VIDEO_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAMERA_CAPTURE:
                if(resultCode == RESULT_OK) {
//                    Bundle b = data.getExtras();
//                    Bitmap bitmap = (Bitmap) b.get("data");
//                    imageView.setImageBitmap(bitmap);
                    if(null != currentPhotoPath) {
                        setPic(currentPhotoPath);
                        addPicToGallery(currentPhotoPath);
                        Uri uri = FileProvider.getUriForFile(this, "com.zb.review.fileProvider", new File(currentPhotoPath));
                        crop(uri);
                    }
                }
                break;
            case REQUEST_CAMERA_VIDEO_CAPTURE:
                if(resultCode == RESULT_OK) {
                    T.t(this, "拍摄完成");
                    if(null != currentPhotoPath) {
                        P.p("video path = " + currentPhotoPath);
                        addPicToGallery(currentPhotoPath);
                    }
                }
                break;
            case REQUEST_GALLERY_PICK:
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    P.p(" uri " + uri.toString());
                    String filePath = UriUtils.getRealPathFromUri(this, uri);
                    P.p(" uri path - >" + filePath);
                    Uri newUri = FileProvider.getUriForFile(this, "com.zb.review.fileProvider", new File(filePath));
                    crop(newUri);
                    Glide.with(this).load(uri).into(imageView);

                }
                break;
            case REQUEST_CROP_PICTURE:
                if(resultCode == RESULT_OK) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    imageView.setImageBitmap(bitmap);
                }
                break;
        }
    }

    /**
     * 根据返回的uri去裁剪图片
     * @param uri
     */
    private void crop(Uri uri) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);
            intent.putExtra("outputFormat", "JPEG");
            intent.putExtra("noFaceDetection", true);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CROP_PICTURE);
        } catch (Exception e) {
        }
    }

    /**
     * 拍照后对获得的图片进行压缩显示
     */
    private void setPic(String picPath) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(picPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    private void addPicToGallery(String picPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(picPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    private String currentPhotoPath;
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private File createVideoFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "mp4_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        File image = File.createTempFile(imageFileName, ".mp4", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }




}
