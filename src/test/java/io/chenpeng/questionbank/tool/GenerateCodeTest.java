package io.chenpeng.questionbank.tool;

import com.google.common.base.Joiner;
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
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateCodeTest {

    private Configuration cfg;

    private String projectPath;
    private String ftlPath;
    private String dtoPath;
    private String repositoryPath;
    private String servicePath;
    private String apiPath;

    @Before
    public void init() throws Exception {
        Joiner joiner = Joiner.on(File.separator).skipNulls();

        projectPath = System.getProperty("user.dir");
        ftlPath = joiner.join(projectPath, "src", "main", "resources", "templates", "ftl") + File.separator;
        dtoPath = joiner.join(projectPath, "src", "main", "java", "io", "chenpeng", "questionbank", "dto") + File.separator;
        repositoryPath = joiner.join(projectPath, "src", "main", "java", "io", "chenpeng", "questionbank", "repository") + File.separator;
        servicePath = joiner.join(projectPath, "src", "main", "java", "io", "chenpeng", "questionbank", "service") + File.separator;
        apiPath = joiner.join(projectPath, "src", "main", "java", "io", "chenpeng", "questionbank", "api") + File.separator;

        cfg = new Configuration();
//        cfg.setClassForTemplateLoading(this.getClass(), "templates\\ftl\\");
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));

        File file = new File(dtoPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(repositoryPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(servicePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(apiPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Test
    public void test() {
        System.out.println(ftlPath);
        System.out.println(dtoPath);
        System.out.println(repositoryPath);
        System.out.println(servicePath);
        System.out.println(apiPath);
    }

    @Test
    public void testProcess() throws Exception {
        String MODEL = "Question";
        String NOTE = "题目";

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
        File file = new File(dtoPath + MODEL + "SearchDTO.java");
        if (!file.exists()) {
            file.createNewFile();
            file.setWritable(true);
        }
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // InputDTO模版类
        t = cfg.getTemplate("InputDTO.ftl");
        file = new File(dtoPath + MODEL + "InputDTO.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // OutputDTO模版类
        t = cfg.getTemplate("OutputDTO.ftl");
        file = new File(dtoPath + MODEL + "OutputDTO.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // Service类
        t = cfg.getTemplate("Service.ftl");
        file = new File(servicePath + MODEL + "Service.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // ServiceImpl类
        t = cfg.getTemplate("Repository.ftl");
        file = new File(repositoryPath + MODEL + "Repository.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));

        // Controller类
        t = cfg.getTemplate("Api.ftl");
        file = new File(apiPath + MODEL + "Api.java");
        t.process(map, new OutputStreamWriter(new FileOutputStream(file)));
    }
}
