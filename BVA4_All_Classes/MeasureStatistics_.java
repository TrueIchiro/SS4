
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class MeasureStatistics_ implements PlugInFilter {
  
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		return DOES_8G+DOES_STACKS+SUPPORTS_MASKING;
	} //setup
	

	
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();
		int tgtRadius = 4;
				
		
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
               
        //calc statistics
        double sum = 0.0;
        int minVal = 255;
        int maxVal = 0;
        
        //iterate over all pixels
        for (int x = 0; x < width; x++)
        {
        	for (int y = 0; y < height; y++)
            {
            	int actVal = inDataArrInt[x][y];
            	sum += actVal;
            	
            	if (actVal > maxVal)
            		maxVal = actVal;
            	if (actVal < minVal)
            		minVal = actVal;
            }
        }
        
        sum /= (width * height);
        
        IJ.log("avg = " + sum + " min = " + minVal + " max = " + maxVal);
        
        ImageJUtility.showNewImage(inDataArrInt, width, height, "orig image");
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class MeasureStatistics_

