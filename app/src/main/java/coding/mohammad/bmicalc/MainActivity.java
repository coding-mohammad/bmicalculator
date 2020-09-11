package coding.mohammad.bmicalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtAge, txtWeight, txtHeight;
    ImageView imgAddAge, imgMinusAge, imgAddWeight, imgMinusWeight;
    SeekBar seekBarHeight;
    Button btnCalculate;

    int age, weight, height;
    float heightf, bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setToolbar();

        initValues();

        setSeekBarHeight();
        setAddMinusClicks();
        setBtnCalculate();

    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        txtAge = findViewById(R.id.txt_age);
        txtWeight = findViewById(R.id.txt_weight);
        txtHeight = findViewById(R.id.txt_height);
        imgAddAge = findViewById(R.id.img_add_age);
        imgMinusAge = findViewById(R.id.img_minus_age);
        imgAddWeight = findViewById(R.id.img_add_weight);
        imgMinusWeight = findViewById(R.id.img_minus_weight);
        seekBarHeight = findViewById(R.id.seekbar_height);
        btnCalculate = findViewById(R.id.btn_calculate_bmi);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initValues() {
        age = 20;
        weight = 75;
        height = 175;
        txtAge.setText(String.valueOf(age));
        txtWeight.setText(String.valueOf(weight));
        txtHeight.setText(String.valueOf(height));
    }

    private void setSeekBarHeight() {
        seekBarHeight.setMax(350);
        seekBarHeight.setProgress(height);
        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height = progress;
                txtHeight.setText(String.valueOf(height));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setAddMinusClicks() {
        imgAddAge.setOnClickListener(v -> {
            age += 1;
            txtAge.setText(String.valueOf(age));
        });
        imgAddWeight.setOnClickListener(v -> {
            weight += 1;
            txtWeight.setText(String.valueOf(weight));
        });
        imgMinusAge.setOnClickListener(v -> {
            if (age > 1) {
                age -= 1;
                txtAge.setText(String.valueOf(age));
            }
        });
        imgMinusWeight.setOnClickListener(v -> {
            if (weight > 1) {
                weight -= 1;
                txtWeight.setText(String.valueOf(weight));
            }
        });
    }

    private void setBtnCalculate() {
        btnCalculate.setOnClickListener(v -> {
            heightf = (float) height/100;
            bmi = (float) weight/(heightf * heightf) ;
            Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
            resultIntent.putExtra("bmi", bmi);
            startActivity(resultIntent);
        });
    }

}