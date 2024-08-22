package com.example.myquizapp;

import static android.graphics.Color.*;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;
    int score=0;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextView= findViewById(R.id.total_questions);
        questionTextView= findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        submitBtn= findViewById(R.id.submit_btn);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions:"+ totalQuestion);
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(BLACK);
        ansB.setBackgroundColor(BLACK);
        ansC.setBackgroundColor(BLACK);
        ansD.setBackgroundColor(BLACK);

        Button clickButton= (Button) view;
        if(clickButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else {
            selectedAnswer= clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.MAGENTA);


        }

    }

    void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }



        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus="";
        if(score>totalQuestion*0.60){
            passStatus="Passed";

        }else {
            passStatus= "Failed";

        }
        new AlertDialog.Builder(this).setTitle(passStatus).setMessage("Score is "+score+" out of "+totalQuestion).setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz()).setCancelable(false).show();

    }
       void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
       }


}