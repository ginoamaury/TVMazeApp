package com.GinoAmaury.TVMazeApp.View.Modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ShowsActor.ISearchActorShowsView;
import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.Person;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.ActorShowsPresenter;
import com.GinoAmaury.TVMazeApp.Presenter.SettingsPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;
import com.GinoAmaury.TVMazeApp.View.Activities.ShowActivity;
import com.GinoAmaury.TVMazeApp.View.Adapters.IOnShowClick;
import com.GinoAmaury.TVMazeApp.View.Adapters.ShowsAdapter;
import com.ethanhua.skeleton.Skeleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbar;

public class DialogActorFragment extends DialogFragment implements ISearchActorShowsView, IOnShowClick {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.imageActor)
    ImageView image;

    @BindView(R.id.recyclerShows)
    RecyclerView recyclerViewShows;

    private ShowsAdapter showsAdapter;
    private ActorShowsPresenter presenter;

    private static final String ACTORARG = "actor";
    private Person actor;

    public static DialogActorFragment newInstance(Person actor) {
        Bundle args = new Bundle();
        DialogActorFragment fragment = new DialogActorFragment();
        args.putSerializable(ACTORARG, actor);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_actor_fragment, null);
        ButterKnife.bind(this,view);
        builder.setView(view);
        if (getArguments() != null) {
            actor = (Person) getArguments().getSerializable(ACTORARG);
            setDataActor(view);
            presenter = new ActorShowsPresenter(this);
            showPreviewLoading();
            presenter.getShows(actor.getId());
        }
        return builder.create();
    }

    private void setDataActor(View v){
        if(actor != null){
            String nameA = actor.getName();
            name.setText(nameA);
            if(actor.getImage()!= null){
                if(actor.getImage().getMedium()!=null && actor.getImage().getOriginal()!=null ){
                    String urlImage = actor.getImage().getMedium();
                    Utility.showImage(v,image,urlImage);
                }else if(actor.getImage().getMedium()==null && actor.getImage().getOriginal()!=null ){
                    String urlImage = actor.getImage().getOriginal();
                    Utility.showImage(v,image,urlImage);
                }else if(actor.getImage().getMedium()!=null && actor.getImage().getOriginal()==null ){
                    String urlImage = actor.getImage().getMedium();
                    Utility.showImage(v,image,urlImage);
                }
            }else{
                Utility.showImage(v,image,"noimage");
            }
        }
    }

    private void showPreviewLoading (){
        recyclerViewShows.setLayoutManager(new GridLayoutManager(getContext(),2));
        Skeleton.bind(recyclerViewShows)
                .adapter(showsAdapter)
                .shimmer(true)      // whether show shimmer animation.                      default is true
                .count(12)          // the recycler view item count.                        default is 10
                .color(R.color.White)       // the shimmer color.                                   default is #a2878787
                .angle(20)          // the shimmer angle.                                   default is 20;
                .duration(900)     // the shimmer animation duration.                      default is 1000;
                .frozen(false)      // whether frozen recyclerView during skeleton showing  default is true;
                .load(R.layout.show_list_item_principal_preview)
                .show();
    }

    @Override
    public void showResult(ArrayList<Show> shows) {
        if(shows != null){
            Log.d("ENTRO",shows.toString());
            showsAdapter = new ShowsAdapter(shows,this,0,getContext());
            recyclerViewShows.setItemAnimator(new DefaultItemAnimator());
            recyclerViewShows.setAdapter(showsAdapter);
        }else{
            showSnackbar(getView(),getContext(),R.string.errShows);
        }
    }

    @Override
    public void onShowClick(int pos, String typeClick) {
        switch (typeClick){
            case Utility.CLICKCARD:
                Utility.goToNextActivityCleanStackShow(getActivity(), ShowActivity.class,false,null,showsAdapter.getShow(pos));
                break;
            case Utility.CLICKADDFAV:

                break;
        }
    }
}
