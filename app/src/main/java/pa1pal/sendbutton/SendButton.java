package pa1pal.sendbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by pa1pal on 14/01/16.
 */
public class SendButton extends View {
    public int flag = 0;
    private int mbuttonColor, mbuttonSide, mBorderStrokeWidth, mPlaneStrokeWidth, mPlaneColor;
    private Paint background, foregraound, two;
    Point a, b, c, d, e;
    Path mPath, mPlanePath;
    Matrix mTranslatePlane;

    public SendButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SendButton,
                0, 0);
        try {
            mbuttonColor = a.getColor(R.styleable.SendButton_buttonColor, Color.BLUE);
            mbuttonSide = a.getDimensionPixelSize(R.styleable.SendButton_buttonSide, getDimensionInPixel(60));
            mBorderStrokeWidth = a.getDimensionPixelSize(R.styleable.SendButton_borderStrokeWidth, 5);
            mPlaneStrokeWidth = a.getInteger(R.styleable.SendButton_planeStrokeWidth, 5);
            mPlaneColor = a.getColor(R.styleable.SendButton_planeColor, Color.DKGRAY);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }


        init();

    }

    private void init() {
        background = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregraound = new Paint(Paint.ANTI_ALIAS_FLAG);
        background.setStyle(Paint.Style.STROKE);
        foregraound.setStrokeWidth(mPlaneStrokeWidth);
        background.setStrokeWidth(mBorderStrokeWidth);
        background.setColor(mbuttonColor);
        mPath = new Path();
        mPlanePath = new Path();
        a = new Point((mbuttonSide * 10) / 100, (mbuttonSide * 55) / 100);
        b = new Point((mbuttonSide * 80) / 100, (mbuttonSide * 20) / 100); // x : ( 80% of side, 20% of side)
        c = new Point((mbuttonSide * 45) / 100, (mbuttonSide * 90) / 100);
        d = new Point((mbuttonSide * 30) / 100, (mbuttonSide * 70) / 100);
        e = new Point(mbuttonSide / 2, mbuttonSide / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.setAlpha(255);
        mPath.addRoundRect(new RectF(0, 0, mbuttonSide, mbuttonSide), mbuttonSide / 3, mbuttonSide / 3, Path.Direction.CCW);
        canvas.drawPath(mPath, background);
        foregraound.setStyle(Paint.Style.FILL);
        foregraound.setColor(getResources().getColor(R.color.orange));
        setPath();
        canvas.drawPath(mPlanePath, foregraound);
        foregraound.setStyle(Paint.Style.STROKE);
        foregraound.setColor(Color.WHITE);
        canvas.drawPath(mPlanePath, foregraound);
        setPath();
    }

    public void setPath() {
        mPlanePath.moveTo(a.x, a.y); //Set the starting point to A
        mPlanePath.lineTo(a.x, a.y);
        mPlanePath.lineTo(b.x, b.y);
        mPlanePath.lineTo(c.x, c.y);
        mPlanePath.lineTo(d.x, d.y);
        mPlanePath.lineTo(e.x, e.y);
        mPlanePath.lineTo(d.x, d.y);
        mPlanePath.lineTo(a.x, a.y);
    }


    private int getDimensionInPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
