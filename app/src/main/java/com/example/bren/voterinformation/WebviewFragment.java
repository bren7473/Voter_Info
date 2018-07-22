package com.example.bren.voterinformation;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.PREFERENCE;
import static com.example.bren.voterinformation.Login.Site;


public class WebviewFragment extends Fragment {

    WebView mWebView;

    public WebviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        String site = sharedPreferences.getString(Site, "https://www.google.com");
        mWebView = new WebView(getActivity().getApplicationContext());
        mWebView = view.findViewById(R.id.webview);
        mWebView.loadUrl(site);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        // Inflate the layout for this fragment
        return view;
    }
}
