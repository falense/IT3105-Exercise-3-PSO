package PSOSolver;

import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ParticleManagerGUI {
	private static PlotXYWrapper plot; 
	public ParticleManagerGUI() {
		if (plot == null)
			plot = new PlotXYWrapper("Particle swarm optimization", "Best particle");
	}
	public void setup(){

		plot.pack();
		plot.setVisible(true);
	}
	public void addValue(int serie, double value){
		plot.addValue(serie, value);
	}
	public void run(){
		setup();
		int c = 0;
		while(true){
			Random r = new Random();
			for (int series = 0; series < c/100; series++)
				plot.addValue(series,series);
			c++;

		}
	}
	
	class PlotXYWrapper extends JFrame{
		private static final long serialVersionUID = 1L;
		private XYSeries [] seriesArray;
		private int [] seriesMaxXValue;
		private XYSeriesCollection dataset;

		public PlotXYWrapper(String applicationTitle, String chartTitle) {
			super(applicationTitle);
			// This will create the dataset 
			dataset = createDataset();
			// based on the dataset we create the chart
			JFreeChart chart = createChart(dataset, chartTitle);
			// we put the chart into a panel
			ChartPanel chartPanel = new ChartPanel(chart);
			// default size
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
			// add it to our application
			setContentPane(chartPanel);
		}
		/** * Creates a sample dataset */
		private  XYSeriesCollection createDataset() {

			seriesArray = new XYSeries[1];
			seriesMaxXValue = new int[1];
			for (int serie = 0; serie < 1; serie++){
				seriesArray[serie] = new XYSeries("Series " + serie);
				seriesMaxXValue[serie] = 0;
				/*for (int i = 0; i < 10; i++){

					seriesArray[serie].add(i, i*serie);
				}*/
			}
			XYSeriesCollection result = new XYSeriesCollection();
			for (int serie = 0; serie < 1; serie++){

				result.addSeries(seriesArray[serie]);
			}
			return result;

		}


		/** * Creates a chart */

		private JFreeChart createChart(XYDataset dataset, String title) {

			JFreeChart chart = ChartFactory.createXYLineChart(title, "Axis Y", "Axis X", dataset);
			return chart;

		}
		private void resizeSeries(int newSize){
			int oldSize = seriesArray.length;
			XYSeries [] newSeriesArray = new XYSeries[newSize];
			int [] newSeriesMaxXValue = new int[newSize];
			for (int index = 0; index < seriesArray.length; index++){
				newSeriesArray[index] = seriesArray[index];
				newSeriesMaxXValue[index] = seriesMaxXValue[index];
			}
			for (int index = oldSize; index < newSize; index++){
				newSeriesArray[index] = new XYSeries("Serie " + index);
				newSeriesMaxXValue[index] = 0;
				dataset.addSeries(newSeriesArray[index]);
			}
			seriesArray = newSeriesArray;
			seriesMaxXValue = newSeriesMaxXValue;
		}
		public void addValue(int serie, double value){
			if (serie < 0){
				System.err.println("Invalid serieIndex in addValue");
				return;
			}
			if (serie >= seriesArray.length){
				resizeSeries(serie+1);
			}
			seriesArray[serie].add(seriesMaxXValue[serie]++,value);

		}

	}

	public static void main(String[] args) {
		ParticleManagerGUI demo = new ParticleManagerGUI();
		demo.run();
	}

}
