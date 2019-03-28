package mau.train.com.fruitsstore.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mau.train.com.fruitsstore.R;
import mau.train.com.fruitsstore.models.Fruit;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    private List<Fruit> fruits;
    private int layout;
    private OnFruitCardClick onFruitCardClickListener;
    private Activity context;

    public FruitAdapter(List<Fruit> fruits, int layout, OnFruitCardClick onFruitCardClickListener, Activity context) {
        this.fruits = fruits;
        this.layout = layout;
        this.onFruitCardClickListener = onFruitCardClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(layout,viewGroup,false);
        FruitViewHolder fruitViewHolder = new FruitViewHolder(view);
        return fruitViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder fruitViewHolder, int i) {
        fruitViewHolder.bind(fruits.get(i),this.onFruitCardClickListener);
    }

    @Override
    public int getItemCount() {
        return this.fruits.size();
    }

    public class FruitViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public ImageView fruitImageView;
        public TextView fruitNameView;
        public TextView fruitDescriptionView;
        public TextView fruitPriceView;
        public TextView fruitQuantityView;
        public FloatingActionButton fruitFloatingButtonView;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImageView = itemView.findViewById(R.id.fruit_image);
            fruitNameView = itemView.findViewById(R.id.fruit_name);
            fruitDescriptionView = itemView.findViewById(R.id.fruit_description);
            fruitPriceView = itemView.findViewById(R.id.fruit_price);
            fruitQuantityView = itemView.findViewById(R.id.quantity);
            fruitFloatingButtonView = itemView.findViewById(R.id.fruit_add);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit, final OnFruitCardClick onFruitCardClickListener){
            Picasso.with(context).load(fruit.getImage()).fit().into(this.fruitImageView);
            fruitNameView.setText(fruit.getName());
            fruitDescriptionView.setText(fruit.getDescription());
            fruitPriceView.setText(fruit.getPrice());
            fruitQuantityView.setText(String.valueOf(fruit.getQuantity()));

            fruitFloatingButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFruitCardClickListener.onFruitCardClick(fruit,getAdapterPosition());
                }
            });

            if(fruit.getQuantity() >= 10){
                fruitQuantityView.setTextColor(ContextCompat.getColor(context,R.color.alert));
                fruitQuantityView.setTypeface(null, Typeface.BOLD);
            }else {
                fruitQuantityView.setTextColor(ContextCompat.getColor(context,R.color.primaryText));
                fruitQuantityView.setTypeface(null, Typeface.NORMAL);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.fruit_delete:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //metodo para crear el menu en cada fruit_item
            //obtenemos el nombre de la fruta seleccionada
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());
            //seteamos el nombre del header del menu y su icono
            menu.setHeaderTitle(fruitSelected.getName());
            menu.setHeaderIcon(fruitSelected.getImage());
            //creamos un menuinflater en nuestra activity
            MenuInflater menuInflater = context.getMenuInflater();
            //inflamos el menu con los items de nuestro menu
            menuInflater.inflate(R.menu.menu_fruit,menu);
            //para cada item seteamos el menuOnClickListener
            for(int i = 0; i < menu.size(); i++)
                menu.getItem(i).setOnMenuItemClickListener(this);
        }
    }

    public interface OnFruitCardClick{
        void onFruitCardClick(Fruit fruit,int position);
    }
}