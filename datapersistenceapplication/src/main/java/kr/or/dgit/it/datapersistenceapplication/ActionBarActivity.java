package kr.or.dgit.it.datapersistenceapplication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ActionBarActivity extends AppCompatActivity {


    private SearchView sView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.icon);

        String msg = "build version : "+ Build.VERSION.SDK_INT;

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

        ImageView imageView = findViewById(R.id.imageView);
        registerForContextMenu(imageView);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId()==android.R.id.home){
           Toast t = Toast.makeText(this,"HOME AS UP Click",Toast.LENGTH_SHORT);
           t.show();
           return  true;
       }
       if(item.getItemId()==0){
           Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
       }
       return super.onContextItemSelected(item);
    }

   @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder)menu;
            m.setOptionalIconsVisible(true);
        }
        MenuItem searchMenu = menu.findItem(R.id.menu_main_search);
       sView = (SearchView)searchMenu.getActionView();
       sView.setQueryHint("검색어를 입력하세요.");
         sView.setOnQueryTextListener(queryTexyListener);
        return true;
    }

    private SearchView.OnQueryTextListener queryTexyListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
            sView.setQuery("",false);
            sView.setIconified(true);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"서버전송");
        menu.add(0,1,0,"보관함에 보관");
        menu.add(0,2,0,"삭제");
    }
}
