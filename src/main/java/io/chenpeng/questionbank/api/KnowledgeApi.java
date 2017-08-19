package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/knowledge")
public class KnowledgeApi {

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return new ResponseEntity(knowledgeService.findAll(), HttpStatus.OK);
    }
}
