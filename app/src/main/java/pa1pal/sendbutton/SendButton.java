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
    private int mButtonColor, mButtonSide, mBorderStrokeWidth, mPlaneStrokeWidth, mPlaneColor;
    private Paint background, foregraound;
    Point a, b, c, d, e;
    Path mPath, mPlanePath;
    Matrix mTranslatePlane;

    public SendButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SendButton,
                0, 0);
        try {
            mButtonColor = a.getColor(R.styleable.SendButton_buttonColor, Color.WHITE);
            mButtonSide = a.getDimensionPixelSize(R.styleable.SendButton_buttonSide, getDimensionInPixel(200));
            mBorderStrokeWidth = a.getInteger(R.styleable.SendButton_borderStrokeWidth, 5);
            mPlaneStrokeWidth = a.getInteger(R.styleable.SendButton_planeStrokeWidth, 5);
            mPlaneColor = a.getColor(R.styleable.SendButton_planeColor, getResources().getColor(R.color.orange));
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
        background.setColor(mButtonColor);
        mPath = new Path();
        mPlanePath = new Path();
        /**
         * The coordinates position calculated by percentage of button side.
         */
        a = new Point((mButtonSide * 10) / 100, (mButtonSide * 55) / 100); // Point a : (10% of mButtonSide, 55% of mButtonSide)
        b = new Point((mButtonSide * 80) / 100, (mButtonSide * 20) / 100); // Point b : (80% of mButtonSide, 20% of mButtonSide)
        c = new Point((mButtonSide * 45) / 100, (mButtonSide * 90) / 100); // Point c : (45% of mButtonSide, 90% of mButtonSide)
        d = new Point((mButtonSide * 30) / 100, (mButtonSide * 70) / 100); // Point d : (30% of mButtonSide, 70% of mButtonSide)
        e = new Point(mButtonSide / 2, mButtonSide / 2); // Point e : (10% of mButtonSide, 55% of mButtonSide)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.setAlpha(255);
//	    canvas.drawARGB(100, 0, 0, 0);
        mPath.addRoundRect(new RectF(0, 0, mButtonSide, mButtonSide), mButtonSide / 3, mButtonSide / 3, Path.Direction.CCW);
        canvas.drawPath(mPath, background);
	    canvas.clipPath(mPath);
        foregraound.setStyle(Paint.Style.FILL); // for different color of Fill and Stroke, first painted in Fill style and then Stroke style with different color
        foregraound.setColor(getResources().getColor(R.color.orange));
        setPath();
        canvas.drawPath(mPlanePath, foregraound);
        foregraound.setStyle(Paint.Style.STROKE);
        foregraound.setColor(Color.WHITE);
        canvas.drawPath(mPlanePath, foregraound);

        translate();
	    setPath();
    }

    public void setPath() {
	    mPlanePath = new Path();

	    mPlanePath.moveTo(a.x, a.y); //Set the starting point to A
        mPlanePath.lineTo(a.x, a.y);
        mPlanePath.lineTo(b.x, b.y);
        mPlanePath.lineTo(c.x, c.y);
        mPlanePath.lineTo(d.x, d.y);
        mPlanePath.lineTo(e.x, e.y);
        mPlanePath.lineTo(d.x, d.y);
        mPlanePath.lineTo(a.x, a.y);
    }

    private void translate() {
        if (flag < 150) {
/*            mTranslatePlane = new Matrix();
            mTranslatePlane.setTranslate(5, -5);
            mPlanePath.transform(mTranslatePlane);*/

	        int change = (int)Math.ceil(Math.cos(((double)flag/300.0)*Math.PI)*4);

	        a.x+=change;
	        a.y-=change;
	        b.x+=change;
	        b.y-=change;
	        c.x+=change;
	        c.y-=change;
	        d.x+=change;
	        d.y-=change;
	        e.x+=change;
	        e.y-=change;

            invalidate();
            flag++;
        }
    }

    private int getDimensionInPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
