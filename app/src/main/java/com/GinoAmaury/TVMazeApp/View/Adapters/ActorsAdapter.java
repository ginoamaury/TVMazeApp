package com.GinoAmaury.TVMazeApp.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.Person;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolderActors>{

    ArrayList<Person> actors;
    ArrayList<Person> actorsFiltered;
    private IOnActorClick onActorClick;

    public ActorsAdapter(ArrayList<Person> actors, IOnActorClick onActorClick) {
        this.actors = actors;
        this.actorsFiltered = actors;
        this.onActorClick = onActorClick;
    }

    @NonNull
    @Override
    public ViewHolderActors onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_item,parent,false);
        return new ViewHolderActors(view,onActorClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderActors holder, int position) {
        holder.setActorData(actorsFiltered.get(position));
    }

    public Person getActor(int pos){
        return actorsFiltered.get(pos);
    }


    @Override
    public int getItemCount() {
        return actorsFiltered.size();
    }

    public class ViewHolderActors extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.actorImage)
        ImageView image;
        @BindView(R.id.name)
        TextView name;

        private IOnActorClick onActorClick;

        public ViewHolderActors(@NonNull View itemView, IOnActorClick onActorClick) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onActorClick = onActorClick;
            itemView.setOnClickListener(this);
        }

        private void setActorData (Person actor){
            String nameA = actor.getName();
            name.setText(nameA);

            if(actor.getImage()!= null){
                if(actor.getImage().getMedium()!=null && actor.getImage().getOriginal()!=null ){
                    String urlImage = actor.getImage().getMedium();
                    Utility.showImage(itemView,image,urlImage);
                }else if(actor.getImage().getMedium()==null && actor.getImage().getOriginal()!=null ){
                    String urlImage = actor.getImage().getOriginal();
                    Utility.showImage(itemView,image,urlImage);
                }else if(actor.getImage().getMedium()!=null && actor.getImage().getOriginal()==null ){
                    String urlImage = actor.getImage().getMedium();
                    Utility.showImage(itemView,image,urlImage);
                }
            }else{
                Utility.showImage(itemView,image,"noimage");
            }

        }


        @Override
        public void onClick(View v) {
            onActorClick.onActorClick(getAdapterPosition());
        }
    }
}
