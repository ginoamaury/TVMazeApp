package com.GinoAmaury.TVMazeApp.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoriteView;
import com.GinoAmaury.TVMazeApp.Model.DB.SQLConnection;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.FavoritePresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{
    @Override
    public Filter getFilter() {
        return null;
    }

    private  ArrayList<Show> shows;
    private ArrayList<Show> showsFiltered;
    private IOnShowClick onShowClick;
    private int typeView;

    private Context c;

    public static final int  TYPE_SHOW_PRINCIPAL = 0;
    public static final int  TYPE_SHOW_SECUNDARY = 1;


    public ShowsAdapter(ArrayList<Show> shows, IOnShowClick onShowClick, int typeView, Context c) {
        this.shows = shows;
        this.onShowClick = onShowClick;
        this.showsFiltered = shows;
        this.typeView = typeView;
        this.c = c;
    }

    public Show getShow (int pos){
        return showsFiltered.get(pos);
    }

    public void checkFav (int pos, Context c){

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        switch (typeView){
            case TYPE_SHOW_PRINCIPAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item_principal,parent,false);
                viewHolder = new ViewHolderShowPrincipal(view,onShowClick,c);
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
                SQLConnection helper = SQLConnection.getInstance(c);
                Boolean fav = helper.ifExist(showsFiltered.get(position));
                ((ViewHolderShowPrincipal) holder).setShowData(showsFiltered.get(position),fav);
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

    public class ViewHolderShowPrincipal extends RecyclerView.ViewHolder implements IFavoriteView{

        private IOnShowClick onShowClick;
        @BindView(R.id.showImage)
        ImageView showImage;
        @BindView(R.id.titleShow)
        TextView titleShow;
        @BindView(R.id.fab)
        FloatingActionButton fab;

        FavoritePresenter favoritePresenter;
        boolean favIsActive;

        Context c;

        public ViewHolderShowPrincipal(@NonNull View itemView, IOnShowClick onShowClick, Context c) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.onShowClick = onShowClick;
            this.c = c;
            onClickShowCard(itemView);
            onClickFav();
            favoritePresenter = new FavoritePresenter(this);
        }

        public void setShowData(Show show, boolean fav){
            String name = show.getName();
            titleShow.setText(name);
            favIsActive = fav;
            changeFabIcon(fab,fav);
            if(show.getImage()!= null){
                if(show.getImage().getMedium()!=null && show.getImage().getOriginal()!=null ){
                    String urlImage = show.getImage().getMedium();
                    Utility.showImage(itemView,showImage,urlImage);
                }else if(show.getImage().getMedium()==null && show.getImage().getOriginal()!=null ){
                    String urlImage = show.getImage().getOriginal();
                    Utility.showImage(itemView,showImage,urlImage);
                }else if(show.getImage().getMedium()!=null && show.getImage().getOriginal()==null ){
                    String urlImage = show.getImage().getMedium();
                    Utility.showImage(itemView,showImage,urlImage);
                }
            }else{
                Utility.showImage(itemView,showImage,"noimage");
            }

        }

        private void changeFabIcon (FloatingActionButton favIcon,boolean isActive){
            if(isActive){
                favIcon.setImageDrawable(c.getDrawable(R.drawable.ic_heart_full));
            }else{
                favIcon.setImageDrawable(c.getDrawable(R.drawable.ic_heart));
            }
        }

        private void onClickFav() {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(favIsActive){
                        favoritePresenter.deleteFav(getShow(getAdapterPosition()),c);
                    }else {
                        favoritePresenter.addFav(getShow(getAdapterPosition()),c);
                    }
                    onShowClick.onShowClick(getAdapterPosition(), Utility.CLICKADDFAV);
                }
            });
        }

        private void onClickShowCard(View v){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onShowClick.onShowClick(getAdapterPosition(), Utility.CLICKCARD);
                }
            });
        }

        @Override
        public void showResult(boolean result) {
            if(result){
                favIsActive = true;
                changeFabIcon(fab,true);
            }
        }

        @Override
        public void showResultDelete(boolean result) {
            if(result){
                favIsActive = false;
                changeFabIcon(fab,false);
            }
        }

        @Override
        public void showIfExist(boolean result) {

        }

        @Override
        public void showResultFavorites(ArrayList<Show> shows) {

        }
    }

    public class ViewHolderShowSecundary extends RecyclerView.ViewHolder{

        private IOnShowClick onShowClick;
        @BindView(R.id.showImage)
        ImageView showImage;
        @BindView(R.id.titleShow)
        TextView titleShow;
        @BindView(R.id.fab)
        FloatingActionButton fab;

        public ViewHolderShowSecundary(@NonNull View itemView, IOnShowClick onShowClick) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.onShowClick = onShowClick;
        }

        public void setShowData(Show show){
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
