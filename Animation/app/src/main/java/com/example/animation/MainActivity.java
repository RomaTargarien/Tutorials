package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private boolean firstImageShown = true;

    public void tomAway(View view) {
        ImageView firstImage = findViewById(R.id.TomimageView);
        ImageView secondImage = findViewById(R.id.JerryimageView);

        if ((firstImage != null) && (firstImageShown)) {
            firstImage.animate().scaleX(0).scaleY(0).alpha(0).rotation(firstImage.getRotation()+3600).setDuration(3000);
            secondImage.animate().scaleY(1).scaleX(1).alpha(1).rotation(secondImage.getRotation()+3600).setDuration(3000);
            firstImageShown = false;
        } else {
            if (firstImage != null){
                secondImage.animate().scaleX(0).scaleY(0).alpha(0).rotation(secondImage.getRotation()+3600).setDuration(3000);
                firstImage.animate().scaleY(1).scaleX(1).alpha(1).rotation(firstImage.getRotation()+3600).setDuration(3000);
            }
            firstImageShown = true;
        }
    }
}