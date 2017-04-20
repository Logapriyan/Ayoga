package logan.com.spinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main7Activity extends AppCompatActivity implements View.OnClickListener{

    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        home = (ImageButton) findViewById(R.id.imageButton8);

        home.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == home)
        {
            startActivity(new Intent(this, Main6Activity.class));

        }
    }
}
