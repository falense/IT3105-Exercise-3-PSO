package PSOSolver;

import java.io.IOException;
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
	private int serie;
	public ParticleManagerGUI() {
		if (plot == null)
			plot = new PlotXYWrapper("Particle swarm optimization", "Best particle");
		serie = plot.getNumSeries();
	}
	public void setup(){
		plot.pack();
		plot.setVisible(true);
	}
	public void addValue(double value){
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
			dataset = createDataset();
			JFreeChart chart = createChart(dataset, chartTitle);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
			setContentPane(chartPanel);
		}
		private  XYSeriesCollection createDataset() {
			seriesArray = new XYSeries[0];
			seriesMaxXValue = new int[0];
			XYSeriesCollection result = new XYSeriesCollection();
			return result;
		}

		private JFreeChart createChart(XYDataset dataset, String title) {
			JFreeChart chart = ChartFactory.createXYLineChart(title, "Iteration", "Fitness", dataset);
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
		public int getNumSeries(){
			return seriesArray.length;
		}
		
	}
	public void newPlot(String applicationName, String figureName){
		plot = new PlotXYWrapper(applicationName, figureName);
		setup();
	}
	public static void main(String[] args) {
		ParticleManagerGUI demo = new ParticleManagerGUI();
		demo.run();
	}

}
