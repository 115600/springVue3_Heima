package config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommonImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> imports = new ArrayList<>();
       InputStream is = CommonImportSelector.class.getClassLoader().getResourceAsStream("common.imports");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String Line = null;
        try {
            while((Line=bufferedReader.readLine())!=null){
                imports.add(Line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(bufferedReader!=null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        //灵活化应用配置文件设置,读取配置文件内容
        return imports.toArray(new String[0]);

    }
}
