package io.chenpeng.questionbank.tool;

import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateCodeTest {

    private Configuration cfg;

    private static final String PROJECT_PATH = "C:\\Users\\Administrator\\Desktop";

    private static final String FTL_PATH = PROJECT_PATH + "\\question-bank-spring\\src\\main\\resources\\templates\\ftl\\";

    private static final String DTO_PATH = PROJECT_PATH + "\\question-bank-spring\\src\\main\\java\\io\\chenpeng\\questionbank\\dto\\";

    private static final String REPOSITORY_PATH = PROJECT_PATH + "\\question-bank-spring\\src\\main\\java\\io\\chenpeng\\questionbank\\repository\\";

    private static final String SERVICE_PATH = PROJECT_PATH + "\\question-bank-spring\\src\\main\\java\\io\\chenpeng\\questionbank\\service\\";

    private static final String API_PATH = PROJECT_PATH + "\\question-bank-spring\\src\\main\\java\\io\\chenpeng\\questionbank\\api\\";

    @Before
    public void init() throws Exception {
        cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(FTL_PATH));

        File file = new File(DTO_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(REPOSITORY_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(SERVICE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(API_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Test
    public void testProcess() throws Exception {
        String MODEL = "Chapter";
        String NOTE = "章节";

        char[] chars = MODEL.toCharArray();
        char c = Character.toLowerCase(chars[0]);
        chars[0] = c;
        String lowModel = String.valueOf(chars);

        Map<String, Object> map = Maps.newHashMap();
        map.put("PAGE", "page111");
        map.put("MODEL", MODEL);
        map.put("NOTE", NOTE);
        map.put("LOW_MODEL", lowModel);
        //创建模版对象
        Template t = cfg.getTemplate("SearchDTO.ftl");
        //在模版上执行插值操作，并输出到制定的输出流中
        File file = new File(DTO_PATH + MODEL + "SearchDTO.java");
        if (!file.exists()) {
            file.createNewFile();
            file.setWritable(true);
        }
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // InputDTO模版类
        t = cfg.getTemplate("InputDTO.ftl");
        file = new File(DTO_PATH + MODEL + "InputDTO.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // OutputDTO模版类
        t = cfg.getTemplate("OutputDTO.ftl");
        file = new File(DTO_PATH + MODEL + "OutputDTO.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // Service类
        t = cfg.getTemplate("Service.ftl");
        file = new File(SERVICE_PATH + MODEL + "Service.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // ServiceImpl类
        t = cfg.getTemplate("Repository.ftl");
        file = new File(REPOSITORY_PATH + MODEL + "Repository.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // Controller类
        t = cfg.getTemplate("Api.ftl");
        file = new File(API_PATH + MODEL + "Api.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));
    }
}
