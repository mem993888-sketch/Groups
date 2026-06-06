package com.whale.share;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // إنشاء واجهة بسيطة برمجياً لعرض نص الترحيب
        TextView textView = new TextView(this);
        textView.setText("Whale Share App\nIt Works Perfectly!");
        textView.setTextSize(24);
        textView.setGravity(Gravity.CENTER);
        
        setContentView(textView);
    }
}
