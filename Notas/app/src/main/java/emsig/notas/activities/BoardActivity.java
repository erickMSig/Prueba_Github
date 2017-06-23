package emsig.notas.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import emsig.notas.R;
import emsig.notas.adapters.BoardAdapter;
import emsig.notas.models.Board;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/*RealmChangeListener esta a la espera de cualquier acceso a la base de datos
    */
public class BoardActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>>, AdapterView.OnItemClickListener{

    private Realm realm;

    private FloatingActionButton fab;
    private ListView listView;
    private BoardAdapter adapter;//castear para acceder a cada uno de los items de la lista

    private RealmResults<Board> boards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        // Db Realm
        realm = Realm.getDefaultInstance();//se levanta la BD
        boards = realm.where(Board.class).findAll();//select * from
        boards.addChangeListener(this);//que este a la espera de cualquier cambio en la lista de board

        adapter = new BoardAdapter(this, boards, R.layout.list_view_board_item);//se le asigna contexto, la entidad y la lista
        listView = (ListView) findViewById(R.id.listViewBoard);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);//cuando se precione el boton flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForCreatingBoard("Add New Board", "Type a name for your new board");
            }
        });

        registerForContextMenu(listView);
    }


    //** CRUD Actions **/
    //ACCIONES PARA CREAR LOS BOARDS (TABLEROS)
    private void createNewBoard(String boardName) {
        realm.beginTransaction();
        Board board = new Board(boardName);//CREA UNA NUEVA PIZZARRA (TABLERO)
        realm.copyToRealm(board);
        realm.commitTransaction();
    }

    private void editBoard(String newName, Board board) {
        realm.beginTransaction();
        board.setTitle(newName);
        //board.setCategory(category); Para darle una categoria al tablero
        realm.copyToRealmOrUpdate(board);
        realm.commitTransaction();
    }

    private void deleteBoard(Board board) {
        realm.beginTransaction();
        board.deleteFromRealm();
        realm.commitTransaction();
    }

    //Borra todos los recursos de la base de datos
    private void deleteAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    //** Dialogs **/
    //AQUI SE LANZA EL MENSAJE
    private void showAlertForCreatingBoard(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);//guarda el titulo
        if (message != null) builder.setMessage(message);//guarda el mensaje

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewBoard);//EDITA EL TITULO DEL TABLERO

        //LLAMA AL METODO AGREGAR
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();
                if (boardName.length() > 0)
                    createNewBoard(boardName);
                else
                    Toast.makeText(getApplicationContext(), "The name is required to create a new Board", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();//CREA EL DIALOGO
        dialog.show();//MUESTRA EL DIALOGO
    }

    private void showAlertForEditingBoard(String title, String message, final Board board) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewBoard);
        input.setText(board.getTitle());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();
                if (boardName.length() == 0)
                    Toast.makeText(getApplicationContext(), "The name is required to edit the current board", Toast.LENGTH_LONG).show();
                else if (boardName.equals(board.getTitle()))
                    Toast.makeText(getApplicationContext(), "The name is the same than it was before", Toast.LENGTH_LONG).show();
                else
                    editBoard(boardName, board);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /* Events */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_board_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete_all:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(boards.get(info.position).getTitle());
        getMenuInflater().inflate(R.menu.context_menu_board_activity, menu);
    }

    //CADA UNO DE LOS ITEMS DEL MENU CONTEXTUAL SE PONE AQUI
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_board:
                deleteBoard(boards.get(info.position));
                return true;
            case R.id.edit_board:
                showAlertForEditingBoard("Edit Board", "Change the name of the board", boards.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //PERMITE VER LOS CAMBIOS REALIZADOS 
    @Override
    public void onChange(RealmResults<Board> element) {
        adapter.notifyDataSetChanged();
    }

    //PARA CADA UNA DE LAS NOTAS
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(BoardActivity.this, NoteActivity.class);
        intent.putExtra("id", boards.get(position).getId());
        startActivity(intent);
    }
}
