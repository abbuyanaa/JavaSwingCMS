
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Myfunc {

    public ImageIcon resizePic(String picPath, byte[] BLOBpic, int width, int height) {
        ImageIcon myImg;

        if (picPath != null) {
            myImg = new ImageIcon(picPath);
        } else {
            myImg = new ImageIcon(BLOBpic);
        }
        Image img = myImg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon myPicture = new ImageIcon(img);
        return myPicture;
    }

    public String browseImage(JLabel lbl) {
        String path = null;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        // File extension
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
        file.addChoosableFileFilter(fileFilter);

        int fileState = file.showSaveDialog(null);

        if (fileState == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();

            lbl.setIcon(resizePic(path, null, lbl.getWidth(), lbl.getHeight()));
        } else if (fileState == JFileChooser.CANCEL_OPTION) {
            System.out.println("No Image Selected");
        }
        return path;
    }
}
