
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;


public class BinaryThreshold_ implements PlugInFilter {
  
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
        int threshVal = 200; //TODO: Let user input the parameters
        int FG_val = 255;
        int BG_val = 0;
        
        //get transfer function 
        int[] threshTFarr = ImageTransformationFilter.GetBinaryThresholdTF(maxVal, threshVal, FG_val, BG_val);
        int[][] segmentedImage = ImageTransformationFilter.GetTransformedImage(inDataArrInt, width, height, threshTFarr);
                          
        ImageJUtility.showNewImage(segmentedImage, width, height, "segmented image, thresh= " + threshVal);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class BinaryThreshold_

