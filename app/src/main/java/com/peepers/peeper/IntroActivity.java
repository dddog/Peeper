package com.peepers.peeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class IntroActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button agreeButton = (Button) findViewById(R.id.agreeButton);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox agreeCheckBox = (CheckBox) findViewById(R.id.agreeCheckBox);
                if( !agreeCheckBox.isChecked() ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(IntroActivity.this);
                    dialog = builder.setMessage("이용 약관에 동의해 주시기 바랍니다.")
                            .setNegativeButton("다시 시도", null)
                            .create();
                    dialog.show();
                } else {
                    Intent intent = new Intent(IntroActivity.this, SiteListActivity.class);
                    IntroActivity.this.startActivity(intent);
                    finish();
                }
            }
        });
    }
}
