package org.techtown.test2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import static android.os.SystemClock.sleep;

public class Gaming5 extends AppCompatActivity {


    class MyTimer2 extends CountDownTimer {

        public MyTimer2(long milliSec, long countSec) {
            super(milliSec, countSec);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            sleep(1000);
            game_board.setBackgroundResource(R.drawable.low1);
            System.out.println("__________________after sleep____________________");
//            if(onButtonOClicked()){
//
//            }

        }

        @Override
        public void onFinish() {

            System.out.println("------------------------TTTTHHHEEEENNNDDD777777_____________________");

        }
    }


    class MyTimer extends CountDownTimer {

        public MyTimer(long milliSec, long countSec) {
            super(milliSec, countSec);
        }

        int i = 0;

        @Override
        public void onTick(long millisUntilFinished) {
            Button btnO = (Button) findViewById(R.id.O_btn);
            Button btnX = (Button) findViewById(R.id.X_btn);
            if (i > 1) {
                btnO.setVisibility(View.VISIBLE);
                btnX.setVisibility(View.VISIBLE);
            }
            main_txt.setText(String.valueOf(i + 1));
            pushed = i;
            random_num = (int) (Math.random() * 5) + 1;
            game_board.setBackgroundResource(R.drawable.low1 + random_num);
            question[i] = random_num;
            System.out.println("__________________before sleep____________________");


            myTimer2.start();
//            if(onButtonOClicked()){
//
//            }
//            if (i == 9) {
//
//            }
            i++;
        }


        @Override
        public void onFinish() {
            sleep(1000);
            last();
            System.out.println("------------------------TTTTHHHEEEENNNDDD_____________________");

        }
    }

    class ReadyTimer extends CountDownTimer {

        public ReadyTimer(long milliSec, long countSec) {
            super(milliSec, countSec);
        }

        int i = 2;

        @Override
        public void onTick(long millisUntilFinished) {
            game_board.setBackgroundResource(R.drawable.ready1 + i);
            i--;
        }


        @Override
        public void onFinish() {
            System.out.println("===============================시작===============================");
            myTimer.start();
        }
    }


    private static final String TAG = "MainActivity";
    ImageView game_board;
    TextView main_txt;
    MyTimer myTimer;
    MyTimer2 myTimer2;
    ReadyTimer ready;

    int[] question = new int[10];//문제
    int[] game_answer = new int[8];//문제 정답  O : 0, X : 1
    int[] user_answer = new int[8];//사용자가 입력한 정답 O : 0, X : 1
    int total_cnt = 0; //answer이랑 guess 비교해서 똑같은 갯수


    int random_num = 0;
    int check_num = 0;
    int pushed = 0;


    Intent refresh = new Intent(this, Gaming5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming5);
        myTimer = new MyTimer(20000, 2000);
        myTimer2 = new MyTimer2(1000, 1000);
        ready = new ReadyTimer(3000,1000);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        game_board = (ImageView) findViewById(R.id.game_board);
        main_txt = (TextView) findViewById(R.id.main_txt);

        game_board.setBackgroundResource(R.drawable.low1);
        Button btnO = (Button) findViewById(R.id.O_btn);
        Button btnX = (Button) findViewById(R.id.X_btn);
        btnO.setVisibility(View.GONE);
        btnX.setVisibility(View.INVISIBLE);

    }

    public void onButtonExChgClicked(View v) {
        TextView txt = (TextView) findViewById(R.id.main_txt);
        Button start_btn = (Button) findViewById(R.id.start_btn);
        if (check_num == 0) {
            txt.setText("준비");
            start_btn.setVisibility(View.INVISIBLE);
        }
        ready.start();
    }

    public void onButtonOClicked(View v) {

        user_answer[pushed - 2] = 1;

    }

    public void onButtonXClicked(View v) {

        user_answer[pushed - 2] = 2;
    }

    public void onButtonReClicked(View v) {

        Intent refresh = new Intent(this, Gaming5.class);
        startActivity(refresh);
        this.finish();
    }

    public void last() {
        ChoiceActivity ch = new ChoiceActivity();
        main_txt.setText("점수 책정 중..");
        bot();

        AlertDialog.Builder builder = new AlertDialog.Builder(Gaming5.this);
        builder.setTitle("N_BACK");
        builder.setMessage("맞은갯수 : " + total_cnt+"/8");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
        main_txt.setText("게임 종료");
    }

    public void bot() {

        for (int i = 2; i < 10; i++) {
            if (question[i] == question[i - 2]) {
                game_answer[i - 2] = 1;
            } else {
                game_answer[i - 2] = 2;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (game_answer[i] == user_answer[i]) {
                total_cnt++;
            }

        }
        System.out.println("=================================================");
        System.out.print("문제 : ");
        for (int i = 0; i < 10; i++) {

            System.out.print(question[i] + " ");

        }
        System.out.println();
        System.out.println("=================================================");
        System.out.print("게임정답   : ");
        for (int i = 0; i < 8; i++) {

            System.out.print(game_answer[i] + " ");

        }
        System.out.println();
        System.out.println("=================================================");
        System.out.print("사용자정답 : ");
        for (int i = 0; i < 8; i++) {

            System.out.print(user_answer[i] + " ");

        }
        System.out.println();
        System.out.println("=================================================");

    }
}


