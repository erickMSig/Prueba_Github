package emsig.fragmento;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static emsig.fragmento.R.id.text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    private EditText textData;
    private Button btnSend;
    private DataListener callback;


    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            //Accedemos a la accion generada en dataFragment y le notificamos a mainActivity
            callback = (DataListener)context;//Contexto de la actividad que se esta implementando
        }
        catch (Exception e){
            throw new ClassCastException(context.toString() +
                    "Se debe implementar la interfaz DataListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data,container, false);
        textData = (EditText)view.findViewById(R.id.editData); //SE PONE PRIMERO EL VIEW QUE ACABAMOS DE CREAR POR QUE ES EL QUE TIENE ACCESO A LA CLASE PARA OBTENER EL ID
        btnSend = (Button)view.findViewById(R.id.sendData);
        btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String textToSend = textData.getText().toString();
                callback.sendData(textToSend);
            }
        });

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_data, container, false); esta linea sirve para poder renderizar el layout
    }

    public interface DataListener{
        void sendData(String text);
        //void sendConfigurations();
    }

}
