package com.example.catanbankcompanion;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

public class AutoMode extends AppCompatActivity {
    int actualColor=1;
    Space actualThief=null;
    int actualMode=0;
    int [][] result =new int[1][5];
    int numero=5;
    int a,b; // 2 dados 2-12
    ImageButton thiefButton=null;
    SplittableRandom sp;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_mode);
        ColorButton colorButton=new ColorButton(R.id.colorButton);
        HashMap<Integer,Lot> lots= createLots();
        HashMap<Integer,Space> spaces=createGrid(lots);
        randomTile(spaces);
        randomNumber(spaces);
        sp=new SplittableRandom();
        ImageButton randomButton=(ImageButton) findViewById(R.id.resultbutton);
        randomButton.setOnTouchListener((v, event) -> {
            if(event.getAction()==event.ACTION_DOWN) {
                a=sp.nextInt(1,7);
                b=sp.nextInt(1,7);
                Intent t= new Intent(AutoMode.this,DialogActivity.class);
                t.putExtra("matriz",calculateResult(spaces,a+b));
                t.putExtra("numero",a+b);
                startActivity(t);
            }
            return false;
        });
        thiefButton=(ImageButton) findViewById(R.id.thiefButton);
        thiefButton.setOnTouchListener((v, event) -> {
            if(event.getAction()==event.ACTION_DOWN) {
                actualMode=1;
                thiefButton.setBackgroundColor(Color.RED);
            }
            return false;
        });
        NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(2);np.setMaxValue(12);
        ImageButton pickerButton=(ImageButton) findViewById(R.id.pickerbutton);
        pickerButton.setOnTouchListener((v, event) -> {
            if(event.getAction()==event.ACTION_DOWN) {
                numero=np.getValue();
                Intent t= new Intent(AutoMode.this,DialogActivity.class);
                t.putExtra("matriz",calculateResult(spaces,numero));
                t.putExtra("numero",numero);
                startActivity(t);
            }
            return false;
        });
    }
    HashMap<Integer,Space> createGrid(HashMap<Integer,Lot> lots){
        HashMap<Integer,Space> h = new HashMap<Integer,Space>();
        h.put(1,new Space(R.id.s1,0,new Lot[]{lots.get(1),lots.get(2),lots.get(3),lots.get(9),lots.get(10),lots.get(11)}));
        h.put(2,new Space(R.id.s2,0,new Lot[]{lots.get(3),lots.get(4),lots.get(5),lots.get(11),lots.get(12),lots.get(13)}));
        h.put(3,new Space(R.id.s3,0,new Lot[]{lots.get(5),lots.get(6),lots.get(7),lots.get(13),lots.get(14),lots.get(15)}));
        h.put(4,new Space(R.id.s4,0,new Lot[]{lots.get(8),lots.get(9),lots.get(10),lots.get(18),lots.get(19),lots.get(20)}));
        h.put(5,new Space(R.id.s5,0,new Lot[]{lots.get(10),lots.get(11),lots.get(12),lots.get(20),lots.get(21),lots.get(22)}));
        h.put(6,new Space(R.id.s6,0,new Lot[]{lots.get(12),lots.get(13),lots.get(14),lots.get(22),lots.get(23),lots.get(24)}));
        h.put(7,new Space(R.id.s7,0,new Lot[]{lots.get(14),lots.get(15),lots.get(16),lots.get(24),lots.get(25),lots.get(26)}));
        h.put(8,new Space(R.id.s8,0,new Lot[]{lots.get(17),lots.get(18),lots.get(19),lots.get(28),lots.get(29),lots.get(30)}));
        h.put(9,new Space(R.id.s9,0,new Lot[]{lots.get(19),lots.get(20),lots.get(21),lots.get(30),lots.get(31),lots.get(32)}));
        h.put(10,new Space(R.id.s10,0,new Lot[]{lots.get(21),lots.get(22),lots.get(23),lots.get(32),lots.get(33),lots.get(34)}));
        h.put(11,new Space(R.id.s11,0,new Lot[]{lots.get(23),lots.get(24),lots.get(25),lots.get(34),lots.get(35),lots.get(36)}));
        h.put(12,new Space(R.id.s12,0,new Lot[]{lots.get(25),lots.get(26),lots.get(27),lots.get(36),lots.get(37),lots.get(38)}));
        h.put(13,new Space(R.id.s13,0,new Lot[]{lots.get(29),lots.get(30),lots.get(31),lots.get(39),lots.get(40),lots.get(41)}));
        h.put(14,new Space(R.id.s14,0,new Lot[]{lots.get(31),lots.get(32),lots.get(33),lots.get(41),lots.get(42),lots.get(43)}));
        h.put(15,new Space(R.id.s15,0,new Lot[]{lots.get(33),lots.get(34),lots.get(35),lots.get(43),lots.get(44),lots.get(45)}));
        h.put(16,new Space(R.id.s16,0,new Lot[]{lots.get(35),lots.get(36),lots.get(37),lots.get(45),lots.get(46),lots.get(47)}));
        h.put(17,new Space(R.id.s17,0,new Lot[]{lots.get(40),lots.get(41),lots.get(42),lots.get(48),lots.get(49),lots.get(50)}));
        h.put(18,new Space(R.id.s18,0,new Lot[]{lots.get(42),lots.get(43),lots.get(44),lots.get(50),lots.get(51),lots.get(52)}));
        h.put(19,new Space(R.id.s19, 0,new Lot[]{lots.get(44),lots.get(45),lots.get(46),lots.get(52),lots.get(53),lots.get(54)}));
        return h;
    }
    HashMap<Integer,Lot> createLots(){
        HashMap<Integer,Lot> h = new HashMap<Integer,Lot>();
        h.put(1,new Lot(R.id.c1,0,0));
        h.put(2,new Lot(R.id.c2,0,0));
        h.put(3,new Lot(R.id.c3,0,0));
        h.put(4,new Lot(R.id.c4,0,0));
        h.put(5,new Lot(R.id.c5,0,0));
        h.put(6,new Lot(R.id.c6,0,0));
        h.put(7,new Lot(R.id.c7,0,0));
        h.put(8,new Lot(R.id.c8,0,0));
        h.put(9,new Lot(R.id.c9,0,0));
        h.put(10,new Lot(R.id.c10,0,0));
        h.put(11,new Lot(R.id.c11,0,0));
        h.put(12,new Lot(R.id.c12,0,0));
        h.put(13,new Lot(R.id.c13,0,0));
        h.put(14,new Lot(R.id.c14,0,0));
        h.put(15,new Lot(R.id.c15,0,0));
        h.put(16,new Lot(R.id.c16,0,0));
        h.put(17,new Lot(R.id.c17,0,0));
        h.put(18,new Lot(R.id.c18,0,0));
        h.put(19,new Lot(R.id.c19,0,0));
        h.put(20,new Lot(R.id.c20,0,0));
        h.put(21,new Lot(R.id.c21,0,0));
        h.put(22,new Lot(R.id.c22,0,0));
        h.put(23,new Lot(R.id.c23,0,0));
        h.put(24,new Lot(R.id.c24,0,0));
        h.put(25,new Lot(R.id.c25,0,0));
        h.put(26,new Lot(R.id.c26,0,0));
        h.put(27,new Lot(R.id.c27,0,0));
        h.put(28,new Lot(R.id.c28,0,0));
        h.put(29,new Lot(R.id.c29,0,0));
        h.put(30,new Lot(R.id.c30,0,0));
        h.put(31,new Lot(R.id.c31,0,0));
        h.put(32,new Lot(R.id.c32,0,0));
        h.put(33,new Lot(R.id.c33,0,0));
        h.put(34,new Lot(R.id.c34,0,0));
        h.put(35,new Lot(R.id.c35,0,0));
        h.put(36,new Lot(R.id.c36,0,0));
        h.put(37,new Lot(R.id.c37,0,0));
        h.put(38,new Lot(R.id.c38,0,0));
        h.put(39,new Lot(R.id.c39,0,0));
        h.put(40,new Lot(R.id.c40,0,0));
        h.put(41,new Lot(R.id.c41,0,0));
        h.put(42,new Lot(R.id.c42,0,0));
        h.put(43,new Lot(R.id.c43,0,0));
        h.put(44,new Lot(R.id.c44,0,0));
        h.put(45,new Lot(R.id.c45,0,0));
        h.put(46,new Lot(R.id.c46,0,0));
        h.put(47,new Lot(R.id.c47,0,0));
        h.put(48,new Lot(R.id.c48,0,0));
        h.put(49,new Lot(R.id.c49,0,0));
        h.put(50,new Lot(R.id.c50,0,0));
        h.put(51,new Lot(R.id.c51,0,0));
        h.put(52,new Lot(R.id.c52,0,0));
        h.put(53,new Lot(R.id.c53,0,0));
        h.put(54,new Lot(R.id.c54,0,0));
        return h;
    }
    void randomTile(HashMap<Integer,Space> spaces){
        SplittableRandom sr = new SplittableRandom();
        int[] cantidades={4,3,3,4,4,1};
        int i,rand;
        for(i=1;i<=19;i++){
            rand=sr.nextInt(6);
            if(cantidades[rand]>0){
                spaces.get(i).setImage(rand+1);
                spaces.get(i).setType(rand+1);
                cantidades[rand]--;
            }
            else i--;
        }
    }
    void randomNumber(HashMap<Integer,Space> spaces){
        SplittableRandom sr = new SplittableRandom();
        int[] numeros={2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
        int i,rand;
        for(i=1;i<=19;i++){
            rand=sr.nextInt(18);
            int k=numeros[rand];
            if(k!=0){
                if(spaces.get(i).type!=6){
                    spaces.get(i).setNumber(k);
                    spaces.get(i).number=k;
                    numeros[rand]=0;}
                else {
                    spaces.get(i).setNumber(7);
                    spaces.get(i).number=7;
                    thiefIsHere(spaces.get(i));
                }
            }
            else i--;
        }
    }
    void thiefIsHere(Space s){
        Space temp=actualThief;
        actualThief=s;
        if(temp!=null){
        temp.setImage(temp.type);
        temp.setNumber(temp.number);
        temp.hasThief=false;}
        actualThief.hasThief=true;
        actualThief.imageButton.setImageResource(R.drawable.c7);
    }
    int[][] calculateResult(HashMap<Integer,Space> spaces,int numero){
        int[][]  matriz =new int[4][5];
        int maxCantidad;
        int encontrados=0;
        if (numero==1||numero==12)maxCantidad=1;
        else maxCantidad=2;
        for (Map.Entry<Integer, Space> pair : spaces.entrySet()) {
            if(pair.getValue().number==numero){
                if(!pair.getValue().hasThief&&pair.getValue().type!=6){
                    for(Lot lot:pair.getValue().lots){
                        if(lot.color!=0) {
                            matriz[lot.color - 1][pair.getValue().type - 1] = matriz[lot.color - 1][pair.getValue().type - 1]+lot.level;
                        }
                    }
                }
                encontrados=encontrados+1;
                if(encontrados==maxCantidad)break;
            }
        }
        return matriz;
    }
    private class Space{
        ImageButton imageButton;
        int type=0;
        int number=0;
        int resource=0;
        boolean hasThief=false;
        Lot[] lots;
        @SuppressLint("ClickableViewAccessibility")
        public Space(int resource,int type,Lot[] lots){
            imageButton=(ImageButton) findViewById(resource);
            setType(type);
            setResource(resource);
            this.lots=lots;
            setImage(this.type);
            imageButton.setOnTouchListener((v, event) -> {
                Drawable drawable = imageButton.getBackground();
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                int x = (int) event.getX();
                int y = (int) event.getY();
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    int pixel = bitmap.getPixel(x,y);
                    if(Color.alpha(pixel)>0.5&&actualMode==1){
                        thiefIsHere(this);
                        thiefButton.setBackgroundColor(Color.LTGRAY);
                        actualMode=0;
                    }
                }
                return false;
            });
        }
        public void setType(int type) {
            this.type = type;
        }
        public void setImage(int type){
            switch (type){
                case 0:
                    imageButton.setBackgroundResource(R.drawable.cnull);
                    break;
                case 1:
                    imageButton.setBackgroundResource(R.drawable.csheep);
                    break;
                case 2:
                    imageButton.setBackgroundResource(R.drawable.crock);
                    break;
                case 3:
                    imageButton.setBackgroundResource(R.drawable.cbrick);
                    break;
                case 4:
                    imageButton.setBackgroundResource(R.drawable.cwheat);
                    break;
                case 5:
                    imageButton.setBackgroundResource(R.drawable.cwood);
                    break;
                case 6:
                    imageButton.setBackgroundResource(R.drawable.cdesert);
                    break;
                default:
                    imageButton.setBackgroundResource(R.drawable.cnull);
                    break;
            }
        }
        public void setNumber(int number){
            switch (number){
                case 2:
                    imageButton.setImageResource(R.drawable.c2);
                    break;
                case 3:
                    imageButton.setImageResource(R.drawable.c3);
                    break;
                case 4:
                    imageButton.setImageResource(R.drawable.c4);
                    break;
                case 5:
                    imageButton.setImageResource(R.drawable.c5);
                    break;
                case 6:
                    imageButton.setImageResource(R.drawable.c6);
                    break;
                case 7:
                    imageButton.setImageResource(android.R.color.transparent);
                    break;
                case 8:
                    imageButton.setImageResource(R.drawable.c8);
                    break;
                case 9:
                    imageButton.setImageResource(R.drawable.c9);
                    break;
                case 10:
                    imageButton.setImageResource(R.drawable.c10);
                    break;
                case 11:
                    imageButton.setImageResource(R.drawable.c11);
                    break;
                case 12:
                    imageButton.setImageResource(R.drawable.c12);
                    break;
                default:
                    imageButton.setBackgroundResource(R.drawable.cnull);
                    break;
            }
        }
        public void setResource(int resource) {
            this.resource = resource;
        }

    }
    private class Lot{
        int resource=0;
        int color=0;
        int level=0;
        ImageButton imageButton;
        @SuppressLint("ClickableViewAccessibility")
        public Lot(int resource, int color, int level){
            this.resource =resource;
            this.color=color;
            this.level=level;
            imageButton=(ImageButton) findViewById(resource);
            setImage(this.color,this.level);
            imageButton.setOnTouchListener((v, event) -> {
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    switch(this.level){
                        case 0:{setLevel(1);break;}
                        case 1:{setLevel(2);break;}
                        case 2:{setLevel(0);break;}
                    }
                    setColor(actualColor);
                    setImage(actualColor,this.level);
                }
                return false;
            });
        }

        private void setImage(int color, int level) {
            switch (color){
                case 1:
                    if(level==1){imageButton.setBackgroundResource(R.drawable.cwhite1);break;}
                    else if(level==2){imageButton.setBackgroundResource(R.drawable.cwhite2);break;}
                    else imageButton.setBackgroundResource(R.drawable.cnothing);break;
                case 2:
                    if(level==1){imageButton.setBackgroundResource(R.drawable.cred1);break;}
                    else if(level==2){imageButton.setBackgroundResource(R.drawable.cred2);break;}
                    else imageButton.setBackgroundResource(R.drawable.cnothing);break;
                case 3:
                    if(level==1){imageButton.setBackgroundResource(R.drawable.cblue1);break;}
                    else if(level==2){imageButton.setBackgroundResource(R.drawable.cblue2);break;}
                    else imageButton.setBackgroundResource(R.drawable.cnothing);break;
                case 4:
                    if(level==1){imageButton.setBackgroundResource(R.drawable.corange1);break;}
                    else if(level==2){imageButton.setBackgroundResource(R.drawable.corange2);break;}
                    else imageButton.setBackgroundResource(R.drawable.cnothing);break;
                default:imageButton.setBackgroundResource(R.drawable.cnothing);
                        break;
            }
        }

        public void setLevel(int level) {
            this.level = level;
        }
        public void setColor(int color) {
            this.color = color;
        }
    }
    private class ColorButton{
        ImageButton imageButton;
        int color=1;
        @SuppressLint("ClickableViewAccessibility")
        ColorButton(int resource){
            imageButton=(ImageButton) findViewById(resource);
            setImage();
            imageButton.setOnTouchListener((v, event) -> {
                if(event.getAction()==event.ACTION_DOWN) {
                    changeColor();
                    actualColor=color;
                }
                return false;
            });
        }
        void setImage(){
            switch(color){
                case 1:{imageButton.setBackgroundResource(R.drawable.cwhite2);break;}
                case 2:{imageButton.setBackgroundResource(R.drawable.cred2);break;}
                case 3:{imageButton.setBackgroundResource(R.drawable.cblue2);break;}
                case 4:{imageButton.setBackgroundResource(R.drawable.corange2);break;}
                default:{imageButton.setBackgroundResource(R.drawable.cnull);break;}
            }
        }
        void changeColor(){
            switch (color){
                case 1:{color=2;setImage();break;}
                case 2:{color=3;setImage();break;}
                case 3:{color=4;setImage();break;}
                case 4:{color=1;setImage();break;}
                default:{color=0;setImage();break;}
            }
        }
    }
}
