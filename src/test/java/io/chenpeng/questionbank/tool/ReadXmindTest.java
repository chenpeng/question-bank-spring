package io.chenpeng.questionbank.tool;

import org.springframework.util.CollectionUtils;
import org.xmind.core.*;

import java.util.List;

public class ReadXmindTest {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\学科特性-方法体系.xmind";

        IWorkbookBuilder builder = Core.getWorkbookBuilder();
        IWorkbook workbook = builder.loadFromPath(path);
        ISheet sheet = workbook.getPrimarySheet();
        ITopic topic = sheet.getRootTopic();
        saveITopic(topic);
    }

    public static void saveITopic(ITopic topic){
        System.out.println(topic.toString());
        INotes notes = topic.getNotes();
        System.out.println(notes.getContent("UTF8"));
        List<ITopic> list = topic.getAllChildren();
        if(!CollectionUtils.isEmpty(list)){
            for (ITopic t:list) {
                saveITopic(t);
            }
        }
    }
}
