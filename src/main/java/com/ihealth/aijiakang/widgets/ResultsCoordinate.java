package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.BpUtils;
import com.ihealth.aijiakang.widgets.datasource.ResultsCoordinateData;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.MyApplication;
import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/9
 */
public class ResultsCoordinate extends BaseView {
    private final String TAG = "ResultsCoordinate";
    private int columsX = 0;
    private int rowsY = 0;
    private int yMin = 0;
    private int yMax = 0;
    private float pointSize = 0f;
    private float yWidth = 0f;
    private float marginTop = 0f;
    private float marginBottom = 0f;
    private float coorTvMargin = 0f;
    private float shadowHeight = 0f;
    private float[] xCoors;
    private float[] yCoors;
    private ResultsCoordinateData mData = null;

    //Paints
    private Paint coorLinePaint;
    private Paint coorTvPaint;
    private Paint linePaint;
    private Paint shadowPaint;

    public ResultsCoordinate(Context context) {
        super(context);
        initWidgets(context);
    }

    public ResultsCoordinate(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ResultsCoordinate);

        columsX = a.getInt(R.styleable.ResultsCoordinate_columsX, 0);
        rowsY = a.getInt(R.styleable.ResultsCoordinate_rowsY, 0);
        yMin = a.getInt(R.styleable.ResultsCoordinate_yMin, 0);
        yMax = a.getInt(R.styleable.ResultsCoordinate_yMax, 0);
        pointSize = a.getDimension(R.styleable.ResultsCoordinate_pointSize, 0f);

        a.recycle();

