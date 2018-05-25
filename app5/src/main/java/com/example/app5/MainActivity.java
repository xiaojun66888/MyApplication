package com.example.app5;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.webkit.WebView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    WebView mWebView;
    SearchView searchView;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String content = "";
        for (int i = 1; i < 200; i++) {
            content += "Stringjasfgjfdsjf;"+"<br/>";
        }
        String summary = "<html><body>" + content + "</body></html>";
        


        mWebView = (WebView) findViewById(R.id.webview);
        searchView = (SearchView) findViewById(R.id.et_searchview);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        mWebView.loadData(summary, "text/html", null);
        mWebView.getSettings().getJavaScriptEnabled();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {


                    if (!flag) {
                        mWebView.findAllAsync(query);

                        flag = true;
                        try {
                            Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
                            m.invoke(mWebView, true);
                        } catch (Throwable ignored) {
                        }
                    } else {
                        mWebView.findNext(true);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.isEmpty()) {
                    try {
                        Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
                        m.invoke(mWebView, false);
                    } catch (Throwable ignored) {
                    }
                }
                flag = false;
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                try {
                    Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
                    m.invoke(mWebView, false);
                } catch (Throwable ignored) {
                }
                return false;
            }
        });


    }


}
