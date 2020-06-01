package org.obarcia.springboot.models;

/**
 * Fichero del explorador.
 * 
 * @author obarcia
 */
public class FileBrowser
{
    private String url;
    private String name;
    private String filename;
    private Boolean isFile = false;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String value)
    {
        url = value;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }
    public String getFilename()
    {
        return filename;
    }
    public void setFilename(String value)
    {
        filename = value;
    }
    public Boolean getIsFile()
    {
        return isFile;
    }
    public void setIsFile(Boolean value)
    {
        isFile = value;
    }
    public Boolean getIsImage()
    {
        if (name != null) {
            int index = name.lastIndexOf(".");
            if (index >= 0) {
                String extension = name.substring(index + 1);
                return (extension.equals("jpeg") 
                        || extension.equals("jpg")
                        || extension.equals("png")
                        || extension.equals("gif"));
            }
        }
        
        return false;
    }
}
