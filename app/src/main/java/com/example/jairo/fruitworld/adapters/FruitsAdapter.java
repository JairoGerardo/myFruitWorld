package com.example.jairo.fruitworld.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jairo.fruitworld.R;
import com.example.jairo.fruitworld.models.Fruit;

import java.util.List;

public class FruitsAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Fruit> fruits;

    public FruitsAdapter(Context context, int layout, List<Fruit> fruits){
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return this.fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Creamos el view para optimizar
        ViewHolder holder;

        if(convertView == null){
            // Inflamos la vista que nos ha llegado con nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);

            // Referenciamos el elemento a modificar
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.originTextView = (TextView) convertView.findViewById(R.id.originTextView);
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // Obtenemos los respectivos valores dependiendo de la posición
        String currentName = fruits.get(position).getName();
        String currentOrigin = fruits.get(position).getOrigin();
        int currentIcon = fruits.get(position).getIcon();

        // Le asignamos los valores al elemento referenciado
        holder.nameTextView.setText(currentName);
        holder.originTextView.setText(currentOrigin);
        holder.iconImageView.setImageResource(currentIcon);

        // Regresamos la vista inflada con los datos de la lista
        return convertView;
    }

    static class ViewHolder {
        private TextView nameTextView;
        private TextView originTextView;
        private ImageView iconImageView;
    }
}
