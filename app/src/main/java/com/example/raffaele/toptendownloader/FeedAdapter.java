package com.example.raffaele.toptendownloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.raffaele.toptendownloader.R.id.imageView;

/**
 * Created by raffaele on 01/10/16.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            Log.d(TAG, "getView: called with null convertView");
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            Log.d(TAG, "getView: provided a convertView");
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
//        TextView tvArtist = (TextView) convertView.findViewById(R.id.tvArtist);
//        TextView tvSummary = (TextView) convertView.findViewById(R.id.tvSummary);

        FeedEntry currentApp = applications.get(position);

        viewHolder.tvName.setText(currentApp.getName());
        viewHolder.tvArtist.setText(currentApp.getArtist());
        viewHolder.tvSummary.setText(currentApp.getSummary());

        /** Utilizzo della libreria Picasso per renderizzare l'immagine a partire dall'Url */
        Picasso.with(getContext()).load(currentApp.getImageUrl()).into(viewHolder.tvImage);

        return convertView;
    }


    /**
     * Per lavorare in modo efficiente, rendendo quindi lo scroll della ListView fluido o comunque
     * migliorare le prestazioni del dispositivo (ridurre il consumo eccessivo della RAM, ad esempio), è necessario
     * "riciclare" gli oggetti che rappresentano ciascuna riga della nostra lista in modo da evitare
     * di fare quello che in gergo si chiama "inflate" e di chiamare il metodo findViewById se
     * non quando strettamente necessario. Questa ottimizzazione è ottenuta grazie all'uso del Pattern
     * ViewHolder.
     */

    private class ViewHolder {
        final TextView tvName;
        final TextView tvArtist;
        final TextView tvSummary;
        final ImageView tvImage;

        ViewHolder(View v) {
            this.tvName = (TextView) v.findViewById(R.id.tvName);
            this.tvArtist = (TextView) v.findViewById(R.id.tvArtist);
            this.tvSummary = (TextView) v.findViewById(R.id.tvSummary);
            this.tvImage = (ImageView) v.findViewById(imageView);
        }
    }
}
