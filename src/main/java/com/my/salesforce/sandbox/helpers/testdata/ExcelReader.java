package com.my.salesforce.sandbox.helpers.testdata;

import com.creditdatamw.zerocell.Reader;
import com.my.salesforce.sandbox.properties.UserConfig;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import static com.my.salesforce.sandbox.Constants.*;

public class ExcelReader {
    public <T> List<T> read(String fileName, String sheetName, Class<T> c) {
        return load(fileName, sheetName, c).list();
    }

    private <T> Reader.@NotNull ReaderBuilder<T> load(String fileName, String sheetName, Class<T> c) {
        return Reader.of(c).from(new File(ROOT_DIRECTORY + UserConfig.getProperties().testData() + fileName)).sheet(sheetName).skipHeaderRow(true);
    }
}