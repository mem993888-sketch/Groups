package com.whale.share;

import android.os.Bundle;
import android.widget.TextView;
import android.view.Gravity;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // تعيين ثيم متوافق برمجياً قبل استدعاء السوبر لمنع الانهيار
        setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar);
        
        super.onCreate(savedInstanceState);
        
        // إنشاء واجهة بسيطة برمجياً للتأكد من عمل التطبيق
        TextView textView = new TextView(this);
        textView.setText("Whale Share App\nIt Works Perfectly!");
        textView.setTextSize(24);
        textView.setGravity(Gravity.CENTER);
        
        setContentView(textView);
    }
}
