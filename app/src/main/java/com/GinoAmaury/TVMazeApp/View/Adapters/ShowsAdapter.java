package com.GinoAmaury.TVMazeApp.View.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    @Override
    public Filter getFilter() {
        return null;
    }

    private  ArrayList<Search> shows;
    private ArrayList<Search> showsFiltered;
    private IOnShowClick onShowClick;
    private int typeView;

    public static final int  TYPE_SHOW_PRINCIPAL = 0;
    public static final int  TYPE_SHOW_SECUNDARY = 1;


    public ShowsAdapter(ArrayList<Search> shows, IOnShowClick onShowClick, int typeView) {
        this.shows = shows;
        this.onShowClick = onShowClick;
        this.showsFiltered = shows;
        this.typeView = typeView;
    }

    public Search getShow (int pos){
        return showsFiltered.get(pos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        switch (typeView){
            case TYPE_SHOW_PRINCIPAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item_principal,parent,false);
                viewHolder = new ViewHolderShowPrincipal(view,onShowClick);
                break;
            case TYPE_SHOW_SECUNDARY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item_principal,parent,false);
                viewHolder =  new ViewHolderShowSecundary(view,onShowClick);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item_principal,parent,false);
                viewHolder =  new ViewHolderShowNoElements(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (typeView){
            case TYPE_SHOW_PRINCIPAL:
                ((ViewHolderShowPrincipal) holder).setShowData(showsFiltered.get(position));
                break;
            case TYPE_SHOW_SECUNDARY:
                ((ViewHolderShowSecundary) holder).setShowData(showsFiltered.get(position));
                break;

            default:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return showsFiltered.size();
    }

    public class ViewHolderShowPrincipal extends RecyclerView.ViewHolder{

        private IOnShowClick onShowClick;
        @BindView(R.id.showImage)
        ImageView showImage;
        @BindView(R.id.titleShow)
        TextView titleShow;

        public ViewHolderShowPrincipal(@NonNull View itemView, IOnShowClick onShowClick) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.onShowClick = onShowClick;
            onClickShowCard(itemView);
        }

        public void setShowData(Search show){
            String name = show.getName();
            titleShow.setText(name);

            String urlImage = show.getImage().getOriginal();
            Log.d("ERROR IMAGE","URL: "+urlImage);

            Utility.showImage(itemView,showImage,urlImage);

        }

        private void onClickShowCard(View v){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onShowClick.onShowClick(getAdapterPosition(), Utility.CLICKCARD);
                }
            });
        }
    }

    public class ViewHolderShowSecundary extends RecyclerView.ViewHolder{

        private IOnShowClick onShowClick;
        @BindView(R.id.showImage)
        ImageView showImage;
        @BindView(R.id.titleShow)
        TextView titleShow;

        public ViewHolderShowSecundary(@NonNull View itemView, IOnShowClick onShowClick) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.onShowClick = onShowClick;
        }

        public void setShowData(Search show){
            titleShow.setText(show.getName());

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new ObjectKey("GLIDEIMAGES"));

            Glide.with(itemView).load(show.getImage())
                    .thumbnail(0.25f)
                    .error(R.drawable.ic_no_photo)
                    .fallback(R.drawable.ic_no_photo)
                    .apply(requestOptions).into(showImage);

        }
    }

    public class ViewHolderShowNoElements extends RecyclerView.ViewHolder{

        public ViewHolderShowNoElements(@NonNull View itemView) {
            super(itemView);
        }
    }



}
