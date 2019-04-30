
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class Rescale_ implements PlugInFilter {
  
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
	   /*
	   double R1 = ((x2 – xIdx)/(x2 – x1))*Q11 + ((xIdx – x1)/(x2 – x1))*Q21;
	   double R2 = ((x2 – xIdx)/(x2 – x1))*Q12 + ((xIdx – x1)/(x2 – x1))*Q22;
	   
	   double P = ((y2 – yIdx)/(y2 – y1))*R1 + ((yIdx – y1)/(y2 – y1))*R2;
	   */
	   return 0;	   
   }
	
   
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();				
		
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
                          
        //let user input the desired scale factor
        //TODO for exercise: restrict to useful scale factor - good relative to max. processable image dimension
        GenericDialog gd = new GenericDialog("User Input for plugin");
        gd.addNumericField("Enter a target-width:", width, 0);
        gd.addNumericField("Enter a target-height: ", height, 0);
        gd.showDialog();
        
        int newWidth = width;
        int newHeight = height;
        
        //if user has NOT cancelled the dialog
        //then process the input scale factor
        if (!gd.wasCanceled())
        {
        	newWidth = (int) gd.getNextNumber();
        	newHeight = (int) gd.getNextNumber();
        }
        

        int[][] resampledImage = new int[newWidth][newHeight];
        
        //better: calculate improved scale factor for x and y direction
        double scaleX = (newWidth - 1.0)/(width - 1.0);
        double scaleY = (newHeight - 1.0)/(height - 1.0);

        
        //now fill the result image     
       for (int xIdx = 0; xIdx < newWidth; xIdx++)
       {
    	   for (int yIdx = 0; yIdx < newHeight; yIdx++)
    	   {
   		   
    		   double xIdxDouble = xIdx/scaleX;
    		   double yIdxDouble = yIdx/scaleY;
    		   
    		   
    		   int nnInterpolatedValue = getNNinterpolated(xIdxDouble, yIdxDouble, width, height, inDataArrInt);
    		   //int bInterpolatedValue = getBiLinearinterpolaredValue(xIdxDouble, yIdxDouble, width, height, inDataArrInt);

    		   resampledImage[xIdx][yIdx] = nnInterpolatedValue;
    		   //resampledImage[xIdx][yIdx] = bInterpolatedValue;
    	   }
       }
        
        ImageJUtility.showNewImage(resampledImage, newWidth, newHeight, "Scaled image to tgtWidth = " + scaleX + " tgtHeight = " + scaleY);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a Rescale Plugin\n");
	} //showAbout
	
} //class Rescale_

