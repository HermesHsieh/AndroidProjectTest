package tw.android.test.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hermes on 2017/7/7.
 */
public class CircleView extends View {

    private final static String TAG = CircleView.class.getSimpleName();

    private Context mContext;
    private Paint mPaint;
    private int mCutSize = 2;
    private int mRadiusSize = 9;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        setDefaultPaint();
    }

    private void setDefaultPaint() {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }

    public void setRadiusSize(int size) {
        if (size > 0 && size <= 10) {
            mRadiusSize = size;
            invalidate();
        }
    }

    public void setStrokeWidth(int width) {
        mPaint.setStrokeWidth(width);
        invalidate();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    public void setCutSize(int size) {
        if (size > 0) {
            if (size > 360) {
                Toast.makeText(mContext, "Cut size need <= 360", Toast.LENGTH_LONG).show();
                return;
            }
            mCutSize = size;
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "widthMeasureSpec : " + widthMeasureSpec);
        Log.d(TAG, "heightMeasureSpec : " + heightMeasureSpec);
        setMeasuredDimension(1080, 1080);
        return;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "Width : " + getWidth());
        Log.d(TAG, "Height : " + getHeight());

        int center_X = getWidth() / 2;
        int center_Y = center_X;
        int radius = center_X * mRadiusSize / 10;

        setDefaultPaint();
        canvas.drawCircle(center_X, center_Y, radius, mPaint);

        int index = 0;
        int rotate = 360 / mCutSize;
        int total = 0;
        while (360 - total >= rotate) {
            if (index % 10 == 0) {
                mPaint.setColor(Color.RED);
                mPaint.setStrokeWidth(5);
            } else if (index % 5 == 0) {
                mPaint.setColor(Color.BLUE);
                mPaint.setStrokeWidth(3);
            } else {
                mPaint.setColor(Color.BLACK);
                mPaint.setStrokeWidth(1);
            }
            canvas.drawLine(center_X, center_Y, center_X, center_X - radius, mPaint);
//            Log.d(TAG, "Rotate : " + 360 / mCutSize);
            canvas.rotate(360 / mCutSize, center_X, center_Y);
            total += rotate;
            index++;
        }
    }
}
