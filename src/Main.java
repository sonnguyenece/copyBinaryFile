import java.io.*;
import java.nio.channels.ScatteringByteChannel;

public class Main {
    public static void main(String[] args) {
        final String sourcePath = "SourceFolder";
        final String sourceTxt = "Source.txt";
        final String desPath = "DestinationFolder";
        final String txtContent = "Have a nice day!";

        File sourceFolder = new File(sourcePath);
        makeDir(sourceFolder);

        File sourceFileTxt = new File(sourceFolder.getPath() + "/" + sourceTxt);
        makeFileTxt(sourceFileTxt);

        if (sourceFileTxt.exists()) {
            System.out.println("File test's absolute path:" + sourceFileTxt.getAbsolutePath());
            writeContent(sourceFileTxt, txtContent);
            System.out.println("Source txt's size : " + sourceFileTxt.length());

            File desFolder = new File(desPath);
            makeDir(desFolder);
            File destinyFileTxt = new File(desPath + "/" + sourceTxt);

            if (!destinyFileTxt.exists()) {
                copyFileUsingStream(sourceFileTxt, destinyFileTxt);
                System.out.println("Destination txt's size : " + destinyFileTxt.length());
            } else {
                System.out.println("Destination File is exist");
            }
        } else {
            System.out.println("Source file isn't exist!");
        }
    }

    static void makeDir(File folder) {
        if (folder.exists()) return;
        else {
            folder.mkdirs();
        }
    }

    static void makeFileTxt(File sourceFileTxt) {
        if (sourceFileTxt.exists()) return;
        else {
            try {
                sourceFileTxt.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void writeContent(File sourceFileTxt, String txtContent) {
        FileWriter fileWriter = null;
        if (sourceFileTxt.length() == 0) {
            try {
                fileWriter = new FileWriter(sourceFileTxt);
                fileWriter.write(txtContent);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void copyFileUsingStream(File sourceFile, File desFile) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(sourceFile);
            os = new FileOutputStream(desFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            System.out.println("Copy completed!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
