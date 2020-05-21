import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Project path or lua file path : ");
        String path = sc.nextLine();

        LuaProject luaProject = new LuaProject(new File(path));
        if(!luaProject.isContainsLuaFile()){
            System.out.println("Project does not have .lua file !");
        }

        System.out.println("Save output ? (Y/N)");

        String saveOutput = sc.next();
        String output = luaProject.getOutput();
        String header = "--- Output generated from LuaDocCreator created by Spike Scarlatti. https://github.com/SpikeScarlatti/LuaDocCreator";
        System.out.println(header);
        System.out.println();
        System.out.println(output);

        if(saveOutput.toUpperCase().equals("Y")){
            String filename = "luaDocOutput_SpikeScarlatti.lua";
            TxtFile outputFile = new TxtFile(filename, path +"\\" + filename);
            outputFile.saveFile(header, output);
        }
    }
}
