package com.askprice.carprice.common.util.excel;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TemplateFileUtil {

    public static FileInputStream getTemplates(String tempName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile("classpath:templates/"+tempName));
    }
}
