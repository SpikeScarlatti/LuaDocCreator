import java.io.*;
import java.util.ArrayList;

public class LuaFile {
    private File file;
    private BufferedReader reader;

    public LuaFile(File file){
        this.file = file;
    }

    public void printFunctionName(){
        ArrayList<String> stringBuffer = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();

            while (line != null) {
                stringBuffer.add(line);
                if(line.startsWith("function ") && line.endsWith(")")){
                    processFunction(stringBuffer);
                    stringBuffer.clear();
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processFunction(ArrayList<String> stringBuffer){
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
            startDocLine = linesCount - 1;
        }

        for(int iDocLine = startDocLine; iDocLine < linesCount; iDocLine++){
            if (iDocLine == linesCount - 1){
                System.out.print(stringBuffer.get(iDocLine) + "end\n\n");
            }else{
                System.out.println(stringBuffer.get(iDocLine));
            }
        }
    }

    public int getParamsCount(String line){
        if(line.contains(",")){
            return line.split(",").length;
        }

        return 0;
    }
}
