
public class ImageTransformationFilter {

	
	public static int[][] GetTransformedImage(int[][] inImg, int width, int height, int[] transferFunction) {
		int[][] returnImg = new int[width][height];
		
		//iterate over all pixels
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				int actVal = inImg[x][y];
				int newVal = transferFunction[actVal];
				returnImg[x][y] = newVal;
			}
		}
		
		return returnImg;
	}
	
	public static int[] GetInversionTF(int maxVal) {
		int[] transferFunction = new int[maxVal + 1];
		for (int idx = 0; idx <= maxVal; idx++) 
		{
			transferFunction[idx] = maxVal - idx;
		}
				
		return transferFunction;
	}
	
	public static int[] GetHistogram(int maxVal, int[][] inImg, int width, int height) {
		int[] histogram = new int[maxVal + 1];
				
		return histogram;
	}
	
	public static int[] GetGammaCorrTF(int maxVal, double gamma) {
		int[] transferFunction = new int[maxVal + 1];
		
		return transferFunction;
	}
	
	public static int[] GetBinaryThresholdTF(int maxVal, int thresholdVal, int FG_VAL, int BG_VAL) {
		int[] transferFunction = new int[maxVal + 1];
		
		for (int idx = 0; idx <= maxVal; idx++) 
		{
			if (idx >= thresholdVal)
			{
				transferFunction[idx] = FG_VAL;
			}
			else
			{
				transferFunction[idx] = BG_VAL;
			}
		}
				
		return transferFunction;
	}
	
	public static int[] GetHistogramEqualizationTF(int maxVal, int[][] inImg, int width, int height) {
		int[] returnTF = new int[maxVal + 1];
		
		return returnTF;
	}
	
}
