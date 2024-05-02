package com.app.ecommerceapp.nested_rv;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;

public class NestedRecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nested_recycler_view);

        List<House> houseList = new ArrayList<>();

        List<Member> starkMembers = new ArrayList<>();
        starkMembers.add(new Member("Jon Snow", "jon"));
        starkMembers.add(new Member("Sansa Stark", "sansa"));
        starkMembers.add(new Member("Eddard \"Ned\" Stark", "ned"));
        starkMembers.add(new Member("Arya Stark", "arya"));
        starkMembers.add(new Member("Bran Stark", "bran"));
        houseList.add(new House("stark", "House Stark of Winterfell", starkMembers));

        List<Member> lannisterMembers = new ArrayList<>();
        lannisterMembers.add(new Member("Jaime Lannister", "jaime"));
        lannisterMembers.add(new Member("Tyrion Lannister", "tyrion"));
        lannisterMembers.add(new Member("Cersei Lannister", "cersei"));
        lannisterMembers.add(new Member("Tywin Lannister", "tywin"));
        houseList.add(new House("lannister", "House Lannister of Casterly Rock", lannisterMembers));


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        HouseAdapter adapter = new HouseAdapter(houseList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}