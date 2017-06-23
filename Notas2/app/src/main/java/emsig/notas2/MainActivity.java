package emsig.notas2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText nomb_arch;
    EditText cont_arch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomb_arch = (EditText)findViewById(R.id.nomb_arch);
        cont_arch = (EditText)findViewById(R.id.cont_arch);
    }

    public void guardar(View v){
        String nombre_arch = nomb_arch.getText().toString();
        String contenido = cont_arch.getText().toString();

        try{
            FileOutputStream flujo = openFileOutput(nombre_arch, MODE_PRIVATE);
            flujo.write(contenido.getBytes());
            flujo.flush();//Limpiar flujo
            flujo.close();//cerrar flujo
            Toast.makeText(this, "Los datos fueron guardados correctamente", Toast.LENGTH_SHORT).show();

        }
        catch (IOException ioe){
            Toast.makeText(this, "Ocurrió un error al guardar", Toast.LENGTH_SHORT).show();

        }
    }

    public void abrir(View v){
        String nombre_arch = nomb_arch.getText().toString();
        String datos = "";
        int c;

        try{
            FileInputStream flujo = openFileInput(nombre_arch);
            //0 es el inicio del archivo, por ello se usa -1 para identificar que no hay nada
            while ((c = flujo.read()) != -1)
                datos = datos + (char)c;
            flujo.close();
            cont_arch.setText(datos);
            Toast.makeText(this, "Se leyó correctamente el archivo", Toast.LENGTH_SHORT).show();
        }
        catch (IOException ioe){
            Toast.makeText(this, "Ocurrió un error al abrir. Probablemente no exista el archivo", Toast.LENGTH_SHORT).show();

        }

    }

    public void eliminar(View v){
        String nombre_arch = nomb_arch.getText().toString();
        File directorio = getFilesDir();//Consigue la ruta donde esta almacenada el archivo (hasta el directorio donde esta)
        File file = new File(directorio, nombre_arch);//Ruta absoluta hasta el archivo (dentro del ultimo directorio)
        if (file.delete()){
            nomb_arch.setText("");
            cont_arch.setText("");
            Toast.makeText(this, "Se elimino correctamente el archivo", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Ocurrio un error al eliminar", Toast.LENGTH_SHORT).show();
    }

}
