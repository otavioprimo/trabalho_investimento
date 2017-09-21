package investimento.codebrain.com.br.investimento_trampo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView txtAmount;
    TextView txtAmount_final;

    EditText edtInitial_value;
    EditText edtMonthly_application;
    EditText edtApplication_time;
    EditText edtTaxs;

    Button btnCalculate;
    Button btnClear;

    Double finalValue;

    Double initial_value;
    Double monthly_application;
    Double taxs ;
    Integer application_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAmount = findViewById(R.id.txtAmount);
        txtAmount_final = findViewById(R.id.txtAmountFinal);

        edtInitial_value = findViewById(R.id.edtInitialValue);
        edtMonthly_application = findViewById(R.id.edtMonthlyApplication);
        edtApplication_time = findViewById(R.id.edtApplicationTime);
        edtTaxs = findViewById(R.id.edtTax);

        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(validateView()){
                    finalValue = calculateAmount();
                    txtAmount.setVisibility(View.VISIBLE);
                    txtAmount_final.setVisibility(View.VISIBLE);

                    txtAmount.setText("R$ " + finalValue.toString());
                }
            }
        });
    }

    private Double calculateAmount(){

        initial_value = Double.parseDouble(edtInitial_value.getText().toString());
        monthly_application = Double.parseDouble(edtMonthly_application.getText().toString());
        taxs = Double.parseDouble(edtTaxs.getText().toString());
        application_time = Integer.parseInt(edtApplication_time.getText().toString());
        Double value = initial_value;
        for (int i = 1;i <= application_time; i++){
            value += (value+monthly_application)*taxs;
        }

        return value;
    }

    private boolean validateView(){
        if(TextUtils.isEmpty(edtInitial_value.getText().toString())){
            edtInitial_value.setError(getString(R.string.value_isempyt_BR));
            return false;
        }
        if(TextUtils.isEmpty(edtMonthly_application.getText().toString())){
            edtMonthly_application.setError(getString(R.string.value_isempyt_BR));
            return false;
        }
        if(TextUtils.isEmpty(edtApplication_time.getText().toString())){
            edtApplication_time.setError(getString(R.string.value_isempyt_BR));
            return false;
        }
        if(TextUtils.isEmpty(edtTaxs.getText().toString())){
            edtTaxs.setError(getString(R.string.value_isempyt_BR));
            return false;
        }

        return true;
    }
}
