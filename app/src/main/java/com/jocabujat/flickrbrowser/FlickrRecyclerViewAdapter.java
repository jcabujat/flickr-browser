package com.jocabujat.flickrbrowser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickrRecyclerViewAdapter(Context context, List<Photo> photoList) {
        mPhotoList = photoList;
        mContext = context;
    }

    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new iew requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (mPhotoList != null && mPhotoList.size() != 0) ? mPhotoList.size() : 1;
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder holder, int position) {

        if (mPhotoList == null || mPhotoList.size() == 0) {
            holder.thumbnail.setImageResource(R.drawable.baseline_image_black_48dp);
            holder.title.setText(R.string.empty_photo_title);
        } else {
            Photo photo = mPhotoList.get(position);
            Picasso.get().load(photo.getImage())
                    .error(R.drawable.baseline_image_black_48dp)
                    .placeholder(R.drawable.baseline_image_black_48dp)
                    .into(holder.thumbnail);

            holder.title.setText(photo.getTitle());
        }
    }

    void loadNewData(List<Photo> newPhotos) {
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return (mPhotoList != null && mPhotoList.size() != 0) ? mPhotoList.get(position) : null;
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;


        public FlickrImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = itemView.findViewById(R.id.thumbnail);
            this.title = itemView.findViewById(R.id.title);
        }
    }
}
