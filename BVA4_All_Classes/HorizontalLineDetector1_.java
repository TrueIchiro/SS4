
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class HorizontalLineDetector1_ implements PlugInFilter {
  
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		return DOES_8G+DOES_STACKS+SUPPORTS_MASKING;
	} //setup
	

	
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();
		int tgtRadius = 1;
		
		//input image
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        //convert to double
        double[][] inDataArrDouble = ImageJUtility.convertToDoubleArr2D(inDataArrInt, width, height);
        
        //calculate the filter kernel (yellow filter mask in lecture slides)
        //attention: due to pre-filling of the array, we get detected vertical lines
        double[][] lineDetectorKernelV = new double[][]{
        	{-1.0, -1.0, -1.0}, 
        	{2.0, 2.0, 2.0}, 
        	{-1.0, -1.0, -1.0}
        };
        		
        //get convolved image
        double[][] filteredImage = ConvolutionFilter.ConvolveDouble(inDataArrDouble, width, height, lineDetectorKernelV, tgtRadius);               
        
        //at that time, lines might be represented by values inbetween e.g. [-1000;1000]
        //TODO: get abs value and scale to 255
        
        double maxVal = 0;
        for (int x = 0; x < width; x++)
        {
        	for (int y = 0; y < height; y++)
            {
            	double actVal = Math.abs(filteredImage[x][y]);
            	if (actVal > maxVal)
            		maxVal = actVal;
            }
        }
        
        double scaleFactor = 255.0/maxVal;
        
        for (int x = 0; x < width; x++)
        {
        	for (int y = 0; y < height; y++)
            {
            	filteredImage[x][y] = Math.abs(filteredImage[x][y]) * scaleFactor;
            }
        }
        
        ImageJUtility.showNewImage(filteredImage, width, height, "vertical lines of width=1 detected");
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a HorizontalLineDetector with width 1\n");
	} //showAbout
	
} //class HorizontalLineDetector1_

