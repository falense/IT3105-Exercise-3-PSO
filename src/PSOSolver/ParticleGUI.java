package PSOSolver;

import java.awt.RenderingHints;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ParticleGUI extends ApplicationFrame {

	    /** A constant for the number of items in the sample dataset. */
	    private static final int COUNT = 5000;

	    /** The data. */
	    private float[][] data = new float[2][COUNT];

	    /**
	     * Creates a new fast scatter plot demo.
	     *
	     * @param title  the frame title.
	     */
	    public ParticleGUI(final String title) {

	        super(title);
	        populateData();
	        final NumberAxis domainAxis = new NumberAxis("X");
	        domainAxis.setAutoRangeIncludesZero(false);
	        final NumberAxis rangeAxis = new NumberAxis("Y");
	        rangeAxis.setAutoRangeIncludesZero(false);
	        final FastScatterPlot plot = new FastScatterPlot(this.data, domainAxis, rangeAxis);
	        final JFreeChart chart = new JFreeChart("Fast Scatter Plot", plot);


	        final ChartPanel panel = new ChartPanel(chart, true);
	        panel.setPreferredSize(new java.awt.Dimension(500, 270));
	        
	        setContentPane(panel);

	    }

	    // ****************************************************************************
	    // * JFREECHART DEVELOPER GUIDE                                               *
	    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
	    // * to purchase from Object Refinery Limited:                                *
	    // *                                                                          *
	    // * http://www.object-refinery.com/jfreechart/guide.html                     *
	    // *                                                                          *
	    // * Sales are used to provide funding for the JFreeChart project - please    * 
	    // * support us so that we can continue developing free software.             *
	    // ****************************************************************************
	    
	    /**
	     * Populates the data array with random values.
	     */
	    private void populateData() {

	        for (int i = 0; i < this.data[0].length; i++) {
	            final float x = (float) i + 100000;
	            this.data[0][i] = x;
	            this.data[1][i] = 100000 + (float) Math.random() * COUNT;
	        }

	    }
	    public void run(){
	    	int c = 0;
	    	while (true){
	    		for (int i = 0; i < this.data[0].length; i++) {
		            final float x = (float) i + 100000;
		            this.data[0][i] = x;
		            this.data[1][i] = 100000+c;
		        }
	    		c++;
	    	}
	    }
	    

	    /**
	     * Starting point for the demonstration application.
	     *
	     * @param args  ignored.
	     */
	    public static void main(final String[] args) {

	        final ParticleGUI demo = new ParticleGUI("Fast Scatter Plot Demo");
	        demo.pack();
	        demo.setVisible(true);
	        demo.run();

	    }

	
}

