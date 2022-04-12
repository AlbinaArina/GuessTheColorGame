package com.example.guessthecolorgamejava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private TextView color1;
    private TextView color2;
    private TextView resColor1;
    private TextView resColor2;
    private TextView resColor3;
    private TextView resColor4;
    private TextView resColor5;
    private TextView points;
    private int countPoints;

    private Color correctColor;
    private TextView correctTextView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color1 = findViewById(R.id.color1);
        color2 = findViewById(R.id.color2);
        resColor1 = findViewById(R.id.resColor1);
        resColor2 = findViewById(R.id.resColor2);
        resColor3 = findViewById(R.id.resColor3);
        resColor4 = findViewById(R.id.resColor4);
        resColor5 = findViewById(R.id.resColor5);
        points = findViewById(R.id.points);
        countPoints = 0;

        setAllValues();

        resColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult((TextView) v);
            }
        });

        resColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult((TextView) v);
            }
        });

        resColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult((TextView) v);
            }
        });

        resColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult((TextView) v);
            }
        });

        resColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult((TextView) v);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showResult(TextView textView) {
        if (textView.equals(correctTextView)) {
            countPoints++;
            if (countPoints == 10) {
                setCountPoints(0);
                Toast.makeText(MainActivity.this, R.string.winner, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, R.string.answer_correct, Toast.LENGTH_LONG).show();
            }
        } else {
            countPoints--;
            if (countPoints == -10) {
                setCountPoints(0);
                Toast.makeText(MainActivity.this, R.string.loser, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, R.string.answer_incorrect, Toast.LENGTH_LONG).show();
            }
        }

        setAllValues();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Color getRandomColor() {
        int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B= (int)(Math.random()*256);
        return Color.valueOf(R, G, B);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getCorrectColor(Color c1, Color c2) {
        int R = (int) (c1.red() + c2.red()) / 2;
        int G = (int) (c1.green() + c2.green()) / 2;
        int B = (int) (c1.blue() + c2.blue()) / 2;
        setCorrectColor(Color.valueOf(R, G, B));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setAllValues() {
        points.setText(String.valueOf(getCountPoints()));
        Color c1 = getRandomColor();
        Color c2 = getRandomColor();
        setColorToCircle(color1, c1);
        setColorToCircle(color2, c2);
        getCorrectColor(c1, c2);

        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            colors.add(getRandomColor());
        }
        colors.add(correctColor);

        List<TextView> set = new ArrayList<>();
        set.add(resColor1);
        set.add(resColor2);
        set.add(resColor3);
        set.add(resColor4);
        set.add(resColor5);
        Collections.shuffle(set);

        for(TextView tv : set) {
            Color c = colors.get(0);
            colors.remove(0);
            setColorToCircle(tv, c);
            if (colors.isEmpty()) {
                setCorrectTextView(tv);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setColorToCircle(TextView circle, Color color) {
        circle.getBackground().setColorFilter(color.toArgb(), PorterDuff.Mode.SRC_IN);
    }

    public void setCorrectColor(Color correctColor) {
        this.correctColor = correctColor;
    }

    public void setCorrectTextView(TextView correctTextView) {
        this.correctTextView = correctTextView;
    }

    public void setCountPoints(int countPoints) {
        this.countPoints = countPoints;
    }

    public int getCountPoints() {
        return countPoints;
    }
}