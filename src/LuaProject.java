import java.io.File;
import java.util.ArrayList;

public class LuaProject {
    private File luaProject;

    public LuaProject(File luaProject){
        this.luaProject = luaProject;

        loadFiles(this.luaProject);
    }

    public void loadFiles(File f){
        File[] files = f.listFiles();
        if(files != null){
            for (File file: files){
                if(file.isDirectory()){
                    loadFiles(file);
                }else if (file.isFile() && Utils.getFileExtension(file).equals("lua")){
                    LuaFile luaFile = new LuaFile(file);
                    luaFile.printFunctionName();
                }
            }
        }else{
            LuaFile luaFile = new LuaFile(f);
            luaFile.printFunctionName();
        }
    }
}
