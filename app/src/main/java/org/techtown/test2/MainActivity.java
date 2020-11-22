package org.techtown.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {


    class MyTimer2 extends CountDownTimer {

        public MyTimer2(long milliSec, long countSec) {
            super(milliSec, countSec);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            sleep(1000);
            game_board.setBackgroundResource(R.drawable.low1);
            System.out.println("__________________after sleep____________________");
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
            random_num = (int) (Math.random() * 16) + 1;
            game_board.setBackgroundResource(R.drawable.frame1 + random_num);
            question[i] = random_num;
            System.out.println("__________________before sleep____________________");

            myTimer2.start();
            i++;
        }

        @Override
        public void onFinish() {
            sleep(1000);
            last();
            System.out.println("------------------------TTTTHHHEEEENNNDDD_____________________");

        }
    }


    ImageView game_board;
    TextView main_txt;
    MyTimer myTimer;

    MyTimer2 myTimer2;

    int[] question = new int[20];//문제
    int[] game_answer = new int[18];//문제 정답  O : 0, X : 1
    int[] user_answer = new int[18];//사용자가 입력한 정답 O : 0, X : 1
    int total_cnt = 0; //answer이랑 guess 비교해서 똑같은 갯수


    int random_num = 0;
    int check_num = 0;
    int pushed = 0;


    Intent refresh = new Intent(this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTimer = new MyTimer(20000, 2000);
        myTimer2 = new MyTimer2(1000, 1000);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        game_board = (ImageView) findViewById(R.id.game_board);
        main_txt = (TextView) findViewById(R.id.main_txt);

        game_board.setBackgroundResource(R.drawable.frame1);
        Button btnO = (Button) findViewById(R.id.O_btn);
        Button btnX = (Button) findViewById(R.id.X_btn);
        btnO.setVisibility(View.GONE);
        btnX.setVisibility(View.INVISIBLE);

    }
    public void onButtonOClicked(View v){
        user_answer[pushed - 2] = 1;
    }
    public void onButtonXClicked(View v){

        user_answer[pushed - 2] = 2;
    }
    public void onButtonExChgClicked(View v){
        TextView txt = (TextView) findViewById(R.id.main_txt);
        Button start_btn = (Button) findViewById(R.id.start_btn);
        if (check_num == 0) {
            txt.setText("보드판 바뀌기 시작!");
            start_btn.setVisibility(View.INVISIBLE);
        }
        myTimer.start();

    }
    public void onButtonReClicked(View v){
//     Intent refresh = new Intent(this, Gaming5.class);
        startActivity(refresh);
        this.finish();


    }

    public void last() {
        ChoiceActivity ch = new ChoiceActivity();
        main_txt.setText("점수 책정 중..");
        bot();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("N_BACK");
        builder.setMessage("맞은갯수 : " + total_cnt);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
        main_txt.setText("게임 종료");
    }

    public void bot() {

        for (int i = 2; i < 20; i++) {
            if (question[i] == question[i - 2]) {
                game_answer[i - 2] = 1;
            } else {
                game_answer[i - 2] = 2;
            }
        }

        for (int i = 0; i < 18; i++) {
            if (game_answer[i] == user_answer[i]) {
                total_cnt++;
            }

        }
        System.out.println("=================================================");
        System.out.print("문제 : ");
        for (int i = 0; i < 20; i++) {

            System.out.print(question[i] + " ");

        }
        System.out.println();
        System.out.println("=================================================");
        System.out.print("게임정답   : ");
        for (int i = 0; i < 18; i++) {

            System.out.print(game_answer[i] + " ");

        }
        System.out.println();
        System.out.println("=================================================");
        System.out.print("사용자정답 : ");
        for (int i = 0; i < 18; i++) {

            System.out.print(user_answer[i] + " ");

        }
        System.out.println();
        System.out.println("=================================================");

    }

}
