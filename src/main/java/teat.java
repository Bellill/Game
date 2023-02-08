import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author zsl
 * @Date 2023/2/8 13:12
 * @PackageName:PACKAGE_NAME
 * @ClassName: teat
 * @Description:
 * @Version 1.0
 */
public class teat {
    public static void main(String[] args) throws Exception {

        Map<Integer, Integer> map = new HashMap<>();

        int[] x = {0,1,2,3,4,5};
        int[] y = {0,1,2,3,4,5,6,7,8,9};
        List xy = new ArrayList<>();
        for (int x1 : x){
            int tens = x1 * 10;
            for (int y1 : y){
                int digits = y1;
                int number = tens + digits;
                xy.add(number);
                if (map.get(number) == null){
                    map.put(number, 1);
                }else {
                    map.put(number, map.get(number) + 1);
                }
            }
        }
        List xADDy = new ArrayList<>();
        for (int x2 : x){
            for (int y2 : y){
                int number = x2 + y2;
                xADDy.add(number);
                if (map.get(number) == null){
                    map.put(number, 1);
                }else {
                    map.put(number, map.get(number) + 1);
                }
            }
        }
        List xyADD1 = new ArrayList<>();
        for (int x2 : x){
            for (int y2 : y){
                int number = x2*10 + y2 + 1;
                xyADD1.add(number);
                if (map.get(number) == null){
                    map.put(number, 1);
                }else {
                    map.put(number, map.get(number) + 1);
                }
            }
        }

        List xADDyADD1 = new ArrayList<>();
        for (int x2 : x){
            for (int y2 : y){
                int number = x2 + y2 + 1;
                xADDyADD1.add(number);
                if (map.get(number) == null){
                    map.put(number, 1);
                }else {
                    map.put(number, map.get(number) + 1);
                }
            }
        }
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            total = total + entry.getValue();
        }
        Map<Integer, Double> preMap = new HashMap<>();
        NumberFormat nf = NumberFormat.getPercentInstance();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            preMap.put(entry.getKey(), (double)entry.getValue()/(double)total);
        }


        System.out.println("a");
        createChart1(preMap);

    }

    public static void createChart1(Map<Integer, Double> preMap) throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText("该活动中奖概率柱状图"); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle("楼层"); // x轴标题

        String[] xAxisData = new String[] {
                "2021-01","2021-02","2021-03","2021-04","2021-05","2021-06",
                "2021-07","2021-08","2021-09","2021-10","2021-11","2021-12",
        };
        List<String> listX = new ArrayList<>();
        List<Double> listY = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : preMap.entrySet()){
            listX.add(String.valueOf(entry.getKey()));
            listY.add(Double.valueOf(entry.getValue()));
        }
        String[] strX = listX.toArray(new String[listX.size()]);
        Double[] strY = listY.toArray(new Double[listY.size()]);

        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(strX);// 设置X轴数据

        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle("概率"); // Y轴标题
        yAxis.setCrossBetween(AxisCrossBetween.BETWEEN); // 设置图柱的位置:BETWEEN居中
        Integer[] yAxisData = new Integer[]{
                10, 35, 21, 46, 79, 88,
                39, 102, 71, 28, 99, 57
        };
        XDDFNumericalDataSource<Double> yAxisSource = XDDFDataSourcesFactory.fromArray(strY); // 设置Y轴数据

        // 7、创建柱状图对象
        XDDFBarChartData barChart = (XDDFBarChartData) chart.createData(ChartTypes.BAR, xAxis, yAxis);
        barChart.setBarDirection(BarDirection.COL); // 设置柱状图的方向:BAR横向,COL竖向,默认是BAR

        // 8、加载柱状图数据集
        XDDFBarChartData.Series barSeries = (XDDFBarChartData.Series) barChart.addSeries(xAxisSource, yAxisSource);
        barSeries.setTitle("楼层中奖概率", null); // 图例标题

        // 9、绘制柱状图
        chart.plot(barChart);

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream("F:\\Study\\barChart.docx");
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

}
