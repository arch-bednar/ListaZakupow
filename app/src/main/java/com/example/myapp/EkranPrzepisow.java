package com.example.myapp;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.example.myapp.logic.AppLogic;
        import com.example.myapp.logic.BazaProduktow;
        import com.example.myapp.logic.BazaPrzepisow;

        import java.io.File;

public class EkranPrzepisow extends AppCompatActivity {
    private Button back;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapter3 adapter;
    public AppLogic logic;
    private File directory;


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

        directory  = getFilesDir();
        logic = new AppLogic(directory);
        System.out.println("Ekran Przepisow");
        logic.getRecipesBase().printOut();

        BazaPrzepisow data = logic.getRecipesBase();

        recycle = (RecyclerView) findViewById(R.id.recycleRecipe);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapter3(data,this);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);
    }

    private void backToMain() {
        Intent intent = new Intent(this, EkranGlowny.class);
        startActivity(intent);
    }
}