// package gui;


// import java.awt.AWTException;
// import java.awt.Color;
// import java.awt.Rectangle;
// import java.awt.Robot;
// import java.awt.image.BufferedImage;

// public class Test {
//     // Method to get color at specified pixel
//     private Color getColorWithPixel(int x, int y) {
//         try {
//             // Create a Robot instance
//             Robot robot = new Robot();

//             // Capture the screen at the specified pixel
//             BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, 1, 1));

//             // Get the color of the pixel
//             return new Color(image.getRGB(0, 0));
//         } catch (AWTException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     public static void main(String[] args) {
//         Test colorGetter = new Test();

//         // Example: Get color at position (100, 100)
//         int x = 100;
//         int y = 100;
//         Color color = colorGetter.getColorWithPixel(x, y);

//         if (color != null) {
//             // Print the RGB values
//             System.out.println("Color at position (" + x + ", " + y + "):");
//             System.out.println("Red: " + color.getRed());
//             System.out.println("Green: " + color.getGreen());
//             System.out.println("Blue: " + color.getBlue());
//         } else {
//             System.out.println("Failed to get color at position (" + x + ", " + y + ")");
//         }
//     }
// }
