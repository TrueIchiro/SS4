
public class ConvolutionFilter {

	public static double[][] ConvolveDoubleNorm(double[][] inputImg, int width, int height, double[][] kernel, int radius, int numOfIterations) {
	  double[][] returnImg = inputImg;
	  for(int iterCount = 0; iterCount < numOfIterations; iterCount++) {
		  returnImg = ConvolutionFilter.ConvolveDoubleNorm(returnImg, width, height, kernel, radius);
	  }
	  
	  return returnImg;
	}
	
	public static double[][] ConvolveDoubleNorm(double[][] inputImg, int width, int height, double[][] kernel, int radius) {
		double[][] returnImg = new double[width][height];
		
		//ATTENTION: only for low-pass filters!!!
		
		//iterate over all pixels to get the kernel centred via hot-spot
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				//calculate one new value by applying several multiplications and additions
				double sum = 0;
				double sumOfCoefficients = 0;
				
				//iterate over filter kernel
				for (int xOffset = -radius; xOffset <= radius; xOffset++)
				{
					for (int yOffset = -radius; yOffset <= radius; yOffset++)
					{
						//calculate yellow neighbour of input image inside kernel mask
						int nbX = x + xOffset;
						int nbY = y + yOffset;
						
						if (nbX >= 0 && nbX < width && nbY >= 0 && nbY < height)
						{
							double imgVal = inputImg[nbX][nbY];
							double kernelCoefficient = kernel[xOffset + radius][yOffset + radius];
							
							sum += imgVal * kernelCoefficient;
							sumOfCoefficients += kernelCoefficient;
						}
					}
				}
				
				//correct sum according to coefficient normalization ==> tgt 1.0 sum
				double normFactor = 1.0 / sumOfCoefficients;
				
				//assign value in result image
				returnImg[x][y] = sum * normFactor;
			}
		}
		
		return returnImg;
	}
	
	public static double[][] ConvolveDouble(double[][] inputImg, int width, int height, double[][] kernel, int radius) {
		double[][] returnImg = new double[width][height];
		
		//iterate over all pixels to get the kernel centred via hot-spot
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				//calculate one new value by applying several multiplications and additions
				double sum = 0;
				
				//iterate over filter kernel
				for (int xOffset = -radius; xOffset <= radius; xOffset++)
				{
					for (int yOffset = -radius; yOffset <= radius; yOffset++)
					{
						//calculate yellow neighbour of input image inside kernel mask
						int nbX = x + xOffset;
						int nbY = y + yOffset;
						
						if (nbX >= 0 && nbX < width && nbY >= 0 && nbY < height)
						{
							double imgVal = inputImg[nbX][nbY];
							double kernelCoefficient = kernel[xOffset + radius][yOffset + radius];
							
							sum += imgVal * kernelCoefficient;
						}
					}
				}
				
				//assign value in result image
				returnImg[x][y] = sum;
			}
		}
		
		return returnImg;
	}
	
	public static double[][] GetMeanMask(int tgtRadius) {
		int size = 2 * tgtRadius + 1;
		double[][] kernelImg = new double[size][size];
		
		double coefficient = 1.0 / ((double) size * size);
		
		//assign coefficient to kernel image
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				kernelImg[x][y] = coefficient;
			}
		}
		
		return kernelImg;
	}
	
    public static double[][] GetGaussMask(int tgtRadius, double sigma) {
    	int size = 2 * tgtRadius + 1;
		double[][] kernelImg = new double[size][size];
						
		return kernelImg;
	}
    
    public static double[][] ApplySobelEdgeDetection(double[][] inputImg, int width, int height) {
    	double[][] returnImg = new double[width][height];
    	double[][] sobelV = new double[][]{{1.0, 0.0, -1.0}, {2.0, 0.0, -2.0}, {1.0, 0.0, -1.0}};
		double[][] sobelH = new double[][]{{1.0, 2.0, 1.0}, {0.0, 0.0, 0.0}, {-1.0, -2.0, -1.0}};
    	
		int radius = 1;
		double maxGradient = 0.0;
						
		return returnImg;
    }
	
	
}
