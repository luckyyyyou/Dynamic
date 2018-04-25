package com.example.dynamic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skydoves.colorpickerview.ColorPickerView;

import java.util.regex.Pattern;

/**
 * Created by Danni on 2017/5/22.
 */

public class Tt extends AppCompatActivity {
    EditText Name,Width,Height;
    Button Submit,Delete;
    ColorPickerView pickerView;
    int colorCode,chosedWidth,choseHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        final int id=intent.getIntExtra("id",7);
        setTitle(id+"属性");
        setContentView(R.layout.name_edit);
        Name=(EditText) findViewById(R.id.name);
        Width=(EditText) findViewById(R.id.width);
        Height=(EditText) findViewById(R.id.height);
        Submit=(Button) findViewById(R.id.submit);
        Delete=(Button) findViewById(R.id.delete);
        pickerView=(ColorPickerView) findViewById(R.id.colorPickerView);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                String name=Name.getText().toString();
                String www=Width.getText().toString();
                String hhh=Height.getText().toString();
                //输入值的有效性判断
                if (www.equals("")||hhh.equals("")){
                    Toast.makeText(Tt.this,"长度或宽度不能为0",Toast.LENGTH_SHORT).show();
                }else if (!isNumeric(hhh)||!isNumeric(www)){
                    Toast.makeText(Tt.this,"长度和宽度必须为数字",Toast.LENGTH_SHORT).show();
                }
                else {
                    chosedWidth=Integer.parseInt(Width.getText().toString());
                    choseHeight=Integer.parseInt(Height.getText().toString());
                    intent1.putExtra("okc",name);
                    intent1.putExtra("w",chosedWidth);
                    intent1.putExtra("h",choseHeight);
                    intent1.putExtra("id",id);
                    intent1.putExtra("chosedColor",colorCode);
                    setResult(RESULT_OK,intent1);
                    finish();
                }

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(Tt.this);
                dialog.setTitle("");
                dialog.setMessage("确认删除此控件?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1=new Intent();
                        String name=Name.getText().toString();
                        intent1.putExtra("okc",name);
                        intent1.putExtra("id",id);
                        setResult(RESULT_CANCELED,intent1);
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        pickerView.setColorListener(new ColorPickerView.ColorListener() {
            @Override
            public void onColorSelected(int color) {
                colorCode=color;
                Toast.makeText(Tt.this,"您已选择了按钮背景颜色",Toast.LENGTH_SHORT).show();


            }
        });

    }
    /*public void init(){
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        final String name=sharedPreferences.getString("name","");
        final int w=sharedPreferences.getInt("w",0);
        final int h=sharedPreferences.getInt("h",0);
        Log.d("okc","name="+name);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Name.setText(name);
                Width.setText(w+"");
                Height.setText(h+"");
            }
        }).start();
*//*        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Name.setText(name);
                Width.setText(w);
                Height.setText(h);
            }
        });*//*
    }*/
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
