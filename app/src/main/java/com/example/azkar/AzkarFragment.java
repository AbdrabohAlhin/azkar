package com.example.azkar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class AzkarFragment extends Fragment {

    public AzkarFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_azkar, container, false);

        CardView cardMorning = view.findViewById(R.id.cardMorning);
        CardView cardEvening = view.findViewById(R.id.cardEvening);
        CardView cardSleep = view.findViewById(R.id.cardSleep);
        CardView cardPrayer = view.findViewById(R.id.cardPrayer);

        cardMorning.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AzkarActivity.class);
            intent.putExtra("category_type", 0);
            startActivity(intent);
        });

        cardEvening.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AzkarActivity.class);
            intent.putExtra("category_type", 1);
            startActivity(intent);
        });

        cardSleep.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AzkarActivity.class);
            intent.putExtra("category_type", 2);
            startActivity(intent);
        });

        cardPrayer.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AzkarActivity.class);
            intent.putExtra("category_type", 3);
            startActivity(intent);
        });

        return view;
    }
}