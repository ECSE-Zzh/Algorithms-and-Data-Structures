import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.log;


public class ProofProject {

    public static void main(String[] args) {
        ArrayList<Integer> inputSizes = new ArrayList<>();
        ArrayList<Long> avgInsertionTimes = new ArrayList<>();

        int randInputSize = 0;

        //collect 200 insertion runtime data
        for (int i = 200; i < 2200; i+=10){
            //generate random input size to collect more persuasive data
            randInputSize = i;
            inputSizes.add(randInputSize);
            avgInsertionTimes.add(CollectRuntimeData(randInputSize));
        }

        //build chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Execution Time of insertion node into a BST").xAxisTitle("Number of Nodes").yAxisTitle("Execution time(μs)").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        XYSeries series1 = chart.addSeries("runtime of BST node insertion", inputSizes, avgInsertionTimes);
        series1.setMarker(SeriesMarkers.CIRCLE);

        // add reference log function
        ArrayList<Double> standardLog = new ArrayList<>();
        for (int j = 0; j < 200; j++){
            standardLog.add(13.7*log(inputSizes.get(j))+5.8);
        }
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        XYSeries series = chart.addSeries("13.7 * log(inputSizes)+5.8", inputSizes, standardLog);
        series.setMarker(SeriesMarkers.CIRCLE);


        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./BST_Run_Time_Chart1", BitmapEncoder.BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long CollectRuntimeData (int randInputSize){
        Node root = null;

        int numOfInsertionProcess = 25;
        long totalInsertionTime = 0;

        //Repeating the insertion process multiple times
        // to get a more accurate estimate of the average insertion time.
        for(int i = 0; i < numOfInsertionProcess; i++){
            // record the start time
            long startTime = System.nanoTime()/1000;

            for (int j = 0; j < randInputSize; j++){
                int val = (int)(Math.random()*5);
                root = BinarySearchTree.insertNode(val, root);
            }

            // calculate the total insertion time
            long endTime = System.nanoTime()/1000;
            long insertionTime = endTime - startTime;
            totalInsertionTime += insertionTime;
        }

        long avgInsertionTime = (totalInsertionTime / numOfInsertionProcess);
        return avgInsertionTime;
    }

}