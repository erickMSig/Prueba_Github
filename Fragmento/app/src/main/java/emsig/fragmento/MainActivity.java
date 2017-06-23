package emsig.fragmento;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    private boolean isMultiPanel;//Atributo para obtener el tamaño del dispositivo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMultiPanel();

    }

    private void setMultiPanel(){
        //implementa los fragmentos para saber si esta o no visible
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detailsFragment)!=null);
    }

    @Override
    public void sendData(String text) {
        if (isMultiPanel){
            //ESTAMOS EN UNA TABLET
            DetailsFragment detailsFragment = (DetailsFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.detailsFragment);
            detailsFragment.renderText(text);
        }
        else {
            //ESTAMOS EN UN CELULAR
            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra("text",text);//Este texto lo saca de datafragment
            startActivity(intent);
        }

    }
}
