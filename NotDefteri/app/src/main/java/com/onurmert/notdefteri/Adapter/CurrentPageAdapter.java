package com.onurmert.notdefteri.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onurmert.notdefteri.Models.DiaryDbModel;
import com.onurmert.notdefteri.R;
import com.onurmert.notdefteri.View.CurrentPage;
import com.onurmert.notdefteri.View.ShowNates;
import com.onurmert.notdefteri.View.UpdatePage;

import java.util.ArrayList;

public class CurrentPageAdapter extends RecyclerView.Adapter<CurrentPageAdapter.MyViewHolder> {

    private ArrayList<DiaryDbModel> _listt =  new ArrayList<DiaryDbModel>();

    private final Activity _activity;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView _title_text;

        ImageView _imageView;

        public MyViewHolder(View itemview){
            super(itemview);
            _title_text = (TextView) itemview.findViewById(R.id.text_recycler_current_page);
            _imageView = (ImageView) itemview.findViewById(R.id.update_imageView_recycler);
        }
    }

    public CurrentPageAdapter(CurrentPage activity, ArrayList<DiaryDbModel> liste){

        this._activity = activity;
        this._listt = liste;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.current_page_recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder._title_text.setText(_listt.get(position).get_titlee());

        holder._title_text.setOnClickListener(view -> titleTextClick(position));

        holder._imageView.setOnClickListener(view -> imageClick(holder.itemView.getContext(), position));
    }

    @Override
    public int getItemCount() {
        return _listt.size();
    }

    private void titleTextClick(final int position){
        Intent intent = new Intent(_activity, ShowNates.class);

        intent.putExtra("id", _listt.get(position).get_id());

        _activity.startActivity(intent);
    }

    private void imageClick(Context context, final int position){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage("Güncellensin mi?");
        alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(_activity, UpdatePage.class);

                intent.putExtra("id", _listt.get(position).get_id());

                _activity.startActivity(intent);
                _activity.finish();
            }
        });
        alertDialog.show();
    }
}