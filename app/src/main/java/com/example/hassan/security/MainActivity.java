package com.example.hassan.security;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText plan_text, Key, M_text, Length_User_Language, User_Language;
    String P_T, Language;
    int k, m, n, Length;
    StringBuilder sd, stringBuilder;
    Spinner Select_Language;
    LinearLayout Dynami_Linear;
    SwitchCompat S_Enc, S_Dec;


    private char small[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char capital[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private char capitalAndsmall[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char smallwithspace[] = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char capitalwithspace[] = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private char CapitalAndsmallwithSpace[] = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

     private char[] DaynamicLanguage;
    private String[] list = {"Small", "Capital", "Capital and Small", "Small with Space", "Cpital with Space", "Capital and Small with Space","Other Language"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plan_text = findViewById(R.id.plan_txt);
        Key = findViewById(R.id.key);
        M_text = findViewById(R.id.m);
        User_Language = findViewById(R.id.input_Language);
        Length_User_Language = findViewById(R.id.length_of_language);

        sd = new StringBuilder();
        stringBuilder = new StringBuilder();

        S_Enc = findViewById(R.id.S_Enc);
        S_Dec = findViewById(R.id.S_Dec);

        Select_Language = findViewById(R.id.select_language);
        Dynami_Linear = findViewById(R.id.Daynmic_Linyear);

        Spinner();


    }


    public void enter(View view) {


        if (TextUtils.isEmpty(Key.getText().toString())) {

            Toast.makeText(this, "enter value of key", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(M_text.getText().toString())) {
            Toast.makeText(this, "enter value of M", Toast.LENGTH_SHORT).show();


        } else if (TextUtils.isEmpty(plan_text.getText().toString())) {
            Toast.makeText(this, "enter Text", Toast.LENGTH_SHORT).show();


        } else {


            if (S_Enc.isChecked()) {
                if (S_Dec.isChecked()) {
                    Toast.makeText(this, "Turn off other method", Toast.LENGTH_SHORT).show();
                } else {
                    EncryptonDaynamic();
                }


            } else if (S_Dec.isChecked()) {

                if (S_Enc.isChecked()) {
                    Toast.makeText(this, "Turn off other method", Toast.LENGTH_SHORT).show();
                } else {
                    DecryptonDaynamic();
                }
            } else {

                Toast.makeText(this, "Please Choose Method...", Toast.LENGTH_SHORT).show();
            }


        }


    }


    private int gcdThing(int m, int n) {
        BigInteger b1 = BigInteger.valueOf(m);
        BigInteger b2 = BigInteger.valueOf(n);
        BigInteger gcd = b1.gcd(b2);

        return gcd.intValue();
    }


    private String MMI(String num, String n) {


        BigInteger bi1, bi2, bi3;

        bi1 = new BigInteger(num);
        bi2 = new BigInteger(n);

        bi3 = bi1.modInverse(bi2);

        return String.valueOf(bi3);
    }

    private void Dialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(sd.toString());
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }


    private void EncryptonDaynamic() {
        sd.setLength(0);
        stringBuilder.setLength(0);
        if (Select_Language.getSelectedItem().equals("Small")) {

            Public_Encryption(26, small);

        } else if (Select_Language.getSelectedItem().equals("Capital")) {

            Public_Encryption(26, capital);
        } else if (Select_Language.getSelectedItem().equals("Capital and Small")) {

            Public_Encryption(52, capitalAndsmall);
        } else if (Select_Language.getSelectedItem().equals("Small with Space")) {

            Public_Encryption(27, smallwithspace);
        } else if (Select_Language.getSelectedItem().equals("Cpital with Space")) {

            Public_Encryption(27, capitalwithspace);
        } else if (Select_Language.getSelectedItem().equals("Capital and Small with Space")) {

            Public_Encryption(53, CapitalAndsmallwithSpace);
        }
        else if (Select_Language.getSelectedItem().equals("Other Language")) {

            User_Language.setVisibility(View.VISIBLE);
            Length_User_Language.setVisibility(View.VISIBLE);

            Length = Integer.parseInt(Length_User_Language.getText().toString());
            Language = User_Language.getText().toString();

            if (Length == Language.length()) {
                Insert_Language(Language);
                Public_Encryption(Length, DaynamicLanguage);


            }
            else {
                Toast.makeText(this, "Your Language don't have the same Length ", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void DecryptonDaynamic() {

        sd.setLength(0);

        stringBuilder.setLength(0);
        if (Select_Language.getSelectedItem().equals("Small")) {

            Public_Decryption(26, small);

        } else if (Select_Language.getSelectedItem().equals("Capital")) {

            Public_Decryption(26, capital);
        } else if (Select_Language.getSelectedItem().equals("Capital and Small")) {

            Public_Decryption(52, capitalAndsmall);
        } else if (Select_Language.getSelectedItem().equals("Small with Space")) {

            Public_Decryption(27, smallwithspace);
        } else if (Select_Language.getSelectedItem().equals("Cpital with Space")) {

            Public_Decryption(27, capitalwithspace);
        } else if (Select_Language.getSelectedItem().equals("Capital and Small with Space")) {

            Public_Decryption(53, CapitalAndsmallwithSpace);
        }
        else if (Select_Language.getSelectedItem().equals("Other Language")) {

            User_Language.setVisibility(View.VISIBLE);
            Length_User_Language.setVisibility(View.VISIBLE);

            Length = Integer.parseInt(Length_User_Language.getText().toString());
            Language = User_Language.getText().toString();

            if (Length == Language.length()) {
                Insert_Language(Language);

                Public_Decryption(Length, DaynamicLanguage);


            }
            else {
                Toast.makeText(this, "Your Language don't have the same Length ", Toast.LENGTH_SHORT).show();
            }



        }
    }


    private void Spinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        Select_Language.setAdapter(adapter);
        Select_Language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 6) {
                    Dynami_Linear.setVisibility(View.VISIBLE);
                } else {

                    Dynami_Linear.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void Insert_Language(String Lang) {
        DaynamicLanguage = Lang.toCharArray();

    }


    private void Public_Encryption(int n, char[] Lanhauge) {
        k = Integer.parseInt(Key.getText().toString());
        m = Integer.parseInt(M_text.getText().toString());
        if (gcdThing(m, n) == 1) {
            String f = "";

            P_T = plan_text.getText().toString();
            for (int i = 0; i < P_T.length(); i++) {
                for (int index = 0; index < n; index++) {
                    if (P_T.charAt(i) == Lanhauge[index]) {
                        int x = (m * index) + k;
                        int cipher = x % n;
                        sd.append(Lanhauge[cipher]);

                        f = "";
                        break;
                    } else {

                        f = String.valueOf(P_T.charAt(i));
                    }


                }
                stringBuilder.append(f);


            }


            if (P_T.length() != stringBuilder.length()) {

                Toast.makeText(this, "" + stringBuilder + " not contain in " + Select_Language.getSelectedItem(), Toast.LENGTH_SHORT).show();


            }


            Dialog();
        } else {
            Toast.makeText(this, "GCD  not equal 1", Toast.LENGTH_SHORT).show();
        }

    }


    private void Public_Decryption(int n, char[] Langauge) {
        k = Integer.parseInt(Key.getText().toString());
        m = Integer.parseInt(M_text.getText().toString());
        String f = "";
        stringBuilder.setLength(0);

        if (gcdThing(m, n) == 1) {


            P_T = plan_text.getText().toString();
            int MMI_Value = Integer.parseInt(MMI(M_text.getText().toString(), String.valueOf(n)));


            for (int i = 0; i < P_T.length(); i++) {
                for (int index = 0; index < n; index++) {

                    if (P_T.charAt(i) == Langauge[index]) {
                        int x = MMI_Value * (index - k);
                        for (int counter = 1; counter > 0; counter++) {
                            if (x < 0) {
                                x += n;

                            } else {
                                f = "";
                                break;
                            }
                        }
                        int Plane_Text = x % n;
                        sd.append(Langauge[Plane_Text]);

                        break;

                    } else {

                        f = String.valueOf(P_T.charAt(i));
                    }

                }


                stringBuilder.append(f);


            }
            if (P_T.length() != stringBuilder.length()) {

                Toast.makeText(this, "" + stringBuilder + " not contain in " + Select_Language.getSelectedItem(), Toast.LENGTH_SHORT).show();


            }


            Dialog();
        } else {
            Toast.makeText(this, "GCD  not equal 1", Toast.LENGTH_SHORT).show();


        }

    }


}
