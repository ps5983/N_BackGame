package org.techtown.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        TextView textView=(TextView)findViewById(R.id.main_txt);
    }
    public void onButton5Clicked(View v){
        Toast.makeText(this,"1단계로 이동",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Gaming5.class);
        startActivity(intent);
    }
    public void onButton16Clicked(View v){
        Toast.makeText(this,"2단계로 이동",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
//    public void recall(){
//
//    }
}