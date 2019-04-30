
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;

import ij.gui.GenericDialog;


public class HitOrMiss_ implements PlugInFilter {
	
	//sets the colours for the pixels
	public static int FG_VAL = 0;
	public static int BG_VAL = 255;
	public static int UNPROCESSED_VAL = -1;
  
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		return DOES_8G+DOES_STACKS+SUPPORTS_MASKING;
	} //setup
   
   //checks if the mask fits a certain area
	public boolean fitsMask(int[][] mask, int[][] dataArr, int posX, int posY, int width, int height) {
		//these are needed for the actual picture to be compared
		//since mask will always begin with 0
		int newX = posX;
		int newY = posY;
		
		for (int i = 0; i < mask.length; i++) {
			for (int j = 0; j < mask.length; j++) {
				//check if x and y are within the boundaries
				if (newX <= width - 1 && newY <= height - 1) {
					//they do not have the same value
					if (!(mask[i][j] == dataArr[newX][newY])) {
						return false;
					}
				} else { //the boundaries are reached
					return false;
				}
				newY++;
			}
			//newY has to be reset
			newX++;
			newY = posY;
		}
		
		return true;
	}

	public void run(ImageProcessor ip) {
		
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();
		int tgtRadius = 4;
				
		
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        //initialize empty array with the needed sizes
        int[][] newPic = new int[width][height];
        
        //initialize all points with UNPROCESSED_VAL
        for (int e = 0; e < width; e++) {
        	for (int f = 0; f < height; f++) {
        		newPic[e][f] = UNPROCESSED_VAL;
        	}
        }
        
        int size = 0;
        
        //let the user enter the size of detection
        GenericDialog gd = new GenericDialog("User Input for region growing");
        gd.addNumericField("Size of detection", size, 1);
        gd.showDialog();
        
        //if user has NOT cancelled the dialog
        //then process the input scale factor
        if (!gd.wasCanceled())
        {
        	size = (int)gd.getNextNumber();
        	}
        
        int[][] mask = new int[size + 2][size + 2];
        
        //initialize mask with the value
        for (int m = 0; m < mask.length; m++) {
        	for (int n = 0; n < mask.length; n++) {
        		//the outer line where everything needs to be background
        		if (m == 0 || n == 0 || m == mask.length - 1 || n == mask.length - 1) {
        			mask[m][n] = BG_VAL;
        		} else {
        		mask[m][n] = FG_VAL;
        		}
        	}
        }
        
        //now for the detection part
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j++) {
        		
        		//it came back positive!
        		if (fitsMask(mask, inDataArrInt, i, j, width, height)) {
        			//now colour the newPic with the FG_Colour
        			for (int b = i + 1; b < mask.length - 2 + i + 1; b++) {
        				for (int c = j + 1; c < mask.length - 2 + j + 1; c++) {
        					newPic[b][c] = FG_VAL;
        				}
        			}
        		}
        	}
        }
                          
        ImageJUtility.showNewImage(newPic, width, height, "Size of detection area: " + size);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class HitOrMiss_

