package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.KnowledgeInputDTO;
import io.chenpeng.questionbank.dto.KnowledgeOutputDTO;
import io.chenpeng.questionbank.service.KnowledgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/knowledge")
@Api(value = "knowledge", description = "知识点接口")
public class KnowledgeApi {

    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回列表对象")
    public ResponseEntity findAll() {
        return new ResponseEntity(knowledgeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity getOne(@PathVariable Integer id) {
        return new ResponseEntity<>(knowledgeService.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<KnowledgeOutputDTO> save(@Valid @RequestBody KnowledgeInputDTO knowledgeInputDTO) {
        KnowledgeOutputDTO knowledgeOutputDTO = knowledgeService.save(knowledgeInputDTO);
        return new ResponseEntity<>(knowledgeOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<KnowledgeOutputDTO> update(@PathVariable Integer id, @RequestBody KnowledgeInputDTO knowledgeInputDTO) {
        knowledgeInputDTO.setId(id);
        KnowledgeOutputDTO knowledgeOutputDTO = knowledgeService.update(knowledgeInputDTO);
        return new ResponseEntity<>(knowledgeOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = knowledgeService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
