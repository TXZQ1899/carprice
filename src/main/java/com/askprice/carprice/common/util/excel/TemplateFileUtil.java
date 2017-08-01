package com.askprice.carprice.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TemplateFileUtil {

    public static InputStream getTemplates(String templateName) throws FileNotFoundException {
    	InputStream is = new FileInputStream(new File("/usr/local/output/templates/" + templateName));
    	return is;
    }
}
