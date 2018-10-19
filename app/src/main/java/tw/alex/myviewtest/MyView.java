package tw.alex.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

import static java.lang.Math.pow;

public class MyView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private GestureDetector gd;
    private Paint paint = new Paint();
    private float strokewidth;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(40);

        lines = new LinkedList<>();

        gd = new GestureDetector(context, new MyGestureListener());

        setBackgroundColor(Color.YELLOW);


//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("alex","onClick()");
//            }
//        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {


            Log.v("alex","onDown()");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float distance;
            distance = (float)(Math.pow(Math.pow(distanceX, 2) + Math.pow(distanceY,2), 0.5));

            strokewidth =  20*(1/distance);
            //paint.setStrokeWidth(40*(1/distance));




            return super.onScroll(e1, e2, distanceX, distanceY);
        }

//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//
//            Log.v("alex", "onFling()" + velocityX +":" + velocityY);
//            if(Math.abs(velocityX) > Math.abs(velocityY) +1000) {
//                if (velocityX > 2000) {
//                    Log.v("alex", "FlingRight");
//                } else if (velocityX < -2000) {
//                    Log.v("alex", "FlingLeft");
//                }
//            }else if(Math.abs(velocityY) > Math.abs(velocityX) +1000) {
//                if (velocityY > 2000) {
//                    Log.v("alex", "FlingDown");
//                } else if (velocityY < -2000) {
//                    Log.v("alex", "FlingUp");
//                }
//            }
//            return super.onFling(e1, e2, velocityX, velocityY);
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Log.v("alex","onDraw()");

        paint.setStrokeWidth(strokewidth);


        for(LinkedList<HashMap<String,Float>> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);

                canvas.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"), paint);
                paint.setStrokeWidth(p1.get("width"));
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();
        float width = strokewidth;

        HashMap<String,Float> point = new HashMap<>();
        point.put("x", ex); point.put("y", ey); point.put("width", width);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // Log.v("alex","dow:" + ex + "x" + ey);
                LinkedList<HashMap<String,Float>> line = new LinkedList<>();
                line.add(point);
                lines.add(line);

                break;
            case MotionEvent.ACTION_UP:
                // Log.v("alex", "up" + ex + "x" + ey);
                break;
            case MotionEvent.ACTION_MOVE:
                // Log.v("alex", "move: " + ex + "x" + ey);
                lines.getLast().add(point);
                break;
        }

        invalidate();

//        return true;//super.onTouchEvent(event);

        return gd.onTouchEvent(event);

    }

    public void clear(){
        lines.clear();

        invalidate();
    }

    public void undo(){
        lines.removeLast();

        invalidate();

    }
}
