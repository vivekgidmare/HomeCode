package com.homecode;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by vivek on 17/05/16.
 */
public class Utility {

    public static void getDataItems(final Context context, final Handler.Callback callback) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ArrayList<DataItem> dataItems = new ArrayList<>();
                try {
                    InputStream inputStream = context.getResources().getAssets().open("navigation.json");
                    int size = inputStream.available();
                    byte[] bytes = new byte[size];
                    inputStream.read(bytes);
                    inputStream.close();
                    String json = new String(bytes);
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataItems.add(new DataItem(jsonObject.getString("title"), jsonObject.getString("description"), jsonObject.getString("class")));
                    }
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", dataItems);
                    message.setData(bundle);
                    callback.handleMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
