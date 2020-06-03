package com.strawberry.delivery.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.strawberry.delivery.MainActivity;
import com.strawberry.delivery.Order;
import com.strawberry.delivery.Poster;
import com.strawberry.delivery.R;
import com.strawberry.delivery.restAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView=(RecyclerView)root.findViewById(R.id.rvRestaurant);
        ArrayList<Poster> data=new ArrayList<>();

        data.add(new Poster("McDonald","https://i.imgur.com/FtGifdh.jpg","15-30min"));
        data.add(new Poster("KEBUKE","https://i.imgur.com/kALZWlr.png","15-30min"));
        data.add(new Poster("再睡5分鐘","https://i.imgur.com/CyRXw7A.png","15-30min"));

        final restAdapter adapter=new restAdapter(requireContext(),data);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter.setOnItemClickListener(new restAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView nameview=(TextView)view.findViewById(R.id.tvItemName);
                Intent intent=new Intent(getActivity(),Order.class);
                intent.putExtra("restaurantID",nameview.getText());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        return root;
    }
}
