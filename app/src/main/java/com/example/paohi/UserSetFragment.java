package com.example.paohi;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yxp on 2015/8/17.
 */
public class UserSetFragment extends Fragment {
    private  ImageButton mback;
    private Button muser;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_set, parent, false);
        mback= (ImageButton) v.findViewById(R.id.back);
        muser= (Button) v.findViewById(R.id.user);

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RunActivity.class);
                startActivity(intent);

            }
        });
        muser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });


        return v;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==2){
            if(data==null)
            {
                return;
            }else {
                Uri uri=data.getData();
                Uri fileuri=converturi(uri);
                StartimageZoom(fileuri);
            }


        }else if (resultCode==3){
            if(data==null){
                return;
            }
        }
        Bundle extras=data.getExtras();
        Bitmap bitmap=extras.getParcelable("data");
        Drawable db=new BitmapDrawable(bitmap);
        muser.setBackgroundDrawable(db);


    }
    private Uri converturi(Uri uri){
        InputStream is = null;
        try {
            is=getActivity().getContentResolver().openInputStream(uri);
            Bitmap bitmap= BitmapFactory.decodeStream(is);
            is.close();
            return  saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
    private Uri saveBitmap(Bitmap bitmap)
    {
        File tempfile=new File(Environment.getExternalStorageDirectory()+"/headface.avater");
        if(!tempfile.exists()){
            tempfile.mkdir();
        }
        File image=new File(tempfile.getAbsolutePath(),"avater.png");
        try {
            FileOutputStream fos=new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
    private void StartimageZoom(Uri uri)
    {
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent, 3);
    }
}