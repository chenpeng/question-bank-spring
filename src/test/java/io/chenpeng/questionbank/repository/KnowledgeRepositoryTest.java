package io.chenpeng.questionbank.repository;

import io.chenpeng.questionbank.domain.Knowledge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.xmind.core.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KnowledgeRepositoryTest {
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Test
    public void save() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\高中数学—知识体系.xmind";
        IWorkbookBuilder builder = Core.getWorkbookBuilder();
        IWorkbook workbook = builder.loadFromPath(path);
        ISheet sheet = workbook.getPrimarySheet();
        ITopic topic = sheet.getRootTopic();
        Integer pId = 0;
        List<ITopic> list = topic.getAllChildren();
        for (ITopic t : list) {
            saveITopic(t, pId);
        }
    }

    public void saveITopic(ITopic topic, Integer pId) throws UnsupportedEncodingException {
        String name = topic.toString().split("\\(")[1].split("\\)")[0];
        Knowledge knowledge = Knowledge.builder().name(name).pId(pId).build();
        knowledge = knowledgeRepository.save(knowledge);
        pId = knowledge.getId();
        List<ITopic> list = topic.getAllChildren();
        if (!CollectionUtils.isEmpty(list)) {
            for (ITopic t : list) {
                saveITopic(t, pId);
            }
        }
    }

}