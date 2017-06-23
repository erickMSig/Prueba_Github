package emsig.notas.models;

import java.util.Date;

import emsig.notas.app.MyApplication;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by luis on 21/06/17.
 */

public class Note extends RealmObject {
    @PrimaryKey
    private int id;
    @Required //para que no sea un dato vacio, no nula
    private String description;
    @Required //para que no sea un dato vacio, no nula
    private Date date; /*String = "Create table Note (
        DESCRIPTION VARCHAR(25) NOT NULL
    )"*/

    //Siempre hay que crear un constructor vacio
    public Note(){}

    public Note(String description){
        this.id = MyApplication.NoteID.getAndIncrement();
        this.description = description;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }
}
