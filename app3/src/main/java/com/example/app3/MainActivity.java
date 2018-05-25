package com.example.app3;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app3.selectCity.CitySortModel;
import com.example.app3.selectCity.EditTextWithDel;
import com.example.app3.selectCity.PinyinComparator;
import com.example.app3.selectCity.PinyinUtils;
import com.example.app3.selectCity.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
     //主要排序listView
    private ListView sortListView;
    //右边A-Z
    private SideBar sideBar;
    //浮动字母提示
    private TextView dialog;
    //主要排序adapter
    private SortAdapter sortadapter;
    //带删除的EditText
    private EditTextWithDel mEtCityName;
    //数据集合
    private List<CitySortModel> SourceDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mEtCityName = (EditTextWithDel) findViewById(R.id.et_search);
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        initDatas();
        initEvents();
        setAdapter();
    }

    /**
     * 设置sideBar
     */
    private void initDatas() {
        sideBar.setTextView(dialog);
    }
    /**
     * 设置监听
     */
    private void initEvents() {
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = sortadapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position + 1);
                }
            }
        });

        //ListView的点击事件
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplication(), ((CitySortModel) sortadapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //根据输入框输入值的改变来过滤搜索
        mEtCityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        SourceDateList = filledData(getResources().getStringArray(R.array.provinces));
        Collections.sort(SourceDateList, new PinyinComparator());
        sortadapter = new SortAdapter(this, SourceDateList);
        sortListView.addHeaderView(initHeadView());
        sortListView.setAdapter(sortadapter);
    }

    /**
     * 添加头部
     * @return
     */
    private View initHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.headview, null);
        GridView mGvCity = (GridView) headView.findViewById(R.id.gv_hot_city);
        String[] datas = getResources().getStringArray(R.array.city);
        ArrayList<String> cityList = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            cityList.add(datas[i]);
        }
        /**
         * 热门城市
         */
        CityAdapter adapter = new CityAdapter(getApplicationContext(), R.layout.gridview_item, cityList);
        mGvCity.setAdapter(adapter);
        return headView;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<CitySortModel> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = SourceDateList;
        } else {
            mSortList.clear();
            for (CitySortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        sortadapter.updateListView(mSortList);
    }

    private List<CitySortModel> filledData(String[] data) {
        List<CitySortModel> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            CitySortModel sortModel = new CitySortModel();
            sortModel.setName(data[i]);
            String pinyin = PinyinUtils.getPingYin(data[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        sideBar.setIndexText(indexString);
        return mSortList;
    }
}
