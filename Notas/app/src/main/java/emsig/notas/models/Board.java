package emsig.notas.models;

import java.util.Date;

import emsig.notas.app.MyApplication;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by luis on 21/06/17.
 */

public class Board extends RealmObject{

    @PrimaryKey
    private int id;
    @Required
    private  String title;
    @Required
    private Date date;
    private RealmList<Note> notes;

    public Board(){}

    public Board(String title){

        this.id = MyApplication.BoardID.getAndIncrement();
        this.title = title;
        this.date = new Date();
        this.notes = new RealmList<Note>();

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }
}
