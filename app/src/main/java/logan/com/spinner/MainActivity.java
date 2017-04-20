package logan.com.spinner;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker,book;
    ImageButton home;
    EditText txtDate, txtTime,txtphone,txtname;
    SQLiteDatabase db;
    private int mYear, mMonth, mDay, mHour, mMinute,mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.FACIAL, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,View view,int positon,long id){
                String selection = (String)parent.getItemAtPosition(positon);
                if(!TextUtils.isEmpty(selection)){
                    if(selection.equals("FACIAL")) {
                        mService = 1;
                    }else if (selection.equals("EYE THREAD" )) {
                        mService = 2;
                    }
                    else if(selection.equals("BLEECH")) {
                        mService = 3;
                    }else if(selection.equals("HAIR CUT")) {
                        mService = 4;
                    }else
                    {
                        mService = 5;
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mService = 0;
            }
        });



       /* Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
           }
        }*/

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        home = (ImageButton) findViewById(R.id.imageButton10) ;
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        txtname = (EditText) findViewById(R.id.editname);
        txtphone = (EditText) findViewById(R.id.editnum);
        book = (Button) findViewById(R.id.btn_book);


        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        book.setOnClickListener(this);
        home.setOnClickListener(this);


        db = openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS booking(name VARCHAR,date VARCHAR,time VARCHAR,phone VARCHAR,service VARCHAR);");


    }


    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }

       if(v == book) {
           if (txtname.getText().toString().trim().length() == 0 ||
                   txtDate.getText().toString().trim().length() == 0 ||
                   txtTime.getText().toString().trim().length() == 0 ||
                   txtphone.getText().toString().trim().length() == 0)
           {
               showMessage("Error", "Please enter all values");
               return;
           } else
           {
               Cursor c = db.rawQuery("select date,time from booking WHERE date='" + txtDate.getText() + "' AND time='" + txtTime.getText() + "'", null);
               if (c.getCount() == 0)
               {
                   db.execSQL("INSERT INTO booking VALUES('" + txtname.getText() + "','" + txtDate.getText() + "','" + txtTime.getText() + "','" + txtphone.getText() + "','" + mService + "');");
                   showMessage("Success", "Record added");
                   clearText();


                   startActivity(new Intent(this, Main2Activity.class));

               }
               showMessage("Error", "ALREADY SOMEONE HAVE BOOKED IN THIS TIME, PLEASE PROVIDE ANOTHER TIME WITH 30MIN LATER FROM THIS TIME ");
               return;


           }
       }
        if(v == home)
        {
            startActivity(new Intent(this, Main6Activity.class));
        }

    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        txtname.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtphone.setText("");


    }




}
