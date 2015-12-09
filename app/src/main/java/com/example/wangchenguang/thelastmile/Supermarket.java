package com.example.wangchenguang.thelastmile;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class Supermarket extends ListActivity {

    private SimpleCursorAdapter adapter;
    private EditText etCommodity, etPrice;
    private Button btnAdd;
    private Db db;
    private SQLiteDatabase dbRead, dbWrite;
    private View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues cv = new ContentValues();
            cv.put("commodity", etCommodity.getText().toString());
            cv.put("price", etPrice.getText().toString());

            dbWrite.insert("user", null, cv);

            refreshListView();

        }
    };


    private int position;
    private AdapterView.OnItemClickListener listViewItemLongClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            new AlertDialog.Builder(Supermarket.this).setNegativeButton("No", null).setTitle("Warning").setMessage("Do you want to Delete it?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor c = adapter.getCursor();
                    c.moveToPosition(position);

                    int itemId = c.getInt(c.getColumnIndex("_id"));
                    dbWrite.delete("user", "_id=?", new String[]{itemId + ""});
                    refreshListView();

                }
            }).show();

        }
//
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//            new AlertDialog.Builder(Supermarket.this).setNegativeButton("No", null).setTitle("Warning").setMessage("Are You Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Cursor c = adapter.getCursor();
//                    c.moveToPosition(position);
//
//                    int itemId = c.getInt(c.getColumnIndex("_id"));
//                    dbWrite.delete("user","_id=?",new String[]{itemId+""});
//                    refreshListView();
//
//                }
//            }).show();
//
//
//            return true;
//        }
    };


//    private AdapterView.OnItemLongClickListener listViewItemLongClickListener = new AdapterView.OnItemLongClickListener() {
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//            new AlertDialog.Builder(Supermarket.this).setTitle("Warnning").setMessage("Delete it?").setNegativeButton("No", null).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Cursor c = adapter.getCursor();
//                    c.moveToPosition(position);
//
//                    int itemId = c.getInt(c.getColumnIndex("_id"));
//                    dbWrite.delete("user", "_id=?", new String[]{itemId + ""});
//                    refreshListView();
//                }
//            }).show();
//
//
//            return true;
//        }
//    };

//    private View.OnLongClickListener ListViewItemLongClickListener = new View.OnItemLongClickListener() {
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//            new AlertDialog.Builder(Supermarket.this).setTitle("Warning").setMessage("Are You Sure?").setNegativeButton("No",null).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Cursor c = adapter.getCursor();
//                    c.moveToPosition(position);
//
//                    int itemId = c.getInt(c.getColumnIndex("_id"));
//                    dbWrite.delete("user","_id=?",new String[]{itemId+""});
//                    refreshListView();
//                }
//            }).show();
//
//            return true;
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket);

        etCommodity = (EditText) findViewById(R.id.etCommodity);
        etPrice = (EditText) findViewById(R.id.etPrice);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(btnAddListener);

//        Db db = new Db(this);
//        SQLiteDatabase dbRead = db.getReadableDatabase();
//
        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
//        dbRead.close();
//        dbWrite.close();


        adapter = new SimpleCursorAdapter(this, R.layout.dblist, null, new String[]{"commodity", "price"}, new int[]{R.string.app_name, R.id.tvPrice});
        setListAdapter(adapter);

        refreshListView();

        getListView().setOnItemClickListener((AdapterView.OnItemClickListener) listViewItemLongClickListener);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    private void refreshListView() {
        Cursor c = dbRead.query("user", null, null, null, null, null, null);
        adapter.changeCursor(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_supermarket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


