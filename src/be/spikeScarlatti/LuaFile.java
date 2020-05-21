package be.spikeScarlatti;

import java.io.*;
import java.util.ArrayList;

public class LuaFile {
    private File file;
    private BufferedReader reader;

    public LuaFile(File file){
        this.file = file;
    }

    public String generateLuaDoc(){
        StringBuilder output = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();

            ArrayList<String> stringBuffer = new ArrayList<>();
            while (line != null) {
                stringBuffer.add(line);
                if(line.startsWith("function ") && line.endsWith(")")){
                    output.append(processFunction(stringBuffer));
                    stringBuffer.clear();
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException ignored) {
        }

        return output.toString();
    }

    private String processFunction(ArrayList<String> stringBuffer){
        StringBuilder output = new StringBuilder();
        int linesCount = stringBuffer.size();

        int iLine = linesCount - 2;
        String currentLine = stringBuffer.get(iLine);

        int startDocLine = 0;
        while(iLine > 0 && currentLine.startsWith("---")){
            startDocLine = iLine;
            iLine--;
            currentLine = stringBuffer.get(iLine);
        }

        if (iLine == linesCount - 2){
            output.append(stringBuffer.get(linesCount - 1)).append(" end\n\n");
        }else{
            for(int iDocLine = startDocLine; iDocLine < linesCount; iDocLine++){
                if (iDocLine == linesCount - 1){
                    output.append(stringBuffer.get(iDocLine)).append(" end\n");
                }else{
                    output.append(stringBuffer.get(iDocLine));
                }

                output.append("\n");
            }
        }

        return output.toString();
    }

    public int getParamsCount(String line){
        if(line.contains(",")){
            return line.split(",").length;
        }

        return 0;
    }
}
