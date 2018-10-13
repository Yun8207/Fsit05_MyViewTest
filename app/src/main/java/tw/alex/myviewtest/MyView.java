package tw.alex.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.YELLOW);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("alex","onClick()");
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        canvas.drawLine(0,0, 200, 200, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.v("alex","dow:" + ex + "x" + ey);
                break;
            case MotionEvent.ACTION_UP:
                Log.v("alex", "up" + ex + "x" + ey);
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                Log.v("alex", "move: " + ex + "x" + ey);
                break;
        }

        return true;//super.onTouchEvent(event);
    }
}
