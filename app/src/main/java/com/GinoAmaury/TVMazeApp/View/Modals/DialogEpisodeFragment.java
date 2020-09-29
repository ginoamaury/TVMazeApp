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
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogEpisodeFragment extends DialogFragment {

    @BindView(R.id.titleEpisode)
    TextView titleEpisode;
    @BindView(R.id.season)
    TextView season;
    @BindView(R.id.sumaryContent)
    TextView sumaryContent;
    @BindView(R.id.imagerEpísode)
    ImageView image;

    private static final String EPISODEARG = "episode";
    private Episode episode;

    public static DialogEpisodeFragment newInstance(Episode episode) {
        Bundle args = new Bundle();
        DialogEpisodeFragment fragment = new DialogEpisodeFragment();
        args.putSerializable(EPISODEARG, episode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_episode_fragment, null);
        ButterKnife.bind(this,view);
        builder.setView(view);
        if (getArguments() != null) {
            episode = (Episode) getArguments().getSerializable(EPISODEARG);
            setDataEpisode(view);
        }
        return builder.create();
    }

    private void setDataEpisode (View v){
        if(episode != null){
            String name = episode.getNumber() + ". "+ episode.getName();
            titleEpisode.setText(name);
            String sea = getContext().getString(R.string.episodesSeason) + ": "+ episode.getSeason();
            season.setText(sea);

            if(episode.getImage()!= null){
                if(episode.getImage().getMedium()!=null && episode.getImage().getOriginal()!=null ){
                    String urlImage = episode.getImage().getMedium();
                    Utility.showImage(v,image,urlImage);
                }else if(episode.getImage().getMedium()==null && episode.getImage().getOriginal()!=null ){
                    String urlImage = episode.getImage().getOriginal();
                    Utility.showImage(v,image,urlImage);
                }else if(episode.getImage().getMedium()!=null && episode.getImage().getOriginal()==null ){
                    String urlImage = episode.getImage().getMedium();
                    Utility.showImage(v,image,urlImage);
                }
            }else{
                Utility.showImage(v,image,"noimage");
            }

            sumaryContent.setText(Html.fromHtml(episode.getSummary()));
        }
    }


}
