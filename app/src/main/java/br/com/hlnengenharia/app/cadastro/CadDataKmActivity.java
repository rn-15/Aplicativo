package br.com.hlnengenharia.app.cadastro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.hlnengenharia.app.InspecaoActivity;
import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.pCarroDAO;
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.DataHoraCarro;
import br.com.hlnengenharia.app.model.RespostaCarro;

public class CadDataKmActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialogData;
    private EditText data, edthora, km;
    private Button salvar;
    private TextView idCarro, carronome;
    private RespostaCarro respostaCarro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_data_km);

        setTitle("");

        calendario();
        mostraCalendario();
        carregaHora();
        carregaDadosCarro();
        botaoSalvar();
    }

    private void carregaDadosCarro() {
        Intent intent = getIntent();
        Carro carro = (Carro) intent.getSerializableExtra("carro");
        carronome = findViewById(R.id.carronome);
        carronome.setText(carro.getNome());
        idCarro = findViewById(R.id.carroid);
        idCarro.setText(carro.getId().toString());
    }


    private void botaoSalvar() {
        salvar = findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = findViewById(R.id.data);
                km = findViewById(R.id.km);
                idCarro = findViewById(R.id.carroid);

                pCarroDAO dao = new pCarroDAO(CadDataKmActivity.this);
                DataHoraCarro horaData = new DataHoraCarro();

                horaData.setIdCarro(Long.valueOf(idCarro.getText().toString()));
                horaData.setData(data.getText().toString());
                horaData.setHora(edthora.getText().toString());
                horaData.setKm(km.getText().toString());


                dao.pega(horaData);
                dao.close();
                finish();

            }
        });
    }

    private void mostraCalendario() {
        data = findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogData.show();
            }
        });
    }

    private void carregaHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date hora = Calendar.getInstance().getTime();
        String dataFormatada = sdf.format(hora);
        edthora = findViewById(R.id.hora);
        edthora.setText(dataFormatada);
    }

    private void calendario() {
        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual   = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual   = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual   = calendarDataAtual.get(Calendar.DAY_OF_MONTH);
        datePickerDialogData = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
                String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ? "0" + (mesSelecionado + 1 ): String.valueOf(mesSelecionado));
                data = findViewById(R.id.data);
                data.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);
            }

        }, anoAtual, mesAtual, diaAtual);
    }
}
