package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.QuestionSourceInputDTO;
import io.chenpeng.questionbank.dto.QuestionSourceOutputDTO;
import io.chenpeng.questionbank.service.QuestionSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/questionSource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "questionSource", description = "题目来源接口")
public class QuestionSourceApi {

    @Autowired
    private QuestionSourceService questionSourceService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<QuestionSourceOutputDTO> list = questionSourceService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<QuestionSourceOutputDTO> getOne(@PathVariable Integer id) {
        QuestionSourceOutputDTO questionSourceOutputDTO = questionSourceService.getOne(id);
        return new ResponseEntity<>(questionSourceOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<QuestionSourceOutputDTO> save(@Valid @RequestBody QuestionSourceInputDTO questionSourceInputDTO) {
        QuestionSourceOutputDTO questionSourceOutputDTO = questionSourceService.save(questionSourceInputDTO);
        return new ResponseEntity<>(questionSourceOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<QuestionSourceOutputDTO> update(@PathVariable Integer id, @RequestBody QuestionSourceInputDTO questionSourceInputDTO) {
        questionSourceInputDTO.setId(id);
        QuestionSourceOutputDTO questionSourceOutputDTO = questionSourceService.update(questionSourceInputDTO);
        return new ResponseEntity<>(questionSourceOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = questionSourceService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
