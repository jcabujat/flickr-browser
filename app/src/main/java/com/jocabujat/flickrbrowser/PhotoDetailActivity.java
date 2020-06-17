package com.jocabujat.flickrbrowser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        activateToolbar(true);

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if (photo != null) {
            Resources resources = getResources();
            TextView photoTitle = findViewById(R.id.photo_title);
            photoTitle.setText(resources.getString(R.string.photo_title_text, photo.getTitle()));

            TextView photoTags = findViewById(R.id.photo_tags);
            photoTags.setText(resources.getString(R.string.photo_tags_text, photo.getTags()));

            TextView photoAuthor = findViewById(R.id.photo_author);
            photoAuthor.setText(resources.getString(R.string.photo_author_text, photo.getAuthor()));

            ImageView photoImage = findViewById(R.id.photo_image);
            Picasso.get().load(photo.getLink())
                    .error(R.drawable.baseline_image_black_48dp)
                    .placeholder(R.drawable.baseline_image_black_48dp)
                    .into(photoImage);

        }
    }
}