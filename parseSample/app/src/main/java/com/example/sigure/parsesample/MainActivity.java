package com.example.sigure.parsesample;

import android.support.annotation.IntegerRes;
import android.support.annotation.InterpolatorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> arrayList = new ArrayList<>();
//
//        for(int i = 0 ; i<12; ++i)
//        {
//            arrayList.add("51."  + (i +1));
//        }

        final ArrayList<String> bookDataList = new ArrayList<>();
        bookDataList.add("学会誌:情報学会,vol51-No1");
        bookDataList.add("学会誌:情報学会,vol51-No2");
        bookDataList.add("学会誌:情報学会,vol51-No3");
        bookDataList.add("学会誌:情報学会,vol51-No4");
        bookDataList.add("学会誌:情報学会,vol51-No5");
        bookDataList.add("学会誌:情報学会,vol51-No6");
        bookDataList.add("学会誌:情報学会,vol51-No7");
        bookDataList.add("学会誌:情報学会,vol51-No8");
        bookDataList.add("学会誌:情報学会,vol51-No9");
        bookDataList.add("学会誌:情報学会,vol51-No10");
        bookDataList.add("学会誌:情報学会,vol51-No11");
        bookDataList.add("学会誌:情報学会,vol51-No12");
        bookDataList.add("学会誌:情報学会,vol52-No12");
        bookDataList.add("学会誌:情報学会,vol53-No12");
        bookDataList.add("学会誌:情報学会,vol52-No3");

        //region タイトルを抜き出すための処理

        ArrayList<String> adapterSetArray = new ArrayList<>();

        for(int i =0;i < bookDataList.size();++i)
        {
            int titleEnd = bookDataList.get(i).indexOf("-");
            String addBookData = bookDataList.get(i).substring(0,titleEnd);
            if(!adapterSetArray.contains(addBookData))
            {
                adapterSetArray.add(addBookData);
            }
        }

        //endregion

        //region ListViewの設定・表示

        final ListView listView = new ListView(this);
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,adapterSetArray);
        listView.setAdapter(adapter);
        setContentView(listView);

        //endregion

        //region いらない処理
//        String vol;
//        ArrayList<String>stringToFloatList = new ArrayList<>();
//
//        for(int i =0 ; i<bookDataList.size();++i)
//        {
//            int volStart = bookDataList.get(i).indexOf("vol") + 3;
//            int volEnd = bookDataList.get(i).indexOf("-");
//            vol = bookDataList.get(i).substring(volStart,volEnd);
//            int noStart = bookDataList.get(i).indexOf("No")+2;
//            String no = bookDataList.get(i).substring(noStart);
//
//            String toFloatData;
//            if(no.length() == 1)
//                   toFloatData = vol + ".0" + no;
//            else
//                toFloatData = vol + "." + no;
//            stringToFloatList.add(toFloatData);
//            Log.d("stringToFloatList",stringToFloatList.get(i));
//        }
//
//        float ft[] = new float[stringToFloatList.size()];
//        for(int i = 0;i<stringToFloatList.size();++i) {
//            ft[i] = Float.parseFloat(stringToFloatList.get(i));
//        }
//
//        for(int i =0 ;i<ft.length-1;++i)
//        {
//            for(int n = i+1 ; n < ft.length;++n)
//            {
//                if(ft[i] <= ft[n])
//                {
//                    float tmp = ft[i];
//                    ft[i] = ft[n];
//                    ft[n] = tmp;
//                }
//            }
//        }
//
//        for(int i=0;i<ft.length;++i)
//            Log.d("ft[" + i + "]",ft[i] + "");
//
//        for(int i = 0;i<ft.length;++i)
//        {
//            String tmp = ft[i] + "";
//            int volEnd  = tmp.indexOf(".");
//            String returnVol = tmp.substring(0,volEnd);
//            String returnNo = tmp.substring(volEnd+1);
//            if(returnNo.contains("0")) {
//                returnNo = returnNo.substring(1);
//            }else if(returnNo.length() == 1)
//                returnNo = returnNo + 0;
//            String userShowData = "vol:" + returnVol + "第" + returnNo + "号";
//            Log.d("show",userShowData);
//        }
////
////        listView.setAdapter(adapter);
////        setContentView(listView);
//
        //endregion

        //region アイテムがクリックされたときの処理

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String tmp = (String) listView.getItemAtPosition(position);//クリックされたタイトルを保存
                ArrayAdapter<String> noAdapter = new ArrayAdapter<>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item);//巻数表示のためのアダプター

                //region 巻数をソートして表示する処理
                if(listView.getAdapter() == adapter) {
                    ArrayList<String> noArray = new ArrayList<>();
                    for (int i = 0; i < bookDataList.size(); ++i) {
                        if (bookDataList.get(i).contains(tmp)) {
                            String no = bookDataList.get(i).substring(tmp.length() + 3);
                            noArray.add(no);
                        }
                    }

                    int intNumbers[] = new int[noArray.size()];

                    for(int i=0;i<noArray.size();++i) {
                        intNumbers[i] = Integer.parseInt(noArray.get(i));
                    }

                    for(int i=0;i<intNumbers.length -1;++i)
                    {
                        for(int n=i;n<intNumbers.length;++n)
                        {
                            if(intNumbers[i]>=intNumbers[n]) {
                                int iTmp = intNumbers[i];
                                intNumbers[i] = intNumbers[n];
                                intNumbers[n] = iTmp;
                            }
                        }
                    }

                    for(int i=0 ; i<intNumbers.length;++i)
                        noAdapter.add("NO."+ intNumbers[i]);

                    noAdapter.add("戻る");//リストを戻すためのボタン的なサムシング
                    listView.setAdapter(noAdapter);//リストビューにセットする
                }//endregion
                else {
                    if (tmp.equals("戻る")) {
                        listView.setAdapter(adapter);//元のリストに戻す処理
                    }
                }
                Toast.makeText(MainActivity.this, tmp + "", Toast.LENGTH_SHORT).show();//自分の確認用，いらない
            }
        });

        //endregion

    }
}
