package topteam.com.oee_kanban;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BarChart barChart_v1;   //时间稼动率
    private BarChart barChart_v2;   //时间的损失
    private BarChart barChart_v3;   //不良TOPS
    private BarChart barChart_v4;   //良品率
    private BarChart barChart_v5;   //性能稼动率
    private BarChart barChart_v6;   //利用率
    private BarChart barChart_v7;   //综合性能TOPS
    private BarChart barChart_v8;   //综合性能OEE
    private List<Float> dayList = new ArrayList<>();   //存放日对比数据
    private List<String> valuesX = new ArrayList<>(); //日对比数据X轴
    private List<String> valuesX2 = new ArrayList<>(); //良品率数据X轴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initView();
        initBarChart1();
        initBarChart2();
        initBarChart3();
        initBarChart4();
        initBarChart5();
        initBarChart6();
        initBarChart7();
        initBarChart8();
    }

    /**
     * 初始化所有的控件
     */
    private void initView() {
        barChart_v1 = findViewById(R.id.bar_chart1);
        barChart_v2 = findViewById(R.id.bar_chart2);
        barChart_v3 = findViewById(R.id.bar_chart3);
        barChart_v4 = findViewById(R.id.bar_chart4);
        barChart_v5 = findViewById(R.id.bar_chart5);
        barChart_v6 = findViewById(R.id.bar_chart6);
        barChart_v7 = findViewById(R.id.bar_chart7);
        barChart_v8 = findViewById(R.id.bar_chart8);
    }

    /**
     * 柱形图   1
     */
    private void initBarChart1() {

        barChart_v1.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v1.setTouchEnabled(false);  //进制触控
        barChart_v1.setScaleEnabled(false); //设置能否缩放
        barChart_v1.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v1.setDrawBarShadow(false);  //设置阴影
        barChart_v1.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v1.setDescription(null); //不描述
        //X轴的数据格式
        XAxis xAxis = barChart_v1.getXAxis();
        valuesX.clear();
        valuesX.add("周一");
        valuesX.add("周二");
        valuesX.add("周三");
        valuesX.add("周四");
        valuesX.add("周五");
        valuesX.add("周六");
        valuesX.add("周日");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        //设置位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置是否绘制网格线
        xAxis.setDrawGridLines(false);
        barChart_v1.getAxisLeft().setDrawGridLines(false);
        // barChart.animateY(2500);
        //设置X轴文字剧中对齐
        xAxis.setCenterAxisLabels(false);
        //X轴最小间距
        xAxis.setGranularity(1f);
        //设置字体颜色
        xAxis.setTextColor(Color.parseColor("#00FFFF"));


        //Y轴的数据格式
        YAxis axisLeft = barChart_v1.getAxisLeft();
        axisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int in = (int) value;
                return in + "%";
            }
        });
        barChart_v1.animateY(2500);
        //设置Y轴刻度的最大值
        axisLeft.setAxisMinValue(0);
        axisLeft.setAxisMaxValue(100);
        barChart_v1.getAxisRight().setEnabled(false);
        //设置字体颜色
        axisLeft.setTextColor(Color.parseColor("#00FFFF"));

        //设置数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        /*if (dayList.size() == 7) {
            yVals1.add(new BarEntry(0, dayList.get(0)));
            yVals1.add(new BarEntry(1, dayList.get(1)));
            yVals1.add(new BarEntry(2, dayList.get(2)));
            yVals1.add(new BarEntry(3, dayList.get(3)));
            yVals1.add(new BarEntry(4, dayList.get(4)));
            yVals1.add(new BarEntry(5, dayList.get(5)));
            yVals1.add(new BarEntry(6, dayList.get(6)));
        }*/

        yVals1.add(new BarEntry(0, 30));
        yVals1.add(new BarEntry(1, 46));
        yVals1.add(new BarEntry(2, 55));
        yVals1.add(new BarEntry(3, 66));
        yVals1.add(new BarEntry(4, 77));
        yVals1.add(new BarEntry(5, 80));
        yVals1.add(new BarEntry(6, 98));
        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "");
        //设置多彩 也可以单一颜色
        set1.setColor(Color.parseColor("#4169E1"));
        set1.setDrawValues(false);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        barChart_v1.setData(data);
        barChart_v1.setFitBars(true);
        //设置文字的大小
        set1.setValueTextSize(12f);
        //设置每条柱子的宽度
        data.setBarWidth(0.7f);
        //设置字体颜色
        data.setValueTextColor(Color.WHITE);
        barChart_v1.invalidate();

        for (IDataSet set : barChart_v1.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        barChart_v1.invalidate();
        barChart_v1.setAutoScaleMinMaxEnabled(!barChart_v1.isAutoScaleMinMaxEnabled());
        barChart_v1.notifyDataSetChanged();
        barChart_v1.invalidate();


    }

    /**
     * 柱形图   2
     */
    private void initBarChart2() {

        barChart_v2.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v2.setTouchEnabled(false);  //进制触控
        barChart_v2.setScaleEnabled(false); //设置能否缩放
        barChart_v2.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v2.setDrawBarShadow(false);  //设置阴影
        barChart_v2.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v2.setDescription(null); //不描述
        //设置是否绘制网格线
        barChart_v2.getAxisLeft().setDrawGridLines(false);
        //因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
        XAxis yAxis = barChart_v2.getXAxis();
        yAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yAxis.setDrawGridLines(false);
        yAxis.setTextSize(12f);
        yAxis.setLabelCount(5);
        yAxis.setTextColor(Color.RED);
        yAxis.setDrawGridLines(false);

        yAxis.setGranularity(1f); // 防止放大图后，标签错乱
        final String label[] = {"修模", "保养", "品质异常", "调机", "待料"};
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                try {
                    return label[(int) v];
                } catch (Exception e) {
                    return "";
                }
            }
        });

        YAxis yAxis_right = barChart_v2.getAxisRight();
        yAxis_right.setAxisMinimum(0f);
        yAxis_right.setAxisMaximum(100f);
        yAxis_right.setTextSize(12f);
        yAxis_right.setDrawGridLines(false);
        yAxis_right.setEnabled(false);
       /* yAxis_right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return v + "0%";
            }
        });*/

        // 不显示最顶部的轴
        YAxis yAxis_left = barChart_v2.getAxisLeft();
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setEnabled(false);
        yAxis_left.setDrawGridLines(false);
        //设置数据
        ArrayList<BarEntry> xVals1 = new ArrayList<>();
        xVals1.add(new BarEntry(0, 30));
        xVals1.add(new BarEntry(1, 46));
        xVals1.add(new BarEntry(2, 55));
        xVals1.add(new BarEntry(3, 66));
        xVals1.add(new BarEntry(4, 77));
        BarDataSet barDataSet = new BarDataSet(xVals1, "");
        barDataSet.setColor(Color.BLACK);
        barDataSet.setValueTextColor(Color.GREEN);
        barDataSet.setBarBorderColor(Color.GREEN);
        barDataSet.setBarBorderWidth(1);
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f); // 设置柱子的宽度
        barChart_v2.setData(barData);

    }

    /**
     * 柱形图   3
     */
    private void initBarChart3() {

        barChart_v3.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v3.setTouchEnabled(false);  //进制触控
        barChart_v3.setScaleEnabled(false); //设置能否缩放
        barChart_v3.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v3.setDrawBarShadow(false);  //设置阴影
        barChart_v3.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v3.setDescription(null); //不描述
        //设置是否绘制网格线
        barChart_v3.getAxisLeft().setDrawGridLines(false);

        XAxis yAxis = barChart_v3.getXAxis();
        yAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yAxis.setDrawGridLines(false);
        yAxis.setTextSize(12f);
        yAxis.setLabelCount(5);
        yAxis.setTextColor(Color.RED);
        yAxis.setDrawGridLines(false);

        //因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
        yAxis.setGranularity(1f); // 防止放大图后，标签错乱
        final String label[] = {"尺寸不良", "披锋", "批伤", "污渍", "色差"};
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                try {
                    return label[(int) v];
                } catch (Exception e) {
                    return "";
                }
            }
        });

        YAxis yAxis_right = barChart_v3.getAxisRight();
        yAxis_right.setAxisMinimum(0f);
        yAxis_right.setAxisMaximum(100f);
        yAxis_right.setTextSize(12f);
        yAxis_right.setDrawGridLines(false);
        yAxis_right.setEnabled(false);
       /* yAxis_right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return v + "0%";
            }
        });*/

        // 不显示最顶部的轴
        YAxis yAxis_left = barChart_v3.getAxisLeft();
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setEnabled(false);
        yAxis_left.setDrawGridLines(false);
        //设置数据
        ArrayList<BarEntry> xVals1 = new ArrayList<>();
        xVals1.add(new BarEntry(0, 30));
        xVals1.add(new BarEntry(1, 46));
        xVals1.add(new BarEntry(2, 55));
        xVals1.add(new BarEntry(3, 66));
        xVals1.add(new BarEntry(4, 77));
        BarDataSet barDataSet = new BarDataSet(xVals1, "");
        barDataSet.setColor(Color.BLACK);
        barDataSet.setValueTextColor(Color.GREEN);
        barDataSet.setBarBorderColor(Color.GREEN);
        barDataSet.setBarBorderWidth(1);
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f); // 设置柱子的宽度
        barChart_v3.setData(barData);

    }

    /**
     * 柱形图   4
     */
    private void initBarChart4() {

        barChart_v4.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v4.setTouchEnabled(false);  //进制触控
        barChart_v4.setScaleEnabled(false); //设置能否缩放
        barChart_v4.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v4.setDrawBarShadow(false);  //设置阴影
        barChart_v4.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v4.setDescription(null); //不描述
        //X轴的数据格式
        XAxis xAxis = barChart_v4.getXAxis();
        valuesX.clear();
        valuesX.add("周一");
        valuesX.add("周二");
        valuesX.add("周三");
        valuesX.add("周四");
        valuesX.add("周五");
        valuesX.add("周六");
        valuesX.add("周日");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        //设置位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置是否绘制网格线
        xAxis.setDrawGridLines(false);
        barChart_v4.getAxisLeft().setDrawGridLines(false);
        // barChart.animateY(2500);
        //设置X轴文字剧中对齐
        xAxis.setCenterAxisLabels(false);
        //X轴最小间距
        xAxis.setGranularity(1f);
        //设置字体颜色
        xAxis.setTextColor(Color.parseColor("#00FFFF"));

        //Y轴的数据格式
        YAxis axisLeft = barChart_v4.getAxisLeft();
        axisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int in = (int) value;
                return in + "%";
            }
        });



        //设置Y轴刻度的最大值
         axisLeft.setAxisMinimum(0);
         axisLeft.setAxisMaximum(100);
        barChart_v4.animateY(2500);
        barChart_v4.getAxisRight().setEnabled(false);
        //设置字体颜色
        axisLeft.setTextColor(Color.parseColor("#00FFFF"));

        //设置数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        /*if (dayList.size() == 7) {
            yVals1.add(new BarEntry(0, dayList.get(0)));
            yVals1.add(new BarEntry(1, dayList.get(1)));
            yVals1.add(new BarEntry(2, dayList.get(2)));
            yVals1.add(new BarEntry(3, dayList.get(3)));
            yVals1.add(new BarEntry(4, dayList.get(4)));
            yVals1.add(new BarEntry(5, dayList.get(5)));
            yVals1.add(new BarEntry(6, dayList.get(6)));
        }*/

        yVals1.add(new BarEntry(0, 30));
        yVals1.add(new BarEntry(1, 46));
        yVals1.add(new BarEntry(2, 55));
        yVals1.add(new BarEntry(3, 66));
        yVals1.add(new BarEntry(4, 77));
        yVals1.add(new BarEntry(5, 80));
        yVals1.add(new BarEntry(6, 98));
        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "");
        //设置多彩 也可以单一颜色
        set1.setColor(Color.parseColor("#4169E1"));
        set1.setDrawValues(false);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        barChart_v4.setData(data);
        barChart_v4.setFitBars(true);
        //设置文字的大小
        set1.setValueTextSize(12f);
        //设置每条柱子的宽度
        data.setBarWidth(0.7f);
        //设置字体颜色
        data.setValueTextColor(Color.WHITE);
        barChart_v4.invalidate();

        for (IDataSet set : barChart_v4.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        barChart_v4.invalidate();
        barChart_v4.setAutoScaleMinMaxEnabled(!barChart_v1.isAutoScaleMinMaxEnabled());
        barChart_v4.notifyDataSetChanged();
        barChart_v4.invalidate();

    }

    /**
     * 柱形图   5
     */
    private void initBarChart5() {

        barChart_v5.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v5.setTouchEnabled(false);  //进制触控
        barChart_v5.setScaleEnabled(false); //设置能否缩放
        barChart_v5.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v5.setDrawBarShadow(false);  //设置阴影
        barChart_v5.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v5.setDescription(null); //不描述


        //X轴的数据格式
        XAxis xAxis = barChart_v5.getXAxis();
        valuesX.clear();
        valuesX.add("周一");
        valuesX.add("周二");
        valuesX.add("周三");
        valuesX.add("周四");
        valuesX.add("周五");
        valuesX.add("周六");
        valuesX.add("周日");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        //设置位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置是否绘制网格线
        xAxis.setDrawGridLines(false);
        barChart_v5.getAxisLeft().setDrawGridLines(false);
        // barChart.animateY(2500);
        //设置X轴文字剧中对齐
        xAxis.setCenterAxisLabels(false);
        //X轴最小间距
        xAxis.setGranularity(1f);
        //设置字体颜色
        xAxis.setTextColor(Color.parseColor("#00FFFF"));


        //Y轴的数据格式
        YAxis axisLeft = barChart_v5.getAxisLeft();
        axisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int in = (int) value;
                return in + "%";
            }
        });
        barChart_v5.animateY(2500);
        //设置Y轴刻度的最大值
        axisLeft.setAxisMinValue(0);
        axisLeft.setAxisMaxValue(100);
        barChart_v5.getAxisRight().setEnabled(false);
        //设置字体颜色
        axisLeft.setTextColor(Color.parseColor("#00FFFF"));

        //设置数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        /*if (dayList.size() == 7) {
            yVals1.add(new BarEntry(0, dayList.get(0)));
            yVals1.add(new BarEntry(1, dayList.get(1)));
            yVals1.add(new BarEntry(2, dayList.get(2)));
            yVals1.add(new BarEntry(3, dayList.get(3)));
            yVals1.add(new BarEntry(4, dayList.get(4)));
            yVals1.add(new BarEntry(5, dayList.get(5)));
            yVals1.add(new BarEntry(6, dayList.get(6)));
        }*/

        yVals1.add(new BarEntry(0, 30));
        yVals1.add(new BarEntry(1, 46));
        yVals1.add(new BarEntry(2, 55));
        yVals1.add(new BarEntry(3, 66));
        yVals1.add(new BarEntry(4, 77));
        yVals1.add(new BarEntry(5, 80));
        yVals1.add(new BarEntry(6, 98));
        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "");
        //设置多彩 也可以单一颜色
        set1.setColor(Color.parseColor("#4169E1"));
        set1.setDrawValues(false);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        barChart_v5.setData(data);
        barChart_v5.setFitBars(true);
        //设置文字的大小
        set1.setValueTextSize(12f);
        //设置每条柱子的宽度
        data.setBarWidth(0.7f);
        //设置字体颜色
        data.setValueTextColor(Color.WHITE);
        barChart_v5.invalidate();

        for (IDataSet set : barChart_v5.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        barChart_v5.invalidate();
        barChart_v5.setAutoScaleMinMaxEnabled(!barChart_v5.isAutoScaleMinMaxEnabled());
        barChart_v5.notifyDataSetChanged();
        barChart_v5.invalidate();

    }

    /**
     * 柱形图   6
     */
    private void initBarChart6() {

        barChart_v6.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v6.setTouchEnabled(false);  //进制触控
        barChart_v6.setScaleEnabled(false); //设置能否缩放
        barChart_v6.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v6.setDrawBarShadow(false);  //设置阴影
        barChart_v6.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v6.setDescription(null); //不描述
        //设置是否绘制网格线
        barChart_v6.getAxisLeft().setDrawGridLines(false);

        //因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
        XAxis yAxis = barChart_v6.getXAxis();
        yAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yAxis.setDrawGridLines(false);
        yAxis.setTextSize(12f);
        yAxis.setLabelCount(5);
        yAxis.setTextColor(Color.RED);
        yAxis.setDrawGridLines(false);

        yAxis.setGranularity(1f); // 防止放大图后，标签错乱
        final String label[] = {"1号注塑机", "2号注塑机", "3号注塑机", "4号注塑机", "5号注塑机"};
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                try {
                    return label[(int) v];
                } catch (Exception e) {
                    return "";
                }
            }
        });

        YAxis yAxis_right = barChart_v6.getAxisRight();
        yAxis_right.setAxisMinimum(0f);
        yAxis_right.setAxisMaximum(100f);
        yAxis_right.setTextSize(12f);
        yAxis_right.setDrawGridLines(false);
        yAxis_right.setEnabled(false);
       /* yAxis_right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return v + "0%";
            }
        });*/

        // 不显示最顶部的轴
        YAxis yAxis_left = barChart_v6.getAxisLeft();
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setEnabled(false);
        yAxis_left.setDrawGridLines(false);
        //设置数据
        ArrayList<BarEntry> xVals1 = new ArrayList<>();
        xVals1.add(new BarEntry(0, 30));
        xVals1.add(new BarEntry(1, 46));
        xVals1.add(new BarEntry(2, 55));
        xVals1.add(new BarEntry(3, 66));
        xVals1.add(new BarEntry(4, 77));
        BarDataSet barDataSet = new BarDataSet(xVals1, "");
        barDataSet.setColor(Color.BLACK);
        barDataSet.setValueTextColor(Color.GREEN);
        barDataSet.setBarBorderColor(Color.GREEN);
        barDataSet.setBarBorderWidth(1);
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f); // 设置柱子的宽度
        barChart_v6.setData(barData);


    }

    /**
     * 柱形图图    7
     */
    private void initBarChart7() {

        barChart_v7.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v7.setTouchEnabled(false);  //进制触控
        barChart_v7.setScaleEnabled(false); //设置能否缩放
        barChart_v7.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v7.setDrawBarShadow(false);  //设置阴影
        barChart_v7.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v7.setDescription(null); //不描述
        //设置是否绘制网格线
        barChart_v7.getAxisLeft().setDrawGridLines(false);

        //因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
        XAxis yAxis = barChart_v7.getXAxis();
        yAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        yAxis.setDrawGridLines(false);
        yAxis.setTextSize(12f);
        yAxis.setLabelCount(5);
        yAxis.setTextColor(Color.RED);
        yAxis.setDrawGridLines(false);

        yAxis.setGranularity(1f); // 防止放大图后，标签错乱
        final String label[] = {"1号注塑机", "2号注塑机", "3号注塑机", "4号注塑机", "5号注塑机"};
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                try {
                    return label[(int) v];
                } catch (Exception e) {
                    return "";
                }
            }
        });

        YAxis yAxis_right = barChart_v7.getAxisRight();
        yAxis_right.setAxisMinimum(0f);
        yAxis_right.setAxisMaximum(100f);
        yAxis_right.setTextSize(12f);
        yAxis_right.setDrawGridLines(false);
        yAxis_right.setEnabled(false);
       /* yAxis_right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return v + "0%";
            }
        });*/

        // 不显示最顶部的轴
        YAxis yAxis_left = barChart_v7.getAxisLeft();
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setEnabled(false);
        yAxis_left.setDrawGridLines(false);
        //设置数据
        ArrayList<BarEntry> xVals1 = new ArrayList<>();
        xVals1.add(new BarEntry(0, 30));
        xVals1.add(new BarEntry(1, 46));
        xVals1.add(new BarEntry(2, 55));
        xVals1.add(new BarEntry(3, 66));
        xVals1.add(new BarEntry(4, 77));
        BarDataSet barDataSet = new BarDataSet(xVals1, "");
        barDataSet.setColor(Color.BLACK);
        barDataSet.setValueTextColor(Color.GREEN);
        barDataSet.setBarBorderColor(Color.GREEN);
        barDataSet.setBarBorderWidth(1);
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f); // 设置柱子的宽度
        barChart_v7.setData(barData);

    }

    /**
     * 柱形图   8
     */
    private void initBarChart8() {

        barChart_v8.setDrawValueAboveBar(true);  //设置所有的数值在图形的上面,而不是图形上
        barChart_v8.setTouchEnabled(false);  //进制触控
        barChart_v8.setScaleEnabled(false); //设置能否缩放
        barChart_v8.setPinchZoom(false);  //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        barChart_v8.setDrawBarShadow(false);  //设置阴影
        barChart_v8.setDrawGridBackground(false);  //设置背景是否网格显示
        barChart_v8.setDescription(null); //不描述


        //X轴的数据格式
        XAxis xAxis = barChart_v8.getXAxis();
        valuesX.clear();
        valuesX.add("周一");
        valuesX.add("周二");
        valuesX.add("周三");
        valuesX.add("周四");
        valuesX.add("周五");
        valuesX.add("周六");
        valuesX.add("周日");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        //设置位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置是否绘制网格线
        xAxis.setDrawGridLines(false);
        barChart_v8.getAxisLeft().setDrawGridLines(false);
        // barChart.animateY(2500);
        //设置X轴文字剧中对齐
        xAxis.setCenterAxisLabels(false);
        //X轴最小间距
        xAxis.setGranularity(1f);
        //设置字体颜色
        xAxis.setTextColor(Color.parseColor("#00FFFF"));


        //Y轴的数据格式
        YAxis axisLeft = barChart_v8.getAxisLeft();
        axisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int in = (int) value;
                return in + "%";
            }
        });
        barChart_v8.animateY(2500);
        //设置Y轴刻度的最大值
        axisLeft.setAxisMinValue(0);
        axisLeft.setAxisMaxValue(100);
        barChart_v8.getAxisRight().setEnabled(false);
        //设置字体颜色
        axisLeft.setTextColor(Color.parseColor("#00FFFF"));

        //设置数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        /*if (dayList.size() == 7) {
            yVals1.add(new BarEntry(0, dayList.get(0)));
            yVals1.add(new BarEntry(1, dayList.get(1)));
            yVals1.add(new BarEntry(2, dayList.get(2)));
            yVals1.add(new BarEntry(3, dayList.get(3)));
            yVals1.add(new BarEntry(4, dayList.get(4)));
            yVals1.add(new BarEntry(5, dayList.get(5)));
            yVals1.add(new BarEntry(6, dayList.get(6)));
        }*/

        yVals1.add(new BarEntry(0, 30));
        yVals1.add(new BarEntry(1, 46));
        yVals1.add(new BarEntry(2, 55));
        yVals1.add(new BarEntry(3, 66));
        yVals1.add(new BarEntry(4, 77));
        yVals1.add(new BarEntry(5, 80));
        yVals1.add(new BarEntry(6, 98));
        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "");
        //设置多彩 也可以单一颜色
        set1.setColor(Color.parseColor("#4169E1"));
        set1.setDrawValues(false);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        barChart_v8.setData(data);
        barChart_v8.setFitBars(true);
        //设置文字的大小
        set1.setValueTextSize(12f);
        //设置每条柱子的宽度
        data.setBarWidth(0.7f);
        //设置字体颜色
        data.setValueTextColor(Color.WHITE);
        barChart_v8.invalidate();

        for (IDataSet set : barChart_v8.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        barChart_v8.invalidate();
        barChart_v8.setAutoScaleMinMaxEnabled(!barChart_v8.isAutoScaleMinMaxEnabled());
        barChart_v8.notifyDataSetChanged();
        barChart_v8.invalidate();

    }





}
