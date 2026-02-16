package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Good> goodsList;
    private LayoutInflater inflater;
    private OnItemCheckListener listener;

    public GoodsAdapter(Context context, ArrayList<Good> goodsList, OnItemCheckListener listener) {
        this.context = context;
        this.goodsList = goodsList;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return goodsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_good, parent, false);

            holder = new ViewHolder();
            holder.tvId = convertView.findViewById(R.id.tvId);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.checkBox = convertView.findViewById(R.id.checkBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Good good = goodsList.get(position);

        holder.tvId.setText("ID: " + good.getId());
        holder.tvName.setText(good.getName());
        holder.tvPrice.setText("Price: $" + good.getPrice());

        // Важно! Убираем старый listener перед установкой нового
        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(good.isChecked());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            good.setChecked(isChecked);
            if (listener != null) {
                listener.onItemCheckChanged();
            }
        });

        return convertView;
    }

    // ViewHolder для оптимизации
    static class ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvPrice;
        CheckBox checkBox;
    }

    // Интерфейс для уведомления Activity
    public interface OnItemCheckListener {
        void onItemCheckChanged();
    }
}
