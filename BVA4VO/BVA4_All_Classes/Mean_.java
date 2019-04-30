
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class Mean_ implements PlugInFilter {
  
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
		
		GenericDialog gd = new GenericDialog("User Input for mean filtering");
        gd.addNumericField("Enter radius", tgtRadius, 0);
        gd.showDialog();
        
        //if user has NOT cancelled the dialog
        //then process the input scale factor
        if (!gd.wasCanceled())
        {
        	tgtRadius = (int)gd.getNextNumber();
        }
		
		//input image
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        //convert to double
        double[][] inDataArrDouble = ImageJUtility.convertToDoubleArr2D(inDataArrInt, width, height);
        
        //calculate the filter kernel (yellow filter mask in lecture slides)
        double[][] meanKernel = ConvolutionFilter.GetMeanMask(tgtRadius);
        
        //get convolved image
        double[][] filteredImage = ConvolutionFilter.ConvolveDouble(inDataArrDouble, width, height, meanKernel, tgtRadius);               
        
        ImageJUtility.showNewImage(filteredImage, width, height, "mean with kernel r=" + tgtRadius);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class Mean_

