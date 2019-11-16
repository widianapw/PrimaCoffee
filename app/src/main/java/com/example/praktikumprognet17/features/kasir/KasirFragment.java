package com.example.praktikumprognet17.features.kasir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.kasir.qty_dialog.QtyDialog;
import com.example.praktikumprognet17.features.kasir.show_produk.OnItemClickListener;
import com.example.praktikumprognet17.features.kasir.show_produk.ProdukRecyclerViewAdapter;
import com.example.praktikumprognet17.features.kasir.show_produk.ResultProduk;
import com.example.praktikumprognet17.features.kasir.show_produk.ValueProduk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KasirFragment extends Fragment implements OnItemClickListener {
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultProduk> results = new ArrayList<>();
    private ProdukRecyclerViewAdapter viewAdapter;
    private SwipeRefreshLayout refreshLayout;
    BaseApiService mApiService;

    @BindView(R.id.recycler_produk)
    RecyclerView recycler_produk;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_kasir_fragment, container, false);
        loadDataProduk(view);
        refreshLayout = view.findViewById(R.id.refreshItems);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataProduk(view);
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button btnEdit = view.findViewById(R.id.edit_item);
//        btnEdit.setOnClickListener(v->{
//            new QtyDialog().show(getFragmentManager(),"QtyDialog");
//        });

    }

    private void loadDataProduk(@NonNull View view) {
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new ProdukRecyclerViewAdapter(getActivity(), results, (v, args) -> onItemClicked(v, args));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_produk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.e("as", "" + viewAdapter);
        recyclerView.setAdapter(viewAdapter);
        Log.e("m", "anjing");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Call<ValueProduk> call = api.viewProduk();
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<ValueProduk>() {
            @Override
            public void onResponse(Call<ValueProduk> call, Response<ValueProduk> response) {
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
                String value = response.body().getValue();
                Log.e("ERROR", "asa" + results);
                results = response.body().getResult();
                viewAdapter = new ProdukRecyclerViewAdapter(getActivity(), results, (v, args) -> KasirFragment.this.onItemClicked(v, args));
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<ValueProduk> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClicked(View v, Bundle args) {
        DialogFragment qtyDialog = new QtyDialog();
        qtyDialog.setArguments(args);
        qtyDialog.show(getFragmentManager(),"qtyDialog");
        Log.e("asd",""+args);

//        new QtyDialog().show(getFragmentManager(),"QtyDialog");
    }
}
