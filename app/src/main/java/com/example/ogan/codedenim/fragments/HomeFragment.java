package com.example.ogan.codedenim.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.ogan.codedenim.MainActivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.adapters.CategoriesRvAdaper;
import com.example.ogan.codedenim.gson.LearningPath;
import com.example.ogan.codedenim.sessionManagement.UserSessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView newsRv;
    CategoriesRvAdaper categoriesRvAdaper;
    LinearLayoutManager newsLayoutManager;
    LinearLayout linearLayout;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<LearningPath> categories;

    // User Session Manager Class
    UserSessionManager session;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_home, container, false);

        session = new UserSessionManager(getActivity().getApplicationContext());

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // get email
        String email = user.get(UserSessionManager.KEY_EMAIL);

        linearLayout = v.findViewById(R.id.linearView);
        progressBar = v.findViewById(R.id.loading_spinner);
        swipeRefreshLayout = v.findViewById(R.id.home_swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        newsRv = v.findViewById(R.id.rv_News);
        newsLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        newsRv.setLayoutManager(newsLayoutManager);
        newsRv.addItemDecoration(itemDecoration);


        //GET categories
        ServiceGenerator.apiMethods.getCategories(email).enqueue(new Callback<ArrayList<LearningPath>>() {
            @Override
            public void onResponse(Call<ArrayList<LearningPath>> call, Response<ArrayList<LearningPath>> response) {

                progressBar.setVisibility(View.GONE);

                System.err.println(response.message());
                System.err.println(response.body());

                if(response.isSuccessful()){
                    categories = response.body();
                    categoriesRvAdaper = new CategoriesRvAdaper(categories);
                    newsRv.setAdapter(categoriesRvAdaper);
                    linearLayout.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<LearningPath>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Log.e("categories_error", t.getMessage());
            }
        });

        return v;
    }

}
