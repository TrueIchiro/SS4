
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class Invert1_ implements PlugInFilter {
  
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		return DOES_8G+DOES_STACKS+SUPPORTS_MASKING;
	} //setup
	

	
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();
		
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        int maxVal = 255;
        
        int[][] invertedImage = new int[width][height];
        //iterate over all pixels and invert the values
        for (int x = 0; x < width; x++)
        {
        	for (int y = 0; y < height; y++)
            {
            	invertedImage[x][y] = maxVal-inDataArrInt[x][y];
            }
        }
        
                          
        ImageJUtility.showNewImage(invertedImage, width, height, "Inverted image");
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class Invert1_

