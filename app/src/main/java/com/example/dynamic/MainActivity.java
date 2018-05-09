package com.example.dynamic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;


public class MainActivity extends AppCompatActivity {

    public static int index,count,i,j,k=0;
    public static String okc;
    float dx, dy,x,y,w,h,r,b,Y,y1,y2;
    long lastDownTime,eventTime;
    boolean isLongPressed=false;
    Button[] btn = new Button[100];
    TextView[] textViews = new TextView[100];
    EditText[] editTexts = new EditText[100];
    Button addB,clear,addT,addEdit;
    RelativeLayout relativeLayout;







    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        okc=intent.getStringExtra("okc");
        addB=(Button) findViewById(R.id.button1);
        clear=(Button) findViewById(R.id.button2);
        addT=(Button) findViewById(R.id.button3);
        addEdit=(Button) findViewById(R.id.button4);
        init();
    }

    private void init(){
        LitePal.getDatabase();

        relativeLayout = (RelativeLayout) findViewById(R.id.Rela);


        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                count=relativeLayout.getChildCount();
                relativeLayout.addView(createButton());

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=relativeLayout.getChildCount();
                //研究整个LAYOUT布局，第0位的是add
                //因此，在remove的时候，只能操作的是0<location<count-3这个范围的
                //在执行每次remove时，我们从count-2的位置即textview上面的那个控件开始删除~
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("");
                dialog.setMessage("确认清空布局?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        relativeLayout.removeViews(4,count-4);
                        i=j=k=0;
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
        addT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;
                count=relativeLayout.getChildCount();
                relativeLayout.addView(createTextView());


            }
        });
        addEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k++;
                count=relativeLayout.getChildCount();
                relativeLayout.addView(createEditText());
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if (data == null){
            return;
        }
        else {
            switch (requestCode) {
                case 1:
                    if (resultCode == RESULT_OK) {
                        okc = data.getStringExtra("okc");
                        int w=data.getIntExtra("w",11);
                        int h=data.getIntExtra("h",12);
                        int id=data.getIntExtra("id",3);
                        int color=data.getIntExtra("chosedColor",9);
   /*                     SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("name",okc);
                        editor.putInt("w",w);
                        editor.putInt("h",h);
                        editor.apply();*/
                        Log.d("da","return id is " + id);
                        Log.d("da","Button name is " + okc);
                        if (!okc.equals("")){
                            btn[id].setText(okc);
                        }
                        if (w!=0&&h!=0){
                            btn[id].setWidth(w);
                            btn[id].setHeight(h);
                        }
                        if (color!= -131332){
                            btn[id].setBackgroundColor(color);
                        }

                        break;
                    }
                    if (resultCode == RESULT_CANCELED){
                        int id=data.getIntExtra("id",4);
                        relativeLayout.removeView(btn[id]);
//                        btn[id].setVisibility(View.INVISIBLE);
                        i--;
                    }
                    break;
                case 2:
                    if (resultCode == RESULT_OK) {
                        okc = data.getStringExtra("okc");
                        int w=data.getIntExtra("w",13);
                        int h=data.getIntExtra("h",14);
                        int id=data.getIntExtra("id",5);
                        int color=data.getIntExtra("chosedColor",10);
                        Log.d("da","return id is " + id);
                        Log.d("da","TextView name is " + okc);
                        if (!okc.equals("")){
                            textViews[id].setText(okc);
                        }
                        if (w!=0&&h!=0){
                            textViews[id].setWidth(w);
                            textViews[id].setHeight(h);
                        }
                        if (color!= -131332){
                            textViews[id].setTextColor(color);
                        }
                        break;
                    }
                    if (resultCode == RESULT_CANCELED){
                        int id=data.getIntExtra("id",6);
                        relativeLayout.removeView(textViews[id]);
//                        textViews[id].setVisibility(View.INVISIBLE);
                        j--;
                    }
                    break;
                default:
                    break;
            }
        }
    }
    protected View createButton(){

        btn[i] = new Button(this);
        btn[i].setId(i);
        Log.d("da","Button id is " + btn[i].getId());
        btn[i].setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btn[i].setText("点击修改属性");


        btn[i].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = motionEvent.getX();
                    dy = motionEvent.getY();
                    w=view.getWidth();
                    h=view.getHeight();
                    Y=view.getY();
                    y1=motionEvent.getRawY();
                    lastDownTime=motionEvent.getDownTime();

                }
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    x=motionEvent.getX();
                    y=motionEvent.getY();
                    r=view.getRight();
                    b=view.getBottom();
                    eventTime=motionEvent.getEventTime();
                    //检测是否长按,在非长按时检测
                    if (!isLongPressed) {
                        isLongPressed = isLongPressed(dx, dy, x, y, lastDownTime, eventTime, 1000);
                    }
                    if (isLongPressed) {
                        //长按模式所做的事
                        y2=motionEvent.getRawY();
                        view.setX(motionEvent.getRawX() - dx);
                        view.setY(Y+y2-y1);

                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    isLongPressed=false;
                }
                return false;
            }
        });
        btn[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tt.class);
                int id=v.getId();
                intent.putExtra("id",id);
                startActivityForResult(intent,1);
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
            }
        });
        return btn[i];

    }
    protected View createTextView(){

        textViews[j]=new TextView(this);
        textViews[j].setId(j);
        Log.d("da","TextView id is " + textViews[j].getId());
        textViews[j].setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        /*btn.setText(okc);*/
        textViews[j].setText("点击修改属性");
        textViews[j].setBackgroundColor(Color.YELLOW);

        textViews[j].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = motionEvent.getX();
                    dy = motionEvent.getY();
                    w=view.getWidth();
                    h=view.getHeight();
                    Y=view.getY();
                    y1=motionEvent.getRawY();
                    lastDownTime=motionEvent.getDownTime();

                }
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    x=motionEvent.getX();
                    y=motionEvent.getY();
                    r=view.getRight();
                    b=view.getBottom();
                    eventTime=motionEvent.getEventTime();
                    //检测是否长按,在非长按时检测
                    if (!isLongPressed) {
                        isLongPressed = isLongPressed(dx, dy, x, y, lastDownTime, eventTime, 1000);
                    }
                    if (isLongPressed) {
                        //长按模式所做的事
                        y2=motionEvent.getRawY();
                        view.setX(motionEvent.getRawX() - dx);
                        view.setY(Y+y2-y1);

                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    isLongPressed=false;
                }

                return false;


            }
        });
        textViews[j].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Ttt.class);
                int id=v.getId();
                intent.putExtra("id",id);
                startActivityForResult(intent,2);
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
            }
        });


        return textViews[j];

    }
    protected View createEditText(){

        editTexts[k]=new EditText(this);
        editTexts[k].setId(k);
        Log.d("da","EditText id is " + editTexts[k].getId());
        editTexts[k].setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        /*btn.setText(okc);*/
        editTexts[k].setHint("请输入文本");
        editTexts[k].setHintTextColor(Color.LTGRAY);


        editTexts[k].setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = motionEvent.getX();
                    dy = motionEvent.getY();
                    w=view.getWidth();
                    h=view.getHeight();
                    Y=view.getY();
                    y1=motionEvent.getRawY();
                    lastDownTime=motionEvent.getDownTime();

                }
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    x=motionEvent.getX();
                    y=motionEvent.getY();
                    r=view.getRight();
                    b=view.getBottom();
                    eventTime=motionEvent.getEventTime();
                    //检测是否长按,在非长按时检测
                    if (!isLongPressed) {
                        isLongPressed = isLongPressed(dx, dy, x, y, lastDownTime, eventTime, 1000);
                    }
                    if (isLongPressed) {
                        //长按模式所做的事
                        y2=motionEvent.getRawY();
                        view.setX(motionEvent.getRawX() - dx);
                        view.setY(Y+y2-y1);

                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    isLongPressed=false;
                }

                return false;


            }
        });

        return editTexts[k];

    }
/*    public int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public int getTitleBarHeight(){
        Window window = getWindow();
        int contentViewTop = getWindow()
                .findViewById(window.ID_ANDROID_CONTENT).getTop();
        // statusBarHeight是上面所求的状态栏的高度
        int titleBarHeight = contentViewTop - getStatusBarHeight();
        return titleBarHeight;
    }*/

    static boolean isLongPressed(float lastX, float lastY, float thisX,
                                 float thisY, long lastDownTime, long thisEventTime,
                                 long longPressTime) {
        float offsetX = Math.abs(thisX - lastX);
        float offsetY = Math.abs(thisY - lastY);
        long intervalTime = thisEventTime - lastDownTime;
        return (offsetX <= 10 && offsetY <= 10 && intervalTime >= longPressTime);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();


    }





}

