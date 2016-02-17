package intelectix.wallpaper;

import android.app.WallpaperManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;

import intelectix.wallpaper.Adapters.ImageAdapter;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenemos la refencia de la vista y seteamos el adapter
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
        //registramos el menu contextual
        registerForContextMenu(gridView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Titulo, adapter y opciones del menu contextual asociadas a un groupId
        menu.setHeaderTitle("Context Menu");
        AdapterView.AdapterContextMenuInfo cmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(1, cmi.position, 0, "Set as wallpaper");
        menu.add(2, cmi.position, 0, "View image");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Referenciamos el gridview del xml y obtenemos los datos del mismo con base a una posicion
        GridView gridView = (GridView) findViewById(R.id.gridView);
        Integer resourceId = (Integer) gridView.getItemAtPosition(item.getItemId());

        //Evaluamos la posicion seleccionada por el usuario en el menu contextual
        switch (item.getGroupId()) {
            //Seteamos el wallpaper
            case 1:
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setResource(resourceId);
                    Toast.makeText(getApplicationContext(), "Your wallpaper has been changed!!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
                break;
            //Accedemos al detalle de la imagen y en el intent pasamos el resourceId de la imagen
            case 2:
                Intent intent = new Intent(MainActivity.this, ImagePreviewActivity.class);
                intent.putExtra("id", resourceId);
                startActivity(intent);
                break;
        }
        return true;
    }
}
