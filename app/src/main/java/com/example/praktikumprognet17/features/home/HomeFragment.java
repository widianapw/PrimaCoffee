package com.example.praktikumprognet17.features.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.dao.ReportDAO;
import com.example.praktikumprognet17.database.AppDatabase;
import com.example.praktikumprognet17.database.AppExecutors;
import com.example.praktikumprognet17.features.home.show_home.ReportTahunRecyclerViewAdapter;
import com.example.praktikumprognet17.features.home.show_home.ResultReport;
import com.example.praktikumprognet17.features.home.show_home.TerlarisRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AppDatabase mDb;
    private RecyclerView.LayoutManager layoutManager;
    //    private RecyclerView.Adapter adapter;
    private ArrayList<ReportDAO.Terlaris> daftarTerlaris;

    private List<ResultReport> results = new ArrayList<>();
    public static final String URL = UtilsApi.BASE_URL_API;
    BaseApiService mApiService;
    View mView;
    TerlarisRecyclerViewAdapter mAdapter;
    ReportTahunRecyclerViewAdapter mAdapterTahun;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);
        mView = view;
        mAdapter = new TerlarisRecyclerViewAdapter(getActivity());
//        mApiService = UtilsApi.getAPIService();
//        viewAdapter = new TerlarisRecyclerViewAdapter(getActivity(), results);
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_terlaris);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(viewAdapter);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        BaseApiService api = retrofit.create(BaseApiService.class);
//        Log.e("AS", "klg"+api);
//        Call<ValueReport> call = api.getTerlaris();
//        call.enqueue(new Callback<ValueReport>() {
//            @Override
//            public void onResponse(Call<ValueReport> call, Response<ValueReport> response) {
//                results = response.body().getResult();
//                viewAdapter = new TerlarisRecyclerViewAdapter(getActivity(), results);
//                recyclerView.setAdapter(viewAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ValueReport> call, Throwable t) {
//
//            }
//        });
//        daftarTerlaris = new ArrayList<>();
//        daftarTerlaris.addAll(Arrays.asList(mDb.reportDAO().readDataTerlaris()));


        //Menentukan bagaimana item pada RecyclerView akan tampil
        RecyclerView recyclerView = view.findViewById(R.id.recycler_terlaris);
        RecyclerView recyclerViewReport = view.findViewById(R.id.recycler_report_tahun);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewReport.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TerlarisRecyclerViewAdapter(getContext());
        mAdapterTahun = new ReportTahunRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        recyclerViewReport.setAdapter(mAdapterTahun);
        mDb = AppDatabase.getDatabase(getContext());
//        retrieveData();
        return mView;

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveData();

    }

    public void retrieveData() {
        AppExecutors.getInstance().diskIO().execute(() -> {


                final List<ReportDAO.Terlaris> data = mDb.reportDAO().readDataTerlaris();
                mAdapter.setTasks(data);
                final List<ReportDAO.ReportTahun> data1 = mDb.reportDAO().reportTahun();
                mAdapterTahun.setTasksReport(data1);

        });
    }
}
