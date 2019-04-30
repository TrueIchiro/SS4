
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class Resample_ implements PlugInFilter {
  
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		return DOES_8G+DOES_STACKS+SUPPORTS_MASKING;
	} //setup
	

   public static int getNNinterpolated(double xIdx, double yIdx, int width, int height, int[][] img)
   {
		//just round the coordinates
	   int xIdxInt = (int)(xIdx + 0.5);
	   int yIdxInt = (int)(yIdx + 0.5);
	   
	   //for safety reason: check range of the index
	   if(xIdxInt >= 0 && xIdxInt < width && yIdxInt >= 0 && yIdxInt < height)
	   {
		   return img[xIdxInt][yIdxInt];
	   }
	   return 0;
   }
	
   public static int getBiLinearinterpolaredValue(double xIdx, double yIdx, int width, int height, int[][] img)
   {
	//TODO for exercise 2
	return 0;	   
   }
	
   
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();				
		
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
                          
        //let user input the target scale factor between [0.1;10.0]
        double scaleFactor = 0.65;
        
        //let user input the desired scale factor
        //TODO for exercise: restrict to useful scale factor - good relative to max. processable image dimension
        GenericDialog gd = new GenericDialog("User Input for plugin");
        gd.addNumericField("Enter Scale factor", scaleFactor, 2);
        gd.showDialog();
        
        //if user has NOT cancelled the dialog
        //then process the input scale factor
        if (!gd.wasCanceled())
        {
        	scaleFactor = gd.getNextNumber();
        }
        
        //first calculate the new size 
        int newWidth = (int)(width*scaleFactor + 0.5);
        int newHeight = (int)(height*scaleFactor + 0.5);
        
        int[][] resampledImage = new int[newWidth][newHeight];
        
        //better: calculate improved scale factor for x and y direction
        double scaleX = (newWidth - 1.0)/(width - 1.0);
        double scaleY = (newHeight - 1.0)/(height - 1.0);

        
        //now fill the result image     
       for (int xIdx = 0; xIdx < newWidth; xIdx++)
       {
    	   for (int yIdx = 0; yIdx < newHeight; yIdx++)
    	   {
    		   //calculate new index position, might be something like img(3.14, 5.98) 
    		   //==> NN interpolation we get rounded indices, NN(3.14,5.98) == img(3,6)
    		   
    		   //Version 1 - inaccurate usage of scale factor
    		   //double xIdxDouble = xIdx/scaleFactor;
    		   //double yIdxDouble = yIdx/scaleFactor;
    		   
    		   double xIdxDouble = xIdx/scaleX;
    		   double yIdxDouble = yIdx/scaleY;
    		   
    		   
    		   int nnInterpolatedValue = getNNinterpolated(xIdxDouble, yIdxDouble, width, width, inDataArrInt);
    		   resampledImage[xIdx][yIdx] = nnInterpolatedValue;
    	   }
       }
        
        ImageJUtility.showNewImage(resampledImage, newWidth, newHeight, "resampled to scale factor xscale = " + scaleX + " yscale = " + scaleY);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class Resample_

