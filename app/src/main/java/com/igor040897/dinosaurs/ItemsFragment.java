package com.igor040897.dinosaurs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.igor040897.dinosaurs.API.DinoDate.Dino;
import com.igor040897.dinosaurs.API.DinoDate.DinoImage;
import com.igor040897.dinosaurs.API.DinoDate.Dino_;
import com.igor040897.dinosaurs.API.LSApi;
import com.igor040897.dinosaurs.API.Result.DinosResult;
import com.igor040897.dinosaurs.activity.AddItemActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.igor040897.dinosaurs.activity.AddItemActivity.RC_ADD_ITEM;

/**
 * Created by fanre on 6/27/2017.
 */

public class ItemsFragment extends Fragment {
    public static final int LODER_ITEMS = 0;
    public static final int LODER_ADD = 1;

    private DinosaursAdapter adapter = new DinosaursAdapter();

    private LSApi api;
    private Button add;
    private SwipeRefreshLayout refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView items = view.findViewById(R.id.items);
        items.setAdapter(adapter);

        refresh = view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
            }
        });

        add = view.findViewById(R.id.ok);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddItemActivity.class);
                startActivityForResult(intent, RC_ADD_ITEM);
            }
        });
        api = ((LSApp) getActivity().getApplication()).api();

        loadItems();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (adapter.getItemCount() == 0) {
//            loadItems();
//        }
//    }

//    private void addItem(final Dino item) {
//        api.dinos().enqueue(new Callback<DinosResult>() {
//            @Override
//            public void onResponse(Call<DinosResult> call, Response<DinosResult> response) {
//                if (response.isSuccessful()) {
//                    adapter.clear();
//                    ArrayList<Dino> dinos = response.body().getDinos();
//                    adapter.addAll(dinos);
////                    refresh.setRefreshing(false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DinosResult> call, Throwable t) {
//            }
//        });
//    }

    private void loadItems() {
        api.dinos().enqueue(new Callback<DinosResult>() {
            @Override
            public void onResponse(Call<DinosResult> call, Response<DinosResult> response) {
                if (response.isSuccessful()) {
                    adapter.clear();
                    List<Dino> dinos = response.body().getDinos();
                    adapter.addAll(dinos);
                    refresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<DinosResult> call, Throwable t) {
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ADD_ITEM && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddItemActivity.RESULT_NAME);
            String description = data.getStringExtra(AddItemActivity.RESULT_DESCRIPTION);
            String uri = data.getStringExtra(AddItemActivity.RESULT_URI_IMAGE);

            Dino item = (new Dino(new Dino_(name, "", "", new DinoImage(uri, ""), description)));
            adapter.add(item);
//            addItem(item);
            Toast.makeText(getContext(), item.getDino().getDino_title(), Toast.LENGTH_LONG).show();
        }
    }
}
