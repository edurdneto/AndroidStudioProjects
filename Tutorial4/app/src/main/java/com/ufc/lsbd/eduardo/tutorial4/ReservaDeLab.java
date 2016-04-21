package com.ufc.lsbd.eduardo.tutorial4;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ReservaDeLab extends Activity {
    public int year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_de_lab);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[]lab =getResources().getStringArray(R.array.lab);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,lab);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?>parent,View view,int pos,long id){
            parent.getItemAtPosition(pos);
        }
        public void onNothingSelected(AdapterView<?>parent){

        }
    }

    public void showTimePickerDialog(View view){
        DialogFragment newFragment= new TimePickerFragment();
        ((TimePickerFragment) newFragment).setTimeTextView((TextView) findViewById(R.id.textView9));
        newFragment.show(getFragmentManager(), "Picker");
    }

    public void showDatePickerDialog(View view){
        DialogFragment newFragment=new DataPickerFragment();
        ((DataPickerFragment) newFragment).setDateTextView((TextView) findViewById(R.id.textView8));
        newFragment.show(getFragmentManager(),"datePiker");
    }

    public void enviarEmail(View view){

        String rb = new String();
        String cb = new String();
        String texto2 = new String();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton);
        ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);

        if(rb1.isChecked()) rb="Sim";
        else rb="Nao";



        String texto="nome:"+((EditText)findViewById(R.id.editText)).getText().toString()+"\n" +
                "SIAPE:"+((EditText)findViewById(R.id.editText2)).getText().toString()+"\n"+
                "email:"+((EditText)findViewById(R.id.editText3)).getText().toString()+"\n"+
                "dados da reserva:\nLab:"+spinner.getSelectedItem().toString()+"\n"+
                "Data da Reserva:"+((TextView)findViewById(R.id.textView8)).getText().toString()+"  "+
                "Hora da Reserva:"+((TextView)findViewById(R.id.textView9)).getText().toString()+"\n"+
                "Vai precisar de datashow?"+rb+"\n";
        if(check.isChecked())cb="(x)";
        else cb="()";
                texto2=cb+"IDE  Eclipse  +  ADT  Bu\n";
        if(check2.isChecked())cb="(x)";
        else cb="()";
                texto2=texto2+cb+"Java  SDK\n";
        if(check3.isChecked())cb="(x)";
        else cb="()";
                texto2=texto2+cb+"Sistema Operacional Linux\n";
        if(check4.isChecked())cb="(x)";
        else cb="()";
                texto2=texto2+cb+"Windows\nReserva Prioritaria?\n";
        if(tb.isChecked())texto2=texto2+"Sim\n";
        else texto2=texto2+"Nao\n";

        texto2= texto2+"Obeservacoes:\n"+((EditText)findViewById(R.id.editText4)).getText().toString();

        String texto3=new String();
        texto3=texto+texto2;
        System.out.println(texto3);
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {"eduardo.rodrigues@lsbd.ufc.br"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        email.putExtra(Intent.EXTRA_TEXT, texto3);
        email.setType("plain/text");
        startActivity(
                Intent.createChooser
                        (email, "Sending mail..."));
    }


}
