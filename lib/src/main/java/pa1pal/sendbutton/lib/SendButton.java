package pa1pal.sendbutton.lib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Pawan Kumar Pal on 14/01/16.
 * Modified by Devesh Khandelwal on 18/01/16.
 */
public class SendButton extends View
{
	private String LOGTAG = getClass().getSimpleName();

	int flag = 0;
	Point a, b, c, d, e;
	Path mOutlinePath, mPlanePath;
	int mButtonColor, mButtonSide, mBorderStrokeWidth, mPlaneStrokeWidth, mPlaneColor;
	Paint mBackgroundPaint, mPlanePaint;
	ValueAnimator mPlaneAnimator;
	long mDuration;
	AnimationType mAnimationType;


	public SendButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.SendButton,
				0, 0);
		try
		{
			mButtonColor = a.getColor(R.styleable.SendButton_buttonColor, Color.WHITE);
			mButtonSide = a.getDimensionPixelSize(R.styleable.SendButton_buttonSide, 200);
			mBorderStrokeWidth = a.getInteger(R.styleable.SendButton_borderStrokeWidth, 5);
			mPlaneStrokeWidth = a.getInteger(R.styleable.SendButton_planeStrokeWidth, 5);
			mPlaneColor = a.getColor(R.styleable.SendButton_planeColor, getResources().getColor(R.color.orange));
			mAnimationType = AnimationType.values()[a.getInteger(R.styleable
					.SendButton_animationType, 0)];
			mDuration = a.getInteger(R.styleable.SendButton_duration, 3000);
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
		mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPlanePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBackgroundPaint.setStyle(Paint.Style.STROKE);
		mPlanePaint.setStrokeWidth(mPlaneStrokeWidth);
		mBackgroundPaint.setStrokeWidth(mBorderStrokeWidth);
		mBackgroundPaint.setColor(mButtonColor);
		mOutlinePath = new Path();
		mPlanePath = new Path();
		mPlaneAnimator = ValueAnimator.ofInt(0, 75);
		mPlaneAnimator.setDuration(mDuration);
		mPlaneAnimator.setRepeatMode(ValueAnimator.RESTART);
		mPlaneAnimator.setRepeatCount(ValueAnimator.INFINITE);

		switch (mAnimationType)
		{
			case LINEAR:
				mPlaneAnimator.setInterpolator(new LinearInterpolator());
				break;
			case ANTICIPATE:
				mPlaneAnimator.setInterpolator(new AnticipateInterpolator());
				break;
			case ANTICIPATE_OVERSHOOT:
				mPlaneAnimator.setInterpolator(new AnticipateOvershootInterpolator());
				break;
			case ACCELERATE:
				mPlaneAnimator.setInterpolator(new AccelerateInterpolator());
				break;
			case ACCELERATE_DECELERATE:
				mPlaneAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
				break;
			case BOUNCE:
				mPlaneAnimator.setInterpolator(new BounceInterpolator());
				break;
			case DECELERATE:
				mPlaneAnimator.setInterpolator(new DecelerateInterpolator());
				break;
			case FASTOUTLINEARIN:
				mPlaneAnimator.setInterpolator(new FastOutLinearInInterpolator());
				break;
			case FASTOUTSLOWIN:
				mPlaneAnimator.setInterpolator(new FastOutSlowInInterpolator());
				break;
			case LINEAROUTSLOWIN:
				mPlaneAnimator.setInterpolator(new LinearOutSlowInInterpolator());
				break;
			case OVERSHOOT:
				mPlaneAnimator.setInterpolator(new OvershootInterpolator());
				break;
		}

		mPlaneAnimator.start();

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
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		mBackgroundPaint.setAlpha(255);
		mOutlinePath.addRoundRect(new RectF(0, 0, mButtonSide, mButtonSide),
				mButtonSide / 3, mButtonSide / 3, Path.Direction.CCW);
		canvas.drawPath(mOutlinePath, mBackgroundPaint);
		canvas.clipPath(mOutlinePath);
		// for different color of Fill and Stroke,
		// first painted in Fill style and then Stroke style with different color
		mPlanePaint.setStyle(Paint.Style.FILL);
		mPlanePaint.setColor(mPlaneColor);
		mPlanePaint.setAlpha(255 - ((int) mPlaneAnimator.getAnimatedValue() * 25) / 10);
		translate();
		setPath();
		canvas.drawPath(mPlanePath, mPlanePaint);
		mPlanePaint.setStyle(Paint.Style.STROKE);
		mPlanePaint.setColor(Color.WHITE);
		mPlanePaint.setAlpha(255 - ((int) mPlaneAnimator.getAnimatedValue() * 25) / 10);
		canvas.drawPath(mPlanePath, mPlanePaint);
	}

	public void setPath()
	{
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

	private void translate()
	{
		a.set((mButtonSide * 10) / 100, (mButtonSide * 55) / 100); // Point a : (10% of mButtonSide,
		// 55% of mButtonSide)
		b.set((mButtonSide * 80) / 100, (mButtonSide * 20) / 100); // Point b : (80% of mButtonSide,
		// 20% of mButtonSide)
		c.set((mButtonSide * 45) / 100, (mButtonSide * 90) / 100); // Point c : (45% of mButtonSide,
		// 90% of mButtonSide)
		d.set((mButtonSide * 30) / 100, (mButtonSide * 70) / 100); // Point d : (30% of mButtonSide,
		// 70% of mButtonSide)
		e.set(mButtonSide / 2, mButtonSide / 2); // Point e : (10% of mButtonSide, 55% of
		// mButtonSide)


		int change = 3 * (int) mPlaneAnimator.getAnimatedValue();

		Log.i(LOGTAG, "Animated Value: " + change + ", Flag: " + flag++);

		a.x += change;
		a.y -= change;
		b.x += change;
		b.y -= change;
		c.x += change;
		c.y -= change;
		d.x += change;
		d.y -= change;
		e.x += change;
		e.y -= change;

		invalidate();

	}

	private enum AnimationType
	{
		LINEAR,
		ANTICIPATE,
		ANTICIPATE_OVERSHOOT,
		ACCELERATE,
		ACCELERATE_DECELERATE,
		BOUNCE,
		DECELERATE,
		FASTOUTLINEARIN,
		FASTOUTSLOWIN,
		LINEAROUTSLOWIN,
		OVERSHOOT
	}

}

