package com.example.lab2_18048941_lenhathuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button generate ;
    EditText password, passLen;
    CheckBox ckLower, ckUpper, ckNum, ckSymbol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2_d);

        generate = (Button) findViewById(R.id.buttonGenerate);
        password = (EditText) findViewById(R.id.password);
        passLen = (EditText) findViewById(R.id.passLen);
        ckLower = (CheckBox) findViewById(R.id.lowerCase);
        ckUpper = (CheckBox) findViewById(R.id.upperCase);
        ckNum = (CheckBox) findViewById(R.id.includeNum);
        ckSymbol = (CheckBox) findViewById(R.id.includeSymbol);

        password.setClickable(false);
        password.setFocusable(false);
        password.setFocusableInTouchMode(false);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len =0;
                try{
                    len = Integer.parseInt(passLen.getText().toString());
                }catch (Exception e){
                    e.getMessage();
                };
                if(len > 0){
                    password.setText(generatedPassword(len));
                }
            }
        });
    }

    public String generatedPassword(int len){
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String symbol = "!@#$%^&*()_-+=<>?/{}~|";
        Random ran = new Random();
        String allowedChar = "";
        StringBuilder result = new StringBuilder();
        int amountck = 0;

        if(ckUpper.isChecked() == true){
            allowedChar += upper;
            result.append(upper.charAt(ran.nextInt(upper.length()-1)));
            amountck++;
        }
        if(ckLower.isChecked() == true){
            allowedChar += lower;
            result.append(lower.charAt(ran.nextInt(lower.length()-1)));
            amountck++;
        }
        if(ckNum.isChecked() == true){
            allowedChar += num;
            result.append(num.charAt(ran.nextInt(num.length()-1)));
            amountck++;
        }
        if(ckSymbol.isChecked() == true){
            allowedChar += symbol;
            result.append(symbol.charAt(ran.nextInt(symbol.length()-1)));
            amountck++;
        }

        if(amountck == 0){
            return "";
        } else if(len < amountck){
            result.delete(len, amountck);
        } else{
            for(int i = result.length(); i < len; i++){
                result.append(allowedChar.charAt(ran.nextInt(allowedChar.length()-1)));
            }
        }




        return result.toString();
    }
}