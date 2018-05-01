package kr.or.dgit.it.datapersistenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setTitle(getIntent().getStringExtra("title"));

        webView = findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/test.html");
        webView.addJavascriptInterface(new JavascriptTest(), "android");
        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new MyWebChrome());
    }

    public void mLineChartClick(View view) {
        webView.loadUrl("javascript:lineChart()");
    }

    public void mDaumClick(View view) {
        webView.loadUrl("http://www.daum.net");
    }

    public void mBarChartClick(View view) {
        webView.loadUrl("javascript:barChart()");
    }

    class JavascriptTest {
        @JavascriptInterface
        public String getChartData() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            for (int i = 0; i < 14; i++) {
                buffer.append("[" + i + "," + Math.sin(i) + "]");

                Log.d("kkang", i + "," + Math.sin(i));
                if (i < 13) {
                    buffer.append(",");
                }
                buffer.append("]");
            }
            return buffer.toString();
        }
    }

        private class MyWebClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
              Toast.makeText(WebActivity.this,url,Toast.LENGTH_SHORT).show();
                return super.shouldOverrideUrlLoading(view,url);
            }
        }

        private class MyWebChrome extends WebChromeClient {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(WebActivity.this,url,Toast.LENGTH_SHORT).show();
               result.confirm();
               return true;
            }
        }
    }

