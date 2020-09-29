package com.GinoAmaury.TVMazeApp.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.ViewHolderEpisodes> implements Filterable {

    ArrayList<Episode> episodes;
    ArrayList<Episode> episodesFiltered;
    private IOnEpisodeClick onEpisodeClick;

    public EpisodesAdapter(ArrayList<Episode> episodes, IOnEpisodeClick onEpisodeClick) {
        this.episodes = episodes;
        this.episodesFiltered = episodes;
        this.onEpisodeClick = onEpisodeClick;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewHolderEpisodes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_episode_item,parent,false);
        return new ViewHolderEpisodes(view,onEpisodeClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEpisodes holder, int position) {
        holder.setEpisodeData(episodesFiltered.get(position));
    }


    @Override
    public int getItemCount() {
        return episodesFiltered.size();
    }

    public Episode getEpisode(int pos){
        return episodesFiltered.get(pos);
    }


    public class ViewHolderEpisodes extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.titleEpisode)
        TextView title;
        @BindView(R.id.season)
        TextView season;
        @BindView(R.id.episodeImage)
        ImageView image;

        private IOnEpisodeClick onEpisodeClick;

        public ViewHolderEpisodes(@NonNull View itemView, IOnEpisodeClick onEpisodeClick) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onEpisodeClick = onEpisodeClick;
            itemView.setOnClickListener(this);
        }

        private void setEpisodeData (Episode episode){
            String name = episode.getNumber()+ ". " + episode.getName();
            title.setText(name);
            String sea = episode.getSeason()+"";
            season.setText(sea);

            if(episode.getImage()!= null){
                if(episode.getImage().getMedium()!=null && episode.getImage().getOriginal()!=null ){
                    String urlImage = episode.getImage().getMedium();
                    Utility.showImage(itemView,image,urlImage);
                }else if(episode.getImage().getMedium()==null && episode.getImage().getOriginal()!=null ){
                    String urlImage = episode.getImage().getOriginal();
                    Utility.showImage(itemView,image,urlImage);
                }else if(episode.getImage().getMedium()!=null && episode.getImage().getOriginal()==null ){
                    String urlImage = episode.getImage().getMedium();
                    Utility.showImage(itemView,image,urlImage);
                }
            }else{
                Utility.showImage(itemView,image,"noimage");
            }

        }

        @Override
        public void onClick(View v) {
            onEpisodeClick.onEpisodeClick(getAdapterPosition());
        }
    }
}
