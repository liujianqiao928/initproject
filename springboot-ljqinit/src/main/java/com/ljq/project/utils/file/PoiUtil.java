package com.ljq.project.utils.file;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Wang Zhe on 2022/8/11.
 */
public class PoiUtil {
    private static final String XLS = "xls";
    public static Workbook getWorkbook(String filePath){
        try {
            return WorkbookFactory.create(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

}
