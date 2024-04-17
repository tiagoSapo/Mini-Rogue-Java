
package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoadSaveFile {
            
    public static void fwrite(File file, Object o) throws IOException {
        
        ObjectOutputStream object_temp = null;

        try{

            object_temp = new ObjectOutputStream(new FileOutputStream(file));        
            object_temp.writeObject(o);

        } finally {
            if(object_temp != null)
                object_temp.close();
        }
    }

    public static Object fread(File file) throws IOException, ClassNotFoundException {
        
        ObjectInputStream object_temp = null;

        try{

            object_temp = new ObjectInputStream(new FileInputStream(file));        
            return object_temp.readObject();

        } finally {
            if(object_temp != null)
                object_temp.close();
        }
    }
    
}
