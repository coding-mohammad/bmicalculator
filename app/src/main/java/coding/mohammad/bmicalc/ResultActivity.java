package coding.mohammad.bmicalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtToolbar, txtBmiResult, txtBmiStatus;
    ImageView imgRecalculate, imgShareResult;

    float bmi;

    String textToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();
        setToolbar();

        getValues();

        setImgClicks();

    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        txtToolbar = findViewById(R.id.txt_toolbar);
        txtBmiResult = findViewById(R.id.txt_bmi_result);
        txtBmiStatus = findViewById(R.id.txt_bmi_status);
        imgRecalculate = findViewById(R.id.img_recalculate);
        imgShareResult = findViewById(R.id.img_share_result);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        txtToolbar.setText(getString(R.string.txt_bmi_result));
    }

    private void getValues() {
        Bundle incomingBundle = getIntent().getExtras();
        if (incomingBundle != null) {
            bmi = incomingBundle.getFloat("bmi");
            makeChanges();
        } else {
            Toast.makeText(this, "error during getting results", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void makeChanges() {
        txtBmiResult.setText(String.valueOf(bmi));
        if (bmi < 18.5) {
            txtBmiStatus.setText(getString(R.string.txt_Underweight));
            txtBmiStatus.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
        } else if (bmi > 18.5 && bmi < 25) {
            txtBmiStatus.setText(getString(R.string.txt_Normal));
            txtBmiStatus.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        } else if (bmi > 25 && bmi < 30) {
            txtBmiStatus.setText(getString(R.string.txt_Overweight));
            txtBmiStatus.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        } else if (bmi > 30) {
            txtBmiStatus.setText(getString(R.string.txt_Obese));
            txtBmiStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            txtBmiStatus.setText(getString(R.string.txt_Unidentified));
            txtBmiStatus.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    private void setImgClicks() {
        imgRecalculate.setOnClickListener(v -> finish());
        imgShareResult.setOnClickListener(v -> {
            textToShare = "Body Mass Index : " + bmi + "\nStatus : " + txtBmiStatus.getText().toString();
            Intent shareText = new Intent(Intent.ACTION_SEND);
            shareText.setType("text/plain");
            shareText.putExtra(Intent.EXTRA_TEXT, textToShare);
            startActivity(Intent.createChooser(shareText, "share result"));
        });
    }


}