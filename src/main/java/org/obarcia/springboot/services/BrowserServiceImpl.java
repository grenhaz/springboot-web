package org.obarcia.springboot.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletContext;
import org.obarcia.springboot.models.browser.FileBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Imoplementación del servicio del explorador de archivos.
 * 
 * @author obarcia
 */
@Service
public class BrowserServiceImpl implements BrowserService
{
    /**
     * Instancia del contexto.
     */
    @Autowired
    ServletContext context; 
    
    /**
     * Comprador de ficheros.
     */
    private final Comparator<FileBrowser> fileComparator = new Comparator<FileBrowser>() {
            @Override
            public int compare(FileBrowser o1, FileBrowser o2) {
                if (!o1.getIsFile() && o2.getIsFile()) {
                    return -1;
                } else if (o1.getIsFile() && !o2.getIsFile()) {
                    return 1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
    };
    
    @Override
    public List<String> getAvatars()
    {
        List<String> avatars = new ArrayList<>();
        avatars.add("anonymous.png");
        
        // Leer los posibles avatares
        File rootDir = new File(context.getRealPath("/WEB-INF/data/avatars/"));
        if (rootDir.isDirectory()) {
            File[] files = rootDir.listFiles();
            for (File f: files) {
                if (f.isFile() && !f.getName().equals("anonymous.png")) {
                    avatars.add(f.getName());
                }
            }
        }
        
        return avatars;
    }
    @Override
    public List<FileBrowser> getFiles(String path, String type)
    {
        List<FileBrowser> files = new ArrayList<>();
        
        // Leer los posibles avatares
        File rootDir = new File(context.getRealPath("/WEB-INF/data/articles/" + path));
        if (rootDir.isDirectory()) {
            File[] fs = rootDir.listFiles();
            // Back
            if (!path.isEmpty()) {
                int index = path.lastIndexOf(path, path.length() - 2);
                FileBrowser back = new FileBrowser();
                back.setIsFile(false);
                back.setFilename(index >= 0 ? path.substring(0, index) : "");
                back.setName("..");
                files.add(back);
            }
            
            for (File f: fs) {
                FileBrowser fb = new FileBrowser();
                fb.setIsFile(!f.isDirectory());
                if (f.isFile()) {
                    fb.setUrl("/data/articles/" + path + f.getName());
                    fb.setFilename(path + f.getName());
                } else {
                    fb.setFilename(path + f.getName() + "/");
                }
                fb.setName(f.getName());
                if ((!type.equals("image") && !type.equals("bimage")) || fb.getIsImage() || !fb.getIsFile()) {
                    files.add(fb);
                }
            }
        }
        
        // Ordenar los ficheros
        Collections.sort(files, fileComparator);

        return files;
    }
}
