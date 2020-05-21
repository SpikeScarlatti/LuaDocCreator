package be.spikeScarlatti;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TxtFile {
    private String saveDirectory;
    private File txtFile;

    public TxtFile(String filename, String saveDirectory) {
        this.saveDirectory = saveDirectory;
        this.txtFile = new File(filename);
    }

    public String getSaveDirectory() {
        return saveDirectory;
    }

    public void saveFile(String header, String outputData){
        BufferedWriter bw = null;
        try{
            bw = Files.newBufferedWriter(Paths.get(saveDirectory));

            bw.append(header);
            bw.newLine();
            bw.newLine();
            bw.append(outputData);
            bw.newLine();
        }catch(IOException ignored){
        }finally{
            try{
                bw.close();
                System.out.println("Output saved at " +saveDirectory);
            }catch (IOException ignored){
            }
        }
    }
}
