package emsig.picassodos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String[] perritos = {
            "https://s-media-cache-ak0.pinimg.com/736x/6f/7e/41/6f7e41ba3a4bed8ab76836e86e9b1f25--pomeranians-chihuahuas.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/97/22/98/9722988011eb072ba0ac2b98221a726b.jpg",
            "https://i.ytimg.com/vi/HeiUiFWpjqQ/hqdefault.jpg",
            "http://4.bp.blogspot.com/_HSv-Kv8IIpc/SSI-N15QD7I/AAAAAAAAADA/IJBJsZYN-YI/s320/perrogordo.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/6f/7e/41/6f7e41ba3a4bed8ab76836e86e9b1f25--pomeranians-chihuahuas.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/97/22/98/9722988011eb072ba0ac2b98221a726b.jpg",
            "https://i.ytimg.com/vi/HeiUiFWpjqQ/hqdefault.jpg",
            "http://4.bp.blogspot.com/_HSv-Kv8IIpc/SSI-N15QD7I/AAAAAAAAADA/IJBJsZYN-YI/s320/perrogordo.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/6f/7e/41/6f7e41ba3a4bed8ab76836e86e9b1f25--pomeranians-chihuahuas.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/97/22/98/9722988011eb072ba0ac2b98221a726b.jpg",
            "https://i.ytimg.com/vi/HeiUiFWpjqQ/hqdefault.jpg",
            "http://4.bp.blogspot.com/_HSv-Kv8IIpc/SSI-N15QD7I/AAAAAAAAADA/IJBJsZYN-YI/s320/perrogordo.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Se esta convirtiendo en un objeto que se puede manejar mediante codigo java

        gridView = (GridView)findViewById(R.id.lista);
        gridView.setAdapter(new AdaptadorImagenes(MainActivity.this, perritos));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picasso.with(MainActivity.this).load(perritos[position])
                        .rotate(180)
                        .fit()
                        .into((ImageView) view);//fit es para ajustar un elemento al tamaño del contenedor
            }

        });

    }
}
