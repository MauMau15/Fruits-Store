package mau.train.com.fruitsstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mau.train.com.fruitsstore.adapters.FruitAdapter;
import mau.train.com.fruitsstore.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;
    private RecyclerView fruitsMenuRecyclerView;
    private RecyclerView.Adapter fruitsMenuRecyclerViewAdapter;
    private RecyclerView.LayoutManager fruitsMenuRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = new ArrayList<Fruit>(){{
            add(new Fruit("Manzana",R.drawable.manzana,"Una muy rica manzana","165",0));
            add(new Fruit("Sandía",R.drawable.sandia,"Una muy rica sandía","170",0));
            add(new Fruit("Guayaba",R.drawable.guayaba,"Una muy rica guayaba","145",0));
            add(new Fruit("Fresa",R.drawable.fresa,"Una muy rica fresa","120",0));
        }};

        fruitsMenuRecyclerView = findViewById(R.id.fruit_menu);

        fruitsMenuRecyclerViewLayoutManager = new GridLayoutManager(MainActivity.this,1);

        fruitsMenuRecyclerViewAdapter = new FruitAdapter(this.fruits, R.layout.product_card_fruit, new FruitAdapter.OnFruitCardClick() {
            @Override
            public void onFruitCardClick(Fruit fruit, int position) {
                if(fruit.getQuantity() >= 10)
                    Toast.makeText(MainActivity.this,"You are not allowed to choose more than 10 " + fruit.getName() + "s",Toast.LENGTH_SHORT).show();
                else {
                    fruit.addFruit(1);
                    fruitsMenuRecyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        },MainActivity.this);

        fruitsMenuRecyclerView.setLayoutManager(fruitsMenuRecyclerViewLayoutManager);

        fruitsMenuRecyclerView.setAdapter(fruitsMenuRecyclerViewAdapter);

    }
}