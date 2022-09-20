/**
 * Share exporter that persists visual shares as png images to disk.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Helper class that transforms provided visual shares back to png format and persists them on
 * disk.
 */
public class VisualShareExporter {

  /**
   * Transforms provided visual shares back to png format and persists them on disk.
   *
   * @param visualShare as the boosted, graphic version of a compact share.
   * @param path        as the location on file system where the share is stored as png file.
   */
  public static void exportVisualShare(boolean[][] visualShare, String path) {

    // set final dimensions
    BufferedImage bufferedImage =
        new BufferedImage(visualShare.length, visualShare[0].length, BufferedImage.TYPE_INT_RGB);

    // iterate over share ( 2D array ) and set pixel by pixel in target buffered image
    for (int x = 0; x < visualShare.length; x++) {
      for (int y = 0; y < visualShare[0].length; y++) {
        int level = (visualShare[x][y] ? 255 : 0);
        bufferedImage.setRGB(x, y, new Color(level, level, level).getRGB());
      }
    }

    // Finally write Buffered Image to file
    try {
      File outputfile = new File(path);
      ImageIO.write(bufferedImage, "png", outputfile);
    } catch (IOException e) {
      System.out.println("Export Failed!");
    }
  }
}

