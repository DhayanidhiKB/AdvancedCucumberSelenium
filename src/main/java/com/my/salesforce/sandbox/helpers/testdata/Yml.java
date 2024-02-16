package com.my.salesforce.sandbox.helpers.testdata;

import com.my.salesforce.sandbox.properties.UserConfig;
import org.jetbrains.annotations.NotNull;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;

import static com.my.salesforce.sandbox.Constants.*;

public class Yml {
    private Map<?, ?> fileContents;
    private final List<Map<?, ?>> arrayList = new ArrayList<>();

    // Read file
    public void load(final String fileName) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(ROOT_DIRECTORY + UserConfig.getProperties().testData() + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file:" + fileName + "," + "Please check the file path, name and format");
        }
        Yaml yaml = new Yaml();
        this.fileContents = yaml.load(inputStream);
    }

    // Read as Properties
    public String getValue(final @NotNull String key) {
        Map<?, ?> map = (Map<?, ?>) this.fileContents.get(key.split("\\.")[0]);
        return map.get(key.split("\\.")[1]).toString();
    }

    // Read as DataProvider
    @SuppressWarnings("unchecked")
    public @NotNull Iterator<Object[]> getValues(final String key) {
        ArrayList<Map<?, ?>> arrList = (ArrayList<Map<?, ?>>) fileContents.get(key);
        Collection<Object[]> dp = new ArrayList<>();
        for (Map<?, ?> map : arrList) {
            dp.add(new Object[]{map});
        }
        return dp.iterator();
    }

    // // Read multiple documents
    public List<Map<?, ?>> loadAll(final String fileName) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(ROOT_DIRECTORY + UserConfig.getProperties().testData() + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file:" + fileName + "," + "Please check the file path, name and format");
        }
        Yaml yaml = new Yaml();

        for (Object o : yaml.loadAll(inputStream)) {
            Map<?, ?> map = (LinkedHashMap<?, ?>) o;
            arrayList.add(map);
        }
        return arrayList;
    }

    // Read multiple documents
    public Iterator<Object[]> getValues() {
        Collection<Object[]> dp = new ArrayList<>();
        for (Map<?, ?> map : arrayList) {
            dp.add(new Object[]{map});
        }
        return dp.iterator();
    }

    public <T> T loadAs(final String fileName, Class<T> c) {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileReader(ROOT_DIRECTORY + UserConfig.getProperties().testData() + fileName), c);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file:" + fileName + "," + "Please check the file path, name and format");
        }
    }
}