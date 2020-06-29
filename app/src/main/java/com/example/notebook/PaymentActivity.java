package com.example.notebook;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText mInputMoney;
    private EditText mInputInfo;
    private Button mBtnOk;
    private CheckBox mBankCardChkBx;
    private CheckBox mMobilePhoneChkBx;
    private CheckBox mCashAddressChkBx;

    String infoMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initViews();
    }

    private void resetCheckBoxes(){
        mBankCardChkBx.setChecked(false);
        mMobilePhoneChkBx.setChecked(false);
        mCashAddressChkBx.setChecked(false);
    }

    private void initViews() {
        mInputMoney = findViewById(R.id.inputMoney);
        mInputInfo = findViewById(R.id.inputInfo);
        mBtnOk = findViewById(R.id.btnOK);
        mBankCardChkBx = findViewById(R.id.bankCardChkBx);
        mMobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        mCashAddressChkBx = findViewById(R.id.cashAddressChkBx);

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switch (compoundButton.getId()) {
                        case R.id.bankCardChkBx:
                            resetCheckBoxes();
                            mBankCardChkBx.setChecked(true);
                            mInputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                            break;
                        case R.id.mobilePhoneChkBx:
                            resetCheckBoxes();
                            mMobilePhoneChkBx.setChecked(true);
                            mInputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                            break;
                        case R.id.cashAddressChkBx:
                            resetCheckBoxes();
                            mInputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                            mCashAddressChkBx.setChecked(true);
                            break;
                        default:
                    }
                }
            }
        };

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String depositMoney = mInputMoney.getText().toString();
                String paymentInformation = mInputInfo.getText().toString();

                if (mBankCardChkBx.isChecked()) {
                    infoMethod = mBankCardChkBx.getText().toString();
                } else if (mMobilePhoneChkBx.isChecked()) {
                    infoMethod = mMobilePhoneChkBx.getText().toString();
                } else if (mCashAddressChkBx.isChecked()) {
                    infoMethod = mCashAddressChkBx.getText().toString();
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Payment = " + depositMoney+"; ")
                        .append("Payment information = "+paymentInformation+"; ")
                        .append("Payment method = "+infoMethod+"; ");

                Toast.makeText(PaymentActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }
        });

        mBankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mMobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mCashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);
    }
}
