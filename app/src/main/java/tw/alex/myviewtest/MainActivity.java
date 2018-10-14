package tw.alex.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyView myview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myview = findViewById(R.id.myView);
    }


    public void clear(View view) {

        myview.clear();
    }

    public void undo(View view) {

        myview.undo();
    }
}
