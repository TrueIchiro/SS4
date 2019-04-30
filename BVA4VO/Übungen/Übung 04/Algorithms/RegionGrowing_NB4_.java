import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

import java.awt.*;
import java.util.Stack;
import java.util.Vector;

import ij.gui.GenericDialog;
import ij.gui.PointRoi;


public class RegionGrowing_NB4_ implements PlugInFilter {
  
	//sets the colour values for the pixels
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
	
	public void run(ImageProcessor ip) {
		byte[] pixels = (byte[])ip.getPixels();
		int width = ip.getWidth();
		int height = ip.getHeight();
		
		int lowerThresh = 100;
		int upperThresh = 200;
		
			
        int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height);
        
        //pre-defined start position
        //int startX = 235;
        //int startY = 173;
        
        GenericDialog gd = new GenericDialog("User Input for region growing");
        gd.addNumericField("Enter lower Thresh", lowerThresh, 0);
        gd.addNumericField("Enter upper Thresh", upperThresh, 0);
        gd.showDialog();
        
        //if user has NOT cancelled the dialog
        //then process the input scale factor
        if (!gd.wasCanceled())
        {
        	lowerThresh = (int)gd.getNextNumber();
        	upperThresh = (int)gd.getNextNumber(); 
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
        processingStack.addAll(seedCandidateVect);
        
        while (!processingStack.empty()) {
        	//take first element of stack
        	Point currPos = processingStack.pop();
        	
        	//check if unprocessed and if value in interval threshold range
        	if (segmentedImg[currPos.x][currPos.y] == UNPROCESSED_VAL) {
        		int actVal = inDataArrInt[currPos.x][currPos.y];
        		
        		if (actVal >= lowerThresh && actVal <= upperThresh) {
        			segmentedImg[currPos.x][currPos.y] = FG_VAL;

        			//now add all neighbours to get processed
        			//==> TODO add N4 for exercise
        			for (int xOffset = -1; xOffset <= 1; xOffset++) {
        				
        				for (int yOffset = -1; yOffset <= 1; yOffset++) {
        					
        					int nbX = currPos.x + xOffset;
        					int nbY = currPos.y + yOffset;
        					
        					if ((xOffset == -1 && yOffset == -1) ||
        						(xOffset == -1 && yOffset == 1) ||
        						(xOffset == 1 && yOffset == -1) ||
        						(xOffset == 1 && yOffset == -1)) { continue;  }     					
        					//check range of coordinates
        					if (nbX >= 0 && nbX < width && nbY >= 0 && nbY < height) {
        						
        						if (segmentedImg[nbX][nbY] == UNPROCESSED_VAL) {
        							//add to processing stack
        							processingStack.add(new Point(nbX, nbY));
        						}
        					
        					} //range check
        				
        				} //OffsetY
        			
        			} //OffsetX
        			//if FG
        		} else {
        			segmentedImg[currPos.x][currPos.y] = BG_VAL;
        		} //else BG
        	
        	} //unprocessed
        
        } //end of while
        
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
	
} //class RegionGrow_NB4_