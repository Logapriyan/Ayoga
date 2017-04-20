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

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    Button cancel;
    ImageButton home;
    EditText numcancel;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cancel = (Button) findViewById(R.id.buttoncancel);
        home = (ImageButton) findViewById(R.id.imageButton7);
        numcancel = (EditText)findViewById(R.id.editn);

        cancel.setOnClickListener(this);
        home.setOnClickListener(this);

        db = openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);

    }
    @Override
    public void onClick(View view)
    {
       if(view == cancel)
       {
           if (numcancel.getText().toString().trim().length() == 0)
           {
               showMessage("Error", "Please enter the number");
               return;
           }
           else
           {
               Cursor c=db.rawQuery("SELECT * FROM booking WHERE phone='"+numcancel.getText()+"'", null);
               if(c.getCount()==0)
               {
                   showMessage("Error", "Invalid Number");
               }
               else
               {
                   db.execSQL("DELETE FROM booking WHERE phone='"+numcancel.getText()+"'");
                   showMessage("Success", "BOOKING CANCELLED");
               }
           }
       }
        if (view == home)
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
