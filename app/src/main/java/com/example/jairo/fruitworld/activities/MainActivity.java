package com.example.jairo.fruitworld.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jairo.fruitworld.R;
import com.example.jairo.fruitworld.adapters.FruitsAdapter;
import com.example.jairo.fruitworld.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fruit> fruits;
    private GridView gridView;
    private ListView listView;

    private FruitsAdapter fruitsGridAdapter;
    private  FruitsAdapter fruitsListAdapter;

    private int counter = 0;

    private MenuItem listItemView, gridItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        listView = (ListView) findViewById(R.id.listView);

        fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit("Sandía 1","Aguascalientes",R.mipmap.ic_watermelon));
        fruits.add(new Fruit("Sandía 2","Aguascalientes",R.mipmap.ic_watermelon));
        fruits.add(new Fruit("Sandía 3","Aguascalientes",R.mipmap.ic_watermelon));

        fruitsGridAdapter = new FruitsAdapter(this, R.layout.grid_item,fruits);
        gridView.setAdapter(fruitsGridAdapter);

        fruitsListAdapter = new FruitsAdapter(this, R.layout.list_item,fruits);
        listView.setAdapter(fruitsListAdapter);

        registerForContextMenu(gridView);
        registerForContextMenu(listView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "La fruta: "+fruits.get(position).getName()+" es de: "+fruits.get(position).getOrigin(), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "La fruta: "+fruits.get(position).getName()+" es de: "+fruits.get(position).getOrigin(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayMessage(String fruitName, String origin){

    }

    // inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        this.listItemView = menu.findItem(R.id.display_list);
        this.gridItemView = menu.findItem(R.id.display_grid);
        return true;
    }

    // Manejo de eventos click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_item:
                this.addFruit();
                this.fruitsGridAdapter.notifyDataSetChanged();
                this.fruitsListAdapter.notifyDataSetChanged();
                return true;
            case R.id.display_list:
                this.listItemView.setVisible(false);
                this.gridItemView.setVisible(true);
                this.listView.setVisibility(View.VISIBLE);
                this.gridView.setVisibility(View.INVISIBLE);
                return true;
            case R.id.display_grid:
                this.gridItemView.setVisible(false);
                this.listItemView.setVisible(true);
                this.gridView.setVisibility(View.VISIBLE);
                this.listView.setVisibility(View.INVISIBLE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                // Borramos el nombre
                this.fruits.remove(info.position);
                // Notificamos al adaptador del cambio producido
                this.fruitsGridAdapter.notifyDataSetChanged();
                this.fruitsListAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    private void addFruit(){
        fruits.add(new Fruit("Nueva Sandía "+(++this.counter),"Aguascalientes",R.mipmap.ic_watermelon));
    }

}
