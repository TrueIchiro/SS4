
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.DialogListener;
import ij.gui.GenericDialog;


public class Morphing_ implements PlugInFilter {
  
	ImagePlus imagePlus = null;

	
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		imagePlus = imp;
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
	
   
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();				
		
        int[][] inDataArrInt1 = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        int[][] inDataArrInt2 = new int[5][5]; 
        
        double alphaMinVal = 0.0;
        double alphaMaxVal = 1.0;
        double alphaVal = 0.5; //start value

        GenericDialog gd = new GenericDialog("Morphing");
		gd.addSlider("alpha val", alphaMinVal, alphaMaxVal, alphaVal); 	
		gd.showDialog();
		if(gd.wasCanceled()) {  
		   return;
		} //if
				
		alphaVal = gd.getNextNumber();
		
        int[][] morphedImgArr = new int[width][height];

		            
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				
			}
		}
		
		ImageJUtility.showNewImage(morphedImgArr, width, height, "morphed image at alpha = " + alphaVal);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a Morphing plugin\n");
	} //showAbout
	
} //class Morphing_

