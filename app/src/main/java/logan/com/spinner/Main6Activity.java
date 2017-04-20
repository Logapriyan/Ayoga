package logan.com.spinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener{

    ImageButton book,cancel,viewing,about,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        cancel = (ImageButton) findViewById(R.id.imageButton2);
        book = (ImageButton) findViewById(R.id.booking);
        address = (ImageButton) findViewById(R.id.imageButton4);
        viewing = (ImageButton) findViewById(R.id.imageButton3);
        about = (ImageButton) findViewById(R.id.imageButton5);

        cancel.setOnClickListener(this);
        book.setOnClickListener(this);
        address.setOnClickListener(this);
        viewing.setOnClickListener(this);
        about.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view == book)
        {
            startActivity(new Intent(this, MainActivity.class));

        }
        if(view == cancel)
        {
            startActivity(new Intent(this, Main3Activity.class));
        }
        if(view == viewing)
        {
            startActivity(new Intent(this, Main4Activity.class));
        }
        if(view == address)
        {
            startActivity(new Intent(this, Main7Activity.class));
        }
        if(view == about)
        {
            startActivity(new Intent(this, Main8Activity.class));
        }
    }
}
