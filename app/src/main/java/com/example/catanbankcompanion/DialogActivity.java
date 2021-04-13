package com.example.catanbankcompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends Activity {

    ImageView[] allImages;
    TextView[] allText;
    Intent intent;
    int[][] result;
    int numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result_dialog);
        intent=getIntent();
        result= (int[][])intent.getSerializableExtra("matriz");
        numero=intent.getIntExtra("numero",7);
        ImageView p1a=(ImageView)findViewById(R.id.p1a);
        ImageView p1b=(ImageView)findViewById(R.id.p1b);
        ImageView p2a=(ImageView)findViewById(R.id.p2a);
        ImageView p2b=(ImageView)findViewById(R.id.p2b);
        ImageView p3a=(ImageView)findViewById(R.id.p3a);
        ImageView p3b=(ImageView)findViewById(R.id.p3b);
        ImageView p4a=(ImageView)findViewById(R.id.p4a);
        ImageView p4b=(ImageView)findViewById(R.id.p4b);
        TextView text1a=(TextView)findViewById(R.id.text1a);
        TextView text1b=(TextView)findViewById(R.id.text1b);
        TextView text2a=(TextView)findViewById(R.id.text2a);
        TextView text2b=(TextView)findViewById(R.id.text2b);
        TextView text3a=(TextView)findViewById(R.id.text3a);
        TextView text3b=(TextView)findViewById(R.id.text3b);
        TextView text4a=(TextView)findViewById(R.id.text4a);
        TextView text4b=(TextView)findViewById(R.id.text4b);
        TextView title=(TextView)findViewById(R.id.titlet);
        allImages=new ImageView[]{p1a,p1b,p2a,p2b,p3a,p3b,p4a,p4b};
        allText=new TextView[]{text1a,text1b,text2a,text2b,text3a,text3b,text4a,text4b};
        title.setText("RESULTADOS PARA "+numero);
        restartImages();
        restartText();
        showResult();
    }
    void restartImages(){
        int i=0;
        for(i=0;i<allImages.length;i++){
            allImages[i].setBackgroundResource(R.drawable.ccardnull);
        }
    }
    void restartText(){
        for(TextView text:allText){
            text.setText("");
        }
    }
    void showResult(){
        int i=0;
        int j=0;
        int actual=0;
        for(i=0;i<4;i++){
            actual=0;
            for(j=0;j<5;j++){
                if(result[i][j]!=0){
                    allImages[(i*2)+actual].setBackgroundResource(cardResourceByType(j));
                    allText[(i*2)+actual].setText("X "+result[i][j]);
                    actual++;
                    if(actual==2){break;}
                }
            }
        }
    }
    int cardResourceByType(int type){
        switch (type){
            case 0:return R.drawable.ccardsheep;
            case 1:return  R.drawable.ccardrock;
            case 2:return R.drawable.ccardbrick;
            case 3:return  R.drawable.ccardwheat;
            case 4:return R.drawable.ccardwood;
            default:return  R.drawable.cnothing;
        }
    }
    void showResultAsText(int[][] result){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "p1-white:["+result[0][0]+"]["+result[0][1]+"]["+result[0][2]+"]["+result[0][3]+"]["+result[0][4]+"]\n"+
                                "p2-red:["+result[1][0]+"]["+result[1][1]+"]["+result[1][2]+"]["+result[1][3]+"]["+result[1][4]+"]\n"+
                                "p3-blue:["+result[2][0]+"]["+result[2][1]+"]["+result[2][2]+"]["+result[3][3]+"]["+result[2][4]+"]\n"+
                                "p4-orange:["+result[3][0]+"]["+result[3][1]+"]["+result[3][2]+"]["+result[3][3]+"]["+result[3][4]+"]\n",Toast.LENGTH_LONG);

        toast1.show();
    }
}

