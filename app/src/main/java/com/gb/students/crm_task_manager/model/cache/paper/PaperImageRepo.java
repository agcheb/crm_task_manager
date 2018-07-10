package com.gb.students.crm_task_manager.model.cache.paper;

import android.graphics.Bitmap;

import com.gb.students.crm_task_manager.App;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import timber.log.Timber;

public class PaperImageRepo {

    private  final String IMAGE_FOLDER_NAME = "image";

    public  File getImageFile(String imagePath)
    {
        return new File(imagePath);
    }
//
//    public  boolean contains(String url)
//    {
//        return Realm.getDefaultInstance().where(CachedImage.class).equalTo("url", url).count() > 0;
//    }
//
//    public  void clear()
//    {
//        Realm.getDefaultInstance().executeTransaction(realm -> realm.delete(CachedImage.class));
//        deleteFileOrDirRecursive(getImageDir());
//    }

    public String saveImage(String format, Bitmap bitmap)
    {
        if (!getImageDir().exists() && !getImageDir().mkdirs())
        {
            throw new RuntimeException("Failed to create directory: " + getImageDir().toString());
        }

        final File imageFile = new File(getImageDir(), MD5(bitmap.toString()) + format);
        FileOutputStream fos;

        try
        {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(format.equals("jpg") || format.equals("jpeg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        catch (Exception e)
        {
            Timber.d("Failed to save image");
            return null;
        }

//        Realm.getDefaultInstance().executeTransactionAsync(realm ->
//        {
//            CachedImage cachedImage = new CachedImage();
//            cachedImage.setUrl(url);
//            cachedImage.setPath(imageFile.toString());
//            realm.copyToRealm(cachedImage);
//        });
        
        return imageFile.toString();
    }

    public  File getImageDir()
    {
        return new File(App.getInstance().getExternalFilesDir(null) + "/" + IMAGE_FOLDER_NAME);
    }

    public  String MD5(String s) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    public  float getSizeKb()
    {
        return getFileOrDirSize(getImageDir()) / 1024f;
    }

    public  void deleteFileOrDirRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
        {
            for (File child : fileOrDirectory.listFiles())
            {
                deleteFileOrDirRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

    public  long getFileOrDirSize(File f) {
        long size = 0;
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                size += getFileOrDirSize(file);
            }
        } else {
            size=f.length();
        }
        return size;
    }
}
