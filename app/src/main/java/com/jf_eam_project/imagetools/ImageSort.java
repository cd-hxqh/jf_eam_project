package com.jf_eam_project.imagetools;

import java.util.Comparator;

public class ImageSort  implements Comparator<ImageSort>{
	public String path;
	public long lastModified;
        public int compare(ImageSort file1, ImageSort file2) {  
            if(file1.lastModified > file2.lastModified)  
            {  
                return -1;  
            }else  
            {  
                return 1;  
            }  
        }
}
