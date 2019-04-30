import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;
import java.util.Stack;
import java.util.Vector;

import ij.gui.GenericDialog;
import ij.gui.PointRoi;


public class RegionGrowing_Recursive_ implements PlugInFilter {
  
   public static int FG_VAL = 255;
   public static int BG_VAL = 0;
   public static int UNPROCESSED_VAL = -1;
	
   ImagePlus imp = null;
   
   public int setup(String arg, ImagePlus imp) {
		if (arg.equals("about"))
			{showAbout(); return DONE;}
		
		this.imp = imp;
		
		return DOES_8G+DOES_STACKS+SUPPORTS_MASKING + ROI_REQUIRED;
	} //setup
	
    Vector<Point> GetSeedPoints(ImagePlus imagePlus) {
    	Vector<Point> seedPositions = new Vector<Point>();
    	PointRoi pr = (PointRoi) imagePlus.getRoi();
    	int[] xPositions = pr.getXCoordinates();
    	int[] yPositions = pr.getYCoordinates();
    	Rectangle boundingBox = pr.getBounds();
    			
    	//finally fill
    	for(int i = 0; i < xPositions.length; i++) {
    		seedPositions.add(
    				new Point(xPositions[i] + boundingBox.x, 
    						yPositions[i] + boundingBox.y));
    	}
    	
    	return seedPositions;
    }
    
    /*
     * Flood-fill (node, target-color, replacement-color):
 1. If target-color is equal to replacement-color, return.
 2. If the color of node is not equal to target-color, return.
 3. Set the color of node to replacement-color.
 4. Perform Flood-fill (one step to the south of node, target-color, replacement-color).
    Perform Flood-fill (one step to the north of node, target-color, replacement-color).
    Perform Flood-fill (one step to the west of node, target-color, replacement-color).
    Perform Flood-fill (one step to the east of node, target-color, replacement-color).
 5. Return.
     * 
     */
    
    private void floodfill_recursive(
    		int[][] segmentedImage, int[][] inDataArrInt, 
    		int xPos, int yPos, 
    		int width, int height,
    		int lowerThresh, int upperThresh) {
    	//first check if the pixel is within the boundaries
    	if (xPos < width && xPos >= 0 && yPos < height && yPos >= 0) {
    		
    		//check if Pixel is unprocessed
    		if (segmentedImage[xPos][yPos] == UNPROCESSED_VAL) {
    			
    			//actual colour of the current pixel
    			int actVal = inDataArrInt[xPos][yPos];
    			
    			if (actVal >= lowerThresh && actVal <= upperThresh) {
        			segmentedImage[xPos][yPos] = FG_VAL;
        			
    			} else {
    				segmentedImage[xPos][yPos] = BG_VAL;
    			}
    			
    			//now the recursion magic happens
    			//one step to the south
    			floodfill_recursive(segmentedImage, inDataArrInt,
    					xPos, yPos - 1, width, height, lowerThresh, upperThresh);
    			//north
    			floodfill_recursive(segmentedImage, inDataArrInt,
    					xPos, yPos + 1, width, height, lowerThresh, upperThresh);
    			//west
    			floodfill_recursive(segmentedImage, inDataArrInt,
    					xPos - 1, yPos, width, height, lowerThresh, upperThresh);
    			//east
    			floodfill_recursive(segmentedImage, inDataArrInt,
    					xPos + 1, yPos, width, height, lowerThresh, upperThresh);
    		}
    		
    	}
    }
	
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();
		
		int lowerThresh = 0;
		int upperThresh = 0;
		
		double userThresh = 0.0;
		
			
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        //pre-defined start position
        //int startX = 235;
        //int startY = 173;
        
        GenericDialog gd = new GenericDialog("User Input for region growing");
        gd.addSlider("Threshold", 0.0, 1.0, 0.5);
        gd.showDialog();
        
        //if user has NOT cancelled the dialog
        //then process the input scale factor
        if (!gd.wasCanceled())
        {
        	userThresh = gd.getNextNumber();
        	}
        
        //TODO: Let user enter the interval and the seed points
                          
        int[][] segmentedImg = new int[width][height];
        int fgCount = 0; //count the number of segmented pixels
        
        for(int x = 0; x < width; x++) {
        	for(int y = 0; y < height; y++) {
            	segmentedImg[x][y] = UNPROCESSED_VAL;
            }
        }
        
        
        //Vector<Point> seedPointCandidates = new Vector<Point>();
        
        Stack<Point> processingStack = new Stack<Point>();
        //iterative
        //processingStack.add(new Point(startX, startY));
        
        Vector<Point> seedCandidateVect = GetSeedPoints(imp);
        
        userThresh *= 255;
    	
    	lowerThresh = inDataArrInt[seedCandidateVect.get(0).x][seedCandidateVect.get(0).y] - (int)userThresh;
		upperThresh = inDataArrInt[seedCandidateVect.get(0).x][seedCandidateVect.get(0).y] + (int)userThresh;
    	
		floodfill_recursive(segmentedImg, inDataArrInt, 0, 0, width, height, 
				lowerThresh, upperThresh);
        
        //finally clean up: all remaining UNPROCESSED_VAL pixels get assigned a background value BG_VAL
        for (int x = 0; x < width; x++)  {
        	for (int y = 0; y < height; y++) {
        		if (segmentedImg[x][y] == UNPROCESSED_VAL) {
        			segmentedImg[x][y] = BG_VAL;
        		}
        	}
        }
        
        
        ImageJUtility.showNewImage(segmentedImg, width, height,
        		"RG in [" + lowerThresh + ";" + upperThresh +"]" + " num of FG=" + fgCount);
                        
	} //run

	void showAbout() {
		IJ.showMessage("About Template_...",
			"this is a PluginFilter template\n");
	} //showAbout
	
} //class RegionGrow_Recursive_