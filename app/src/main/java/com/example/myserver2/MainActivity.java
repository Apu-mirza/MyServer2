package com.example.myserver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageAdapter adapter;
    private TextView textViewImageCount;
    private FloatingActionButton fabArrow, fabArrow2;

    private int[] images = {R.drawable.mirza2, R.drawable.mirza1, R.drawable.image3, R.drawable.image4, R.drawable.image5};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        textViewImageCount = findViewById(R.id.textViewImageCount);
        fabArrow = findViewById(R.id.fabArrow);
        fabArrow2 = findViewById(R.id.fabArrow2);

        adapter = new ImageAdapter(this, images);
        viewPager.setAdapter(adapter);

        updateImageCount(1); // Display initial count

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateImageCount(position + 1); // Update count when page changes
                updateFabVisibility(position);
                updateFab2Visibility(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        fabArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });
        fabArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

    }

    private void updateImageCount(int currentPosition) {
        int totalImages = adapter.getCount();
        textViewImageCount.setText(currentPosition + "/" + totalImages);
    }

    private void updateFabVisibility(int currentPosition) {
        int totalImages = adapter.getCount();
        if (currentPosition < totalImages - 1) {
            fabArrow.setVisibility(View.VISIBLE);
        } else {
            fabArrow.setVisibility(View.GONE);
        }
    }
    private void updateFab2Visibility(int currentPosition) {
        int totalImages = adapter.getCount();
        if (currentPosition >= 1) {
            fabArrow2.setVisibility(View.VISIBLE);
        } else {
            fabArrow2.setVisibility(View.GONE);
        }
    }
}

