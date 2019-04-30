import ij.*; import ij.plugin.filter.PlugInFilter; import ij.process.*;
import java.awt.*; import java.util.Arrays; import java.util.stream.DoubleStream;
import ij.gui.GenericDialog;

public class Gauss_ implements PlugInFilter {
	
public int setup(String arg, ImagePlus imp) { 
	
	if (arg.equals("about")) { 
		showAbout(); return DONE; 
		} return DOES_8G + DOES_STACKS + SUPPORTS_MASKING; 
		
} // setup
public void run(ImageProcessor ip) { 
	byte[] pixels = (byte[]) ip.getPixels(); 
	int width = ip.getWidth(); 
	int height = ip.getHeight(); 
	int tgtRadius = 4; // request user input!!!
	
GenericDialog gd = new GenericDialog("User Inut for mean filtering"); 
gd.addNumericField("Geben Sie bitte den gewünschten radius ein ein:", tgtRadius, 0); 
gd.showDialog();

// if user has NOT canceled the dialog... // ...then process the input scale factor 
if (!gd.wasCanceled()) 
{ tgtRadius = (int) gd.getNextNumber(); 
}
// input image 
int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width, height); // convert to double 
double[][] inDataArrDouble = ImageJUtility.convertToDoubleArr2D(inDataArrInt, width, height);

// 3.1a 

// calculate the filter kernel (yellow filter mask in lecture slides) 
double standardDerivation = this .calculateSD( Arrays.stream(inDataArrDouble).flatMapToDouble(Arrays::stream).toArray()); 
double[][] gaussKernel = this.getGaussMask(tgtRadius, standardDerivation);

// get convolved image 
double[][] filteredImg = ConvolutionFilter.ConvolveDoubleNorm(inDataArrDouble, width, height, gaussKernel, tgtRadius);
ImageJUtility.showNewImage(filteredImg, width, height, "gauss with kernel r=" + tgtRadius);
// 3.1b // Convert kernel to 8bit image 
DoubleStream stream = Arrays.stream(gaussKernel).flatMapToDouble(Arrays::stream); 
double max = stream.max().getAsDouble(); 
stream = Arrays.stream(gaussKernel).flatMapToDouble(Arrays::stream); 
double min = stream.min().getAsDouble(); 
double oldRange = (max - min); 
double newRange = (255 - 0);
int[][] graphicalKernel = new int[gaussKernel.length][gaussKernel[0].length];

for (int i = 0; i < graphicalKernel.length; i++) {
	for (int j = 0; j < graphicalKernel[i].length; j++) {
			double newValue = (((gaussKernel[i][j] - min) * newRange) / oldRange) + 0;
			graphicalKernel[i][j] = (int) newValue;
	}
}

ImageJUtility.showNewImage(graphicalKernel, gaussKernel.length,
gaussKernel.length, "kernel r=" + tgtRadius);
// 3.1c
double[][] differenceImg =
new double[inDataArrInt.length][inDataArrInt[0].length];
for (int i = 0; i < differenceImg.length; i++) {
for (int j = 0; j < differenceImg[i].length; j++) {
differenceImg[i][j] = inDataArrDouble[i][j] - filteredImg[i][j];
}
}
ImageJUtility.showNewImage(differenceImg, width, height, "difference r=" +
tgtRadius);
} // run
private double[][] getGaussMask(int tgtRadius, double sigma) {
int size = 2 * tgtRadius + 1;
double[][] kernelImg = new double[size][size];
double calcEuler = 1 / (2 * Math.PI * Math.pow(sigma, 2));
double sumTotal = 0;
// assign coefficient to kernel image
for (int x = -tgtRadius; x <= tgtRadius; x++) {
for (int y = -tgtRadius; y <= tgtRadius; y++) {
double distance = (Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2));
kernelImg[tgtRadius + x][tgtRadius + y] = calcEuler * Math.exp(-distance);
sumTotal += kernelImg[tgtRadius + x][tgtRadius + y];
}
}
for (int y = 0; y < kernelImg.length; y++) {
for (int x = 0; x < kernelImg.length; x++) {
kernelImg[x][y] = kernelImg[x][y] * (1.0 / sumTotal);
}
}
return kernelImg;
}
private double calculateSD(double numArray[]) {
double sum = 0.0, standardDeviation = 0.0;
int length = numArray.length;
for (double num : numArray) {
sum += num;
}
double mean = sum / length;
for (double num : numArray) {
standardDeviation += Math.pow(num - mean, 2);
}
return Math.sqrt(standardDeviation / length);
}
void showAbout() {
IJ.showMessage("About Template_...", "this is a PluginFilter template\n");
} // showAbout
} // class Mean_