import java.io.File;
import java.util.ArrayList;

public class LuaProject {
    private File luaProject;
    private boolean containsLuaFile;
    private String output;

    public LuaProject(File luaProject){
        this.luaProject = luaProject;
        this.containsLuaFile = false;
        this.output = "";

        loadFiles(this.luaProject);
    }

    public boolean isContainsLuaFile(){
        return containsLuaFile;
    }

    public String getOutput(){
        return output;
    }

    public void loadFiles(File f){
        File[] files = f.listFiles();
        if(files != null){
            for (File file: files){
                if(file.isDirectory()){
                    loadFiles(file);
                }else if (file.isFile() && Utils.getFileExtension(file).equals("lua")){
                    LuaFile luaFile = new LuaFile(file);
                    this.containsLuaFile = true;
                    this.output += luaFile.generateLuaDoc();
                }
            }
        }else if (Utils.getFileExtension(f).equals("lua")){
            LuaFile luaFile = new LuaFile(f);
            this.containsLuaFile = true;
            this.output += luaFile.generateLuaDoc();
        }
    }
}
