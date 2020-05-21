import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String OUTPUT_FILENAME = "luaDocOutput_SpikeScarlatti.lua";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Project path or lua file path : ");
        String path = sc.nextLine();

        LuaProject luaProject = new LuaProject(new File(path));
        if(!luaProject.isContainsLuaFile()){
            System.out.println("Project does not have .lua file !");
        }else{
            String output = luaProject.getOutput();

            System.out.println("Save output ? (Y/N)");
            String saveOutput = sc.next();

            String header = "--- Output generated from LuaDocCreator created by Spike Scarlatti. https://github.com/SpikeScarlatti/LuaDocCreator";
            System.out.println(header);
            System.out.println();
            System.out.println(output);

            if(saveOutput.toUpperCase().equals("Y")){
                TxtFile outputFile = new TxtFile(OUTPUT_FILENAME, path +"\\" + OUTPUT_FILENAME);
                outputFile.saveFile(header, output);
            }
        }
    }
}
