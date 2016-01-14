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
public class SendButton extends View
{
	public int flag = 0;
	private int mbuttonColor;
	private int mbuttonSide;
	private float mroundAngle;
	private Paint background, foregraound;
	Point a,b,c,d,e;
	Path mPath, mPlanePath;
	Matrix mTranslatePlane;

	public SendButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.SendButton,
				0, 0);
		try
		{
			mbuttonColor = a.getColor(R.styleable.SendButton_buttonColor, Color.BLUE);
			mbuttonSide = a.getDimensionPixelSize(R.styleable.SendButton_buttonSide, getDimensionInPixel(60)  );
			mroundAngle = a.getFloat(R.styleable.SendButton_borderRadius, getDimensionInPixel(10));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			a.recycle();
		}



		init();

	}

	private void init()
	{
	background = new Paint(Paint.ANTI_ALIAS_FLAG);
	foregraound = new Paint(Paint.ANTI_ALIAS_FLAG);
	background.setColor(Color.CYAN);
	background.setStyle(Paint.Style.STROKE);
	foregraound = new Paint(Paint.ANTI_ALIAS_FLAG);
	foregraound.setStyle(Paint.Style.STROKE);
		foregraound.setStrokeWidth(10);
		background.setStrokeWidth(10);

	foregraound.setColor(Color.RED);
		background.setColor(mbuttonColor);
		mPath =  new Path();
		mPlanePath = new Path();
	a = new Point(0,70);
	b = new Point(100, 0);
		c = new Point(30,100);
		d = new Point(18, 83);
		e = new Point(50, 50);

	}

	/**
	 * Implement this to do your drawing.
	 *
	 * @param canvas the canvas on which the background will be drawn
	 */
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);


		background.setAlpha(255);

		mPath.addRoundRect(new RectF(0, 0, mbuttonSide, mbuttonSide),mbuttonSide/3,mbuttonSide/3,
				Path
				.Direction.CCW);
//		canvas.clipPath(mPath, Region.Op.INTERSECT);
		canvas.drawPath(mPath, background);
//		background.setAntiAlias(true);
//		canvas.drawRect(0, 0, mbuttonSide,mbuttonSide,background);

		foregraound.setColor(Color.YELLOW);




		mPlanePath.setFillType(Path.FillType.EVEN_ODD);
		mPlanePath.lineTo(a.x, a.y);
		mPlanePath.lineTo(b.x, b.y);
		mPlanePath.lineTo(c.x, c.y);
		mPlanePath.lineTo(d.x, d.y);
		mPlanePath.lineTo(e.x, e.y);
		mPlanePath.lineTo(d.x, d.y);
		//mPlanePath.lineTo(a.x, a.y);




		canvas.drawPath(mPlanePath, foregraound);

		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		translate();

	}

	private void translate()
	{
		if (flag == 0)
		{
			mTranslatePlane = new Matrix();
			mTranslatePlane.setTranslate(0, 100);
			mPlanePath.transform(mTranslatePlane);
			invalidate();
			flag++;
		}
	}

	private int getDimensionInPixel(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}
}
