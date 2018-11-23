package com.hva.joris.rollthedice;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int currentScore, highScore, lastNumber;
    private int[] images;
    private ImageView imageView;
    private ListView listView;
    private Button lowerButton, higherButton;
    private TextView currentScoreView, highScoreView;
    private ArrayList<String> rollList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link all UI items
        imageView = findViewById(R.id.diceImageView);
        listView = findViewById(R.id.result_list);
        lowerButton = findViewById(R.id.lower_button);
        higherButton = findViewById(R.id.higher_button);
        currentScoreView = findViewById(R.id.current_score_text);
        highScoreView = findViewById(R.id.high_score_text);

        rollList = new ArrayList<>();
        currentScore = 0;
        highScore = 0;
        lastNumber = 0;

        //Initialize the images
        images = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rollList);
        listView.setAdapter(adapter);
        updateUI(generateRandomNumberBetweenOneAndSix());

        higherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = generateRandomNumberBetweenOneAndSix();
                if (number > lastNumber) {
                    Snackbar.make(v,"Correct!", Snackbar.LENGTH_LONG).show();
                    currentScore++;
                    if (currentScore > highScore) {
                        highScore = currentScore;
                    }
                } else {
                    Snackbar.make(v,"Incorrect!", Snackbar.LENGTH_LONG).show();
                    currentScore = 0;
                }
                updateUI(number);
            }
        });

        lowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = generateRandomNumberBetweenOneAndSix();
                if (number < lastNumber) {
                    Snackbar.make(v,"Correct!", Snackbar.LENGTH_LONG).show();
                    currentScore++;
                    if (currentScore > highScore) {
                        highScore = currentScore;
                    }
                } else {
                    Snackbar.make(v,"Incorrect!", Snackbar.LENGTH_LONG).show();
                    currentScore = 0;
                }
                updateUI(number);
            }
        });

    }

    private int generateRandomNumberBetweenOneAndSix() {
        Random r = new Random();
        return r.nextInt((6 - 1) + 1) + 1;
    }

    private void updateUI(int number) {
        imageView.setImageResource(images[number - 1]);
        currentScoreView.setText("Score: " + Integer.toString(currentScore));
        highScoreView.setText("Highscore: " + Integer.toString(highScore));
        lastNumber = number;
        rollList.add("Throw is " + Integer.toString(number));
        adapter.notifyDataSetChanged();
    }
}
