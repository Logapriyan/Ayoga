package logan.com.spinner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    Button viewing;
    ImageButton homing;
    EditText numview;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        viewing = (Button) findViewById(R.id.button2);
        homing = (ImageButton) findViewById(R.id.imageButton9);
        numview = (EditText)findViewById(R.id.edittingnum);

        viewing.setOnClickListener(this);
        homing.setOnClickListener(this);

        db = openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);

    }
    @Override
    public void onClick(View view)
    {
        if(view == viewing)
        {
            if (numview.getText().toString().trim().length() == 0)
            {
                showMessage("Error", "Please enter the number");
                return;
            }
            else
            {
                Cursor c=db.rawQuery("SELECT * FROM booking WHERE phone='"+numview.getText()+"'", null);
                if(c.getCount()==0)
                {

                    showMessage("Error", "No booking found");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Name: "+c.getString(0)+"\n");
                    buffer.append("Date: "+c.getString(1)+"\n");
                    buffer.append("Time: "+c.getString(2)+"\n");
                    buffer.append("Service: "+c.getString(4)+"\n\n");
                }
                showMessage("Booking Details", buffer.toString());

            }

        }
        if(view == homing)
        {
            startActivity(new Intent(this, Main6Activity.class));
        }
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