        initWidgets(context);
    }

    private void initWidgets(Context context) {
        yWidth = context.getResources().getDimension(R.dimen.host_results_coordinate_y_width);
        marginTop = context.getResources().getDimension(R.dimen.host_results_coordinate_topline_margintop);
        marginBottom = context.getResources().getDimension(R.dimen.host_results_coordinate_bottomline_marginbottom);
        coorTvMargin = context.getResources().getDimension(R.dimen.host_results_coordinate_ycoor_margin);
        shadowHeight = context.getResources().getDimension(R.dimen.host_results_coordinate_line_shadow);

        //坐标系线条
        coorLinePaint = new Paint();
        coorLinePaint.setStrokeWidth(context.getResources().getDimension(R.dimen.line_width));
        coorLinePaint.setColor(context.getResources().getColor(R.color.shadow_line_color));
        coorLinePaint.setStyle(Paint.Style.FILL);
        coorLinePaint.setAntiAlias(true);

        MyApplication appContext = (MyApplication) context.getApplicationContext();
        //坐标系文字
        coorTvPaint = new Paint();
        coorTvPaint.setColor(context.getResources().getColor(R.color.text_shadow_color));
        coorTvPaint.setAntiAlias(true);
        coorTvPaint.setTypeface(appContext.getFont(MyApplication.DEFAULT_HYQH));
        coorTvPaint.setStyle(Paint.Style.FILL);
        coorTvPaint.setTextAlign(Paint.Align.RIGHT);
        coorTvPaint.setTextSize(context.getResources().getDimension(R.dimen.textsize_9));
        //绘制曲线
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(context.getResources().getDimension(R.dimen.host_results_coordinate_linewidth));
        //绘制阴影
        shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAlpha(100);
    }

    /**
     * 判断是否可以去绘制
     * Author YanJiaqi
     * Created at 15/12/9 下午6:27
     */

    private boolean canDraw() {
        if (columsX == 0 || rowsY == 0 || yMax == 0 || pointSize == 0f || yMax < yMin) {
            return false;
        } else {
            if (mData == null || mData.getValues() == null || mData.getDates() == null)
                return false;
            if (mData.getValues().size() == 0 || mData.getDates().size() != columsX)
                return false;
            for (int i = 0; i < mData.getValues().size(); i++) {
                if (mData.getValues().get(i).size() != columsX)
                    return false;
            }
            return true;
        }
    }

    @Override
    protected void drawSub(Rect mRect, Canvas canvas, float ratioX, float ratioY) {
        if (!canDraw()) {
            return;
        }

        drawCoor(mRect, canvas, ratioX, ratioY);
        drawShadow(mRect, canvas, ratioX, ratioY);
        drawLines(mRect, canvas, ratioX, ratioY);
    }

    /**
     * 绘制阴影
     * Author YanJiaqi
     * Created at 15/12/9 下午11:43
     */

    private void drawShadow(Rect mRect, Canvas canvas, float ratioX, float ratioY) {
        float yInterval = (mRect.height() * ratioY - marginTop - marginBottom
                - getResources().getDimension(R.dimen.host_results_coordinate_linewidth)) / (yMax - yMin);
        float y0 = 0f,y1 = 0f,y2 = 0f,y3 = 0f,max = 0f,x1 = 0f;
        max = valueToY(mRect,ratioY,yInterval,yMin);
        for (int i = 0; i < mData.getValues().size(); i++) {
            Path shadowPath = new Path();
            int color = 0;
            for (int j = 1; j < xCoors.length - 1; j++) {
                color = mData.getValues().get(i).get(j).getColor();
                y0 = valueToY(mRect, ratioY, yInterval, mData.getValues().get(i).get(j - 1).getValue());
                y1 = valueToY(mRect, ratioY, yInterval, mData.getValues().get(i).get(j).getValue());
                y2 = y1+shadowHeight;
                if(y2>max){
                    y2 = max;
                }
                y3 = y0+shadowHeight;
                if(y3>max){
                    y3 = max;
                }
                shadowPath.reset();
                shadowPath.moveTo(xCoors[j], y0);
                shadowPath.lineTo(xCoors[j + 1], y1);
                shadowPath.lineTo(xCoors[j + 1], y2);
                shadowPath.lineTo(xCoors[j], y3);

                x1 = xCoors[j] + (y0 - y1) * 1f / (xCoors[j + 1] - xCoors[j]) * shadowHeight;
//                AJKLog.i(TAG,"x0 = " + xCoors[j] + ",y0 = " + y0 + ",x1 = " + x1 + ",y1 = " + y3);
                shadowPaint.setShader(new LinearGradient(xCoors[j], y0, x1, y3,
                        Color.argb(200, Color.red(color), Color.green(color), Color.blue(color)), 0x00ffffff, Shader.TileMode.MIRROR));

                canvas.drawPath(shadowPath, shadowPaint);
            }
        }
    }

    /**
     * 绘制曲线
     * Author YanJiaqi
     * Created at 15/12/9 下午9:23
     */

    private void drawLines(Rect mRect, Canvas canvas, float ratioX, float ratioY) {
        float yInterval = (mRect.height() * ratioY - marginTop - marginBottom
                - getResources().getDimension(R.dimen.host_results_coordinate_linewidth)) / (yMax - yMin);
        float y0 = 0f, y1 = 0f;
        for (int i = 0; i < mData.getValues().size(); i++) {
            for (int j = 1; j < xCoors.length - 1; j++) {
                linePaint.setColor(mData.getValues().get(i).get(j).getColor());
                linePaint.setStyle(Paint.Style.FILL);
                y0 = valueToY(mRect, ratioY, yInterval, mData.getValues().get(i).get(j - 1).getValue());
                y1 = valueToY(mRect, ratioY, yInterval, mData.getValues().get(i).get(j).getValue());
                canvas.drawLine(xCoors[j], y0, xCoors[j + 1], y1, linePaint);
                drawPoints(mRect, canvas, ratioX, ratioY, yInterval, xCoors[j],
                        y0,
                        mData.getValues().get(i).get(j - 1).isFill(),
                        mData.getValues().get(i).get(j - 1).getColor());
            }
            int j = xCoors.length - 1;
            drawPoints(mRect, canvas, ratioX, ratioY, yInterval, xCoors[j],
                    valueToY(mRect, ratioY, yInterval, mData.getValues().get(i).get(j - 1).getValue()),
                    mData.getValues().get(i).get(j - 1).isFill(),
                    mData.getValues().get(i).get(j - 1).getColor());
        }
    }

    /**
     * 画点
     * Author YanJiaqi
     * Created at 15/12/9 下午10:16
     */

    private void drawPoints(Rect mRect, Canvas canvas, float ratioX, float ratioY, float yInterval, float x, float y, boolean isFill, int color) {
        if (isFill) {
            linePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x, y, pointSize / 2, linePaint);
        } else {
            linePaint.setStyle(Paint.Style.FILL);
            linePaint.setColor(0xffffffff);
            canvas.drawCircle(x, y, pointSize / 2, linePaint);
            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setColor(color);
            canvas.drawCircle(x, y, pointSize / 2, linePaint);
        }
    }

    /**
     * 将value转化为Y坐标
     * Author YanJiaqi
     * Created at 15/12/9 下午9:33
     */

    private float valueToY(Rect mRect, float ratioY, float yInterval, int value) {
        if (value < yMin) {
            value = yMin;
        } else if (value > yMax) {
            value = yMax;
        }
        return mRect.height() * ratioY - marginBottom - yInterval * value;
    }

    /**
     * 绘制坐标系
     * Author YanJiaqi
     * Created at 15/12/9 下午7:32
     */

    private void drawCoor(Rect mRect, Canvas canvas, float ratioX, float ratioY) {
        //计算
        xCoors = new float[columsX + 1];
        for (int i = 0; i < columsX + 1; i++) {
            xCoors[i] = yWidth + i * (mRect.width() * ratioX - yWidth - pointSize / 2
                    - getResources().getDimension(R.dimen.host_results_coordinate_linewidth)) / columsX;
        }
        yCoors = new float[rowsY + 1];
        for (int i = 0; i < rowsY + 1; i++) {
            yCoors[i] = marginTop + i * (mRect.height() * ratioY - marginTop - marginBottom
                    - getResources().getDimension(R.dimen.host_results_coordinate_linewidth)) / rowsY;
        }

        //画坐标系
        for (int i = 0; i < xCoors.length; i++) {
            canvas.drawLine(xCoors[i], yCoors[0], xCoors[i], yCoors[yCoors.length - 1], coorLinePaint);
        }

        //画纵坐标值
        String txt = "";
        for (int i = 0; i < yCoors.length; i++) {
            canvas.drawLine(xCoors[0], yCoors[i], mRect.width() * ratioX, yCoors[i], coorLinePaint);
            if (mData.isBpToKpa()) {
                txt = BpUtils.getInstance().mmghToKpa((yMax - (yMax - yMin) / (rowsY) * i)) + "";
            } else {
                txt = (yMax - (yMax - yMin) / (rowsY) * i) + "";
            }
            canvas.drawText(txt, xCoors[0] - coorTvMargin, yCoors[i] + marginTop, coorTvPaint);
        }

        //画横坐标值
        for (int i = 1; i < xCoors.length; i++) {
            txt = mData.getDates().get(i - 1);
            canvas.drawText(txt, xCoors[i], yCoors[yCoors.length - 1] + coorTvMargin + coorTvPaint.getTextSize(), coorTvPaint);
        }
    }

    @Override
    protected void logic() {

    }

    @Override
    protected void reset() {

    }

    /**
     * 刷新数据
     * Author YanJiaqi
     * Created at 15/12/9 下午8:37
     */

    public void notifyChangedData(ResultsCoordinateData data) {
        mData = data;
        invalidate();
    }
}
