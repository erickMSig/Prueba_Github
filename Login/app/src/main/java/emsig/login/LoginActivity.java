package emsig.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by luis on 22/06/17.
 */

public class LoginActivity extends AppCompatActivity{
    //Shared preferences guarda preferencias de la aplicacion
    SharedPreferences preferences;
    EditText editTextEmail, editTextPass;
    Switch switchRecordar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("datos_login", Context.MODE_PRIVATE);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPass = (EditText)findViewById(R.id.editTextPass);
        switchRecordar = (Switch)findViewById(R.id.switchRecordar);

        generaLasCredencialesSiExisten();

    }

    private boolean login(String email, String password){
        //patterns sirve para verificar patrones como los que tiene un email (cadena @ cadena . dominio)
        if(TextUtils.isEmpty(email)||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email no valido", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!(password.length() > 3)){
            Toast.makeText(this, "La contraseña no es valida", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    public void iniciarSesion(View v){
        String email = editTextEmail.getText().toString();
        String password = editTextPass.getText().toString();

        if(login(email, password)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            guardarPreferencias(email,password);
        }

    }

    private void guardarPreferencias(String email, String password){
        //Para modificar un elemento sharedPrefences es forzoso
        // usar un editor. Se utiliza para dar los elementos de una app,
        // como maudio al iniciar app, etc...
        if (switchRecordar.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.commit();
            editor.apply();

        }
    }

    private void generaLasCredencialesSiExisten(){
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");
        editTextEmail.setText(email);
        editTextPass.setText(password);
    }

}
