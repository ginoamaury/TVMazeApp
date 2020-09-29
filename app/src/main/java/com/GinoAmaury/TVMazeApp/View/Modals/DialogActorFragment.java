package com.GinoAmaury.TVMazeApp.View.Modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.Person;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogActorFragment extends DialogFragment {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.imageActor)
    ImageView image;

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

}
