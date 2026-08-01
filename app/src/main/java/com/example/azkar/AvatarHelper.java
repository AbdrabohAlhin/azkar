package com.example.azkar;

import android.content.Context;
import android.graphics.Outline;
import android.net.Uri;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

public class AvatarHelper {

    public static void makeCircular(ImageView imageView) {
        imageView.setClipToOutline(true);
        imageView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
    }

    public static void loadUserAvatar(Context context, ImageView imageView) {
        makeCircular(imageView);
        String uriString = UserSession.getUserPhotoUri(context);

        if (uriString != null) {
            try {
                imageView.setImageURI(Uri.parse(uriString));
            } catch (Exception e) {
            }
        }
    }
}