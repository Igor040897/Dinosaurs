package com.igor040897.dinosaurs.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
import com.igor040897.dinosaurs.API.DinoApi;
import com.igor040897.dinosaurs.DinoApp;
import com.igor040897.dinosaurs.R;
import com.igor040897.dinosaurs.mvp.model.DinosaursAdapter;
import com.igor040897.dinosaurs.mvp.model.db.DataModel;
import com.igor040897.dinosaurs.mvp.model.db.DatabaseHelper;
import com.igor040897.dinosaurs.ui.activity.AddItemActivity;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.igor040897.dinosaurs.ui.activity.AddItemActivity.RC_ADD_ITEM;

public class ItemsFragment extends Fragment {
    private DinosaursAdapter adapter = new DinosaursAdapter();

    @Inject
    DinoApi api;

    DatabaseHelper databaseHelper;

    private SwipeRefreshLayout refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DinoApp.component().inject(this);

        final Button add = view.findViewById(R.id.ok);
        final RecyclerView items = view.findViewById(R.id.items);
        refresh = view.findViewById(R.id.refresh);

        items.setAdapter(adapter);
        refresh.setOnRefreshListener(this::loadItems);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddItemActivity.class);
            startActivityForResult(intent, RC_ADD_ITEM);
        });

        databaseHelper = DinoApp.getDatabaseInstance();
        if (isNetworkConnecting()) {
            loadItems();
        } else {
            List<DataModel> dataModels = databaseHelper.getDataDao().getAllData();
            for (DataModel dataModel : dataModels) {
                adapter.add(dataModel.getDino());
            }//make in other tread
        }
    }

    boolean isNetworkConnecting() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null &&
                (cm.getActiveNetworkInfo() != null &&
                        cm.getActiveNetworkInfo().isConnectedOrConnecting());
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
        api.dinos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultDinos -> {
                    List<Dino> dinos = resultDinos.getDinos();
                    adapter.clear();
                    adapter.addAll(dinos);

                    DataModel model = new DataModel();
                    for (Dino dino : dinos) {
                        model.setDino(dino.getDino());
                        databaseHelper.getDataDao().insert(model);
                    }//make inside other tread

                    refresh.setRefreshing(false);
                }, Throwable::printStackTrace);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ADD_ITEM && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddItemActivity.RESULT_NAME);
            String description = data.getStringExtra(AddItemActivity.RESULT_DESCRIPTION);
            String uri = data.getStringExtra(AddItemActivity.RESULT_URI_IMAGE);

            Dino item = (new Dino(new Dino_(name, "", "", new DinoImage(uri, ""), description)));
            adapter.add(item.getDino());

            DataModel model = new DataModel();//move to function
            model.setDino(item.getDino());//move to function
            databaseHelper.getDataDao().insert(model);//move to function

//            addItem(item);
            Toast.makeText(getContext(), item.getDino().getDino_title(), Toast.LENGTH_LONG).show();
        }
    }
}
