import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import ij.gui.GenericDialog;
public class Median_ implements PlugInFilter {
public int setup(String arg, ImagePlus imp) {
if (arg.equals("about")) {
showAbout();
return DONE;
}
return DOES_8G + DOES_STACKS + SUPPORTS_MASKING;
} // setup
public void run(ImageProcessor ip) {
byte[] pixels = (byte[]) ip.getPixels();
int width = ip.getWidth();
int height = ip.getHeight();
int tgtRadius = 4; // request user input!!!
GenericDialog gd = new GenericDialog("User Inut for median filtering");
gd.addNumericField("Geben Sie bitte den gewünschten radius ein ein:",
tgtRadius, 0);
gd.showDialog();
// if user has NOT canceled the dialog...
// ...then process the input scale factor
if (!gd.wasCanceled()) {
tgtRadius = (int) gd.getNextNumber();
}
// input image
int[][] inDataArrInt = ImageJUtility.convertFrom1DByteArr(pixels, width,
height);
int[][] filteredImage = new int[inDataArrInt.length][inDataArrInt[0].length];
// calculate for each hotspot pixel in radius the median value
for (int x = 0; x < width; x++) {
for (int y = 0; y < height; y++) {
ArrayList<Integer> medianValues = new ArrayList<>();
for (int xOffset = -tgtRadius; xOffset <= tgtRadius; xOffset++)
{
for (int yOffset = -tgtRadius; yOffset <= tgtRadius;
yOffset++) {
int nbX = x + xOffset;
int nbY = y + yOffset;
// check range at borders!!
if (nbX >= 0 && nbX < width && nbY >= 0 && nbY <
height) {
medianValues.add(inDataArrInt[nbX][nbY]);
}
}
}
Collections.sort(medianValues);
int middle = (medianValues.get(medianValues.size() / 2) +
medianValues.get(medianValues.size() / 2 - 1))
/ 2;
filteredImage[x][y] = middle;
}
}
ImageJUtility.showNewImage(filteredImage, width, height, "median r=" +
tgtRadius);
// Checkerboard
// iterate over all pixels to get the kernel centred via hot-spot
int[][] checkerBoardImage = new
int[inDataArrInt.length][inDataArrInt[0].length];
int boarderSize = 64;
boolean isXFirst = true;
boolean isYfirst = true;
for (int x = 0; x < width; x++) {
if (((x + 1) % boarderSize) == 0) {
isXFirst = !isXFirst;
}
isYfirst = isXFirst;
for (int y = 0; y < height; y++) {
if (((y + 1) % boarderSize) == 0) {
isYfirst = !isYfirst;
}
if (isYfirst) {
checkerBoardImage[x][y] = inDataArrInt[x][y];
} else {
checkerBoardImage[x][y] = filteredImage[x][y];
}
}
}
ImageJUtility.showNewImage(checkerBoardImage, width, height,
"checkerBoardResult r=" + tgtRadius);
} // run

void showAbout() {
IJ.showMessage("About Template_...", "this is a PluginFilter template\n");
} // showAbout
}