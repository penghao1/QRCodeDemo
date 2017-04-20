package com.example.android.qrcodedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView resultTv;
    String information ="http://music.163.com/#/playlist?id=695592862";
    private CheckBox ckbAddlogo;
    private EditText sourceInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = (ImageView) findViewById(R.id.tv_content);
        ckbAddlogo = (CheckBox)findViewById(R.id.ckb_addlogo);
        sourceInf = (EditText)findViewById(R.id.edt_sourcemessage);
        sourceInf.setText(information);
    }

    //点击按钮
    public void generateQR(View view) {
        //二维码图片较大时，生成图片、保存文件的时间可能较长，因此放在新线程中
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap qrJpg = QRCodeUtil.createQRImage(MainActivity.this,sourceInf.getText().toString().trim(), 600, 600,
                        ckbAddlogo.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) : null);

                if (null!=qrJpg) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultTv.setImageBitmap(qrJpg);
                        }
                    });
                }
            }
        }).start();

    }

}
