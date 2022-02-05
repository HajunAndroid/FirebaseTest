package com.exmaple.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // 파이어베이스 데이터베이스 연동
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    private FirebaseAnalytics analytics;

    Button btn, btn2;
    EditText edit1, edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn); //버튼 아이디 연결
        btn2 = findViewById(R.id.btn2);
        edit1 = findViewById(R.id.edit1); //동물 이름 적는 곳
        edit2 = findViewById(R.id.edit2); //동물 종류 적는 곳

        analytics = FirebaseAnalytics.getInstance(this);

        //버튼 누르면 값을 저장
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //에딧 텍스트 값을 문자열로 바꾸어 함수에 넣어줍니다.
                addanimal(edit1.getText().toString(),edit2.getText().toString());
            }
        });


    }


    //값을 파이어베이스 Realtime database로 넘기는 함수
    public void addanimal(String name, String kind) {

        //여기에서 직접 변수를 만들어서 값을 직접 넣는것도 가능합니다.
        // ex) 갓 태어난 동물만 입력해서 int age=1; 등을 넣는 경우

        //animal.java에서 선언했던 함수.
        animal animal = new animal(name,kind);

        //child는 해당 키 위치로 이동하는 함수입니다.
        //키가 없는데 "zoo"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        databaseReference.child("zoo").child(name).setValue(animal);

    }

    public void btn2(View view){
        Bundle bundle = new Bundle(); // logEvent()까지 추가
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "탭 레이아웃으로 유저가 들어왔습니다");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        Toast.makeText(MainActivity.this, String.valueOf(bundle), Toast.LENGTH_SHORT).show();
    }

    public void btn3(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}