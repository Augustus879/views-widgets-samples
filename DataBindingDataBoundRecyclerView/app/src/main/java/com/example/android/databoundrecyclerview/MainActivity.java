/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.databoundrecyclerview;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VerificationCodeDialogFragment.OnVerificationCodeEnteredListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    //protected String[] mDataset;
    ArrayList<String> mDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        City[] cities = {new City("Istanbul"),
                new City("Barcelona"),
                new City("London"),
                new City("San Francisco")};

        ArrayList<City> names = new ArrayList<City>();
        names.add(new City("Istanbul"));
        names.add(new City("Barcelona"));
        names.add(new City("London"));
        names.add(new City("San Francisco"));

        initDataset();
        recyclerView = findViewById(R.id.phone_list);
        adapter = new CustomAdapter(mDataset);
        recyclerView.setAdapter(adapter);

    }

    private void initDataset() {
        mDataset = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            mDataset.add("This is Phone #" + i);
        }
    }

    @Override
    public void onVerificationCodeEntered(String code) {
        // 处理输入的验证码，例如验证其有效性并进行后续操作
        // ...
        Log.d("tag", "test");
    }

    public void setInfo(String name) {
//        mainBinding.userName.setText("xyr");
//        mainBinding.phoneMac.setText("aa:bb:cc:dd");
//        mainBinding.bindTime.setText("24-04-01-13:00");
    }

}
