package com.example.myapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class EkranPrzepisow extends AppCompatActivity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_przepisow);
        back = (Button) findViewById(R.id.backMain);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    private void backToMain() {
        Intent intent = new Intent(this, EkranGlowny.class);
        startActivity(intent);
    }
}