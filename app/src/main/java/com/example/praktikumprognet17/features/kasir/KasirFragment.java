package com.example.praktikumprognet17.features.kasir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang.KeranjangActivity;

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
    public static final String URL = UtilsApi.BASE_URL_API;
    private List<ResultProduk> results = new ArrayList<>();
    private ProdukRecyclerViewAdapter viewAdapter;
    private SwipeRefreshLayout refreshLayout;
    BaseApiService mApiService;
    TextView tvQty;
    @BindView(R.id.recycler_produk)
    RecyclerView recycler_produk;

    View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_kasir_fragment, container, false);
        Log.e("bram", "onCreateView: "+ results.size());
        mView = view;
        loadDataProduk(mView);
        refreshLayout = view.findViewById(R.id.refreshItems);
        refreshLayout.setOnRefreshListener(() -> {
            loadDataProduk(mView);
            refreshLayout.setRefreshing(false);
        });
        return mView;
    }

    public void loadDataProduk(@NonNull View view) {
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new ProdukRecyclerViewAdapter(getActivity(), results, (v, args) -> onItemClicked(v, args));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_produk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(viewAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Log.e("AS", "klg"+api);
        Call<ValueProduk> call = api.viewProduk();
        call.enqueue(new Callback<ValueProduk>() {
            @Override
            public void onResponse(Call<ValueProduk> call, Response<ValueProduk> response) {
                int i;
                i = response.body().getTotal_keranjang();
                tvQty = view.findViewById(R.id.qty);
                tvQty.setText(Integer.toString(i));
//                tvQty.setText(Integer.toString(results.get(0).getHarga_total()));

                results = response.body().getHarga_keranjang();
                TextView tvTotalHarga = view.findViewById(R.id.total_harga);
                tvTotalHarga.setText("Rp "+results.get(0).getHarga_total());
                Log.e("ASSA", ""+results.get(0).getHarga_total());

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
    public void onItemClicked (View v, Bundle args) {
        DialogFragment qtyDialog = new QtyDialog(KasirFragment.this, mView);
        qtyDialog.setArguments(args);
        qtyDialog.show(getFragmentManager(),"qtyDialog");
        Log.e("asd",""+args);

//        new QtyDialog().show(getFragmentManager(),"QtyDialog");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnDetailKeranjang = view.findViewById(R.id.button_detail_item);
        btnDetailKeranjang.setOnClickListener(v->{
            startActivity(new Intent(getContext(), KeranjangActivity.class));
//            KeranjangFragmentBottom fragment = new KeranjangFragmentBottom();
//            fragment.show(getFragmentManager(), fragment.getTag());
//            DialogFragment dfr =  new KeranjangDialog();
//            dfr.show(getFragmentManager(), "dfr");
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataProduk(mView);
    }
}
