package org.sid.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigProperties {
    private final Properties propertiesConfig=new Properties();
    FileReader fileReaderConfig = null;

    public ReadConfigProperties(String fileName){
        try {
            fileReaderConfig = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\"+ fileName +".properties");
            propertiesConfig.load(fileReaderConfig);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getPropertiesConfig() {
        return propertiesConfig;
    }
}
