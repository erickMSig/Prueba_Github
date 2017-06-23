package emsig.notas.app;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import emsig.notas.models.Board;
import emsig.notas.models.Note;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by luis on 21/06/17.
 */

//ESTA ES LA PRIMER CLASE QUE SE VA A MANDAR A LLAMAR
public class MyApplication extends Application {

    public static AtomicInteger BoardID = new AtomicInteger();
    public static AtomicInteger NoteID = new AtomicInteger();

    @Override
    public void onCreate(){
        super.onCreate();
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        BoardID = getIDByTable(realm, Board.class);
        NoteID = getIDByTable(realm, Note.class);
        realm.close();
    }

    private  void setUpRealmConfig(){
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    //Este metodo regresa el id de cualquier entidad
    private <T extends RealmObject> AtomicInteger getIDByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();//AQUI SE HACE UNA CONSULTA A LA BASE DE DATOS -- SELECT * FROM que trae todo
        return (results.size()>0)? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
        //AtomicInteger genera numeros consecutivos
    }



}
