package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.QuestionInputDTO;
import io.chenpeng.questionbank.dto.QuestionOutputDTO;
import io.chenpeng.questionbank.dto.QuestionSearchDTO;
import io.chenpeng.questionbank.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;
import java.util.List;

@RestController
@RequestMapping(value = "/api/question", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "question", description = "题目接口")
public class QuestionApi {

    @Autowired
    private Validator validator;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll(QuestionSearchDTO questionSearchDTO) {
        Set<ConstraintViolation<QuestionSearchDTO>> constraintViolations = validator.validate(questionSearchDTO);
        if (!constraintViolations.isEmpty()) {
            return new ResponseEntity<>(constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
        }

        Page<QuestionOutputDTO> page = questionService.findAll(questionSearchDTO);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<QuestionOutputDTO> getOne(@PathVariable Integer id) {
        QuestionOutputDTO questionOutputDTO = questionService.getOne(id);
        return new ResponseEntity<>(questionOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<QuestionOutputDTO> save(@Valid @RequestBody QuestionInputDTO questionInputDTO) {
        QuestionOutputDTO questionOutputDTO = questionService.save(questionInputDTO);
        return new ResponseEntity<>(questionOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<QuestionOutputDTO> update(@PathVariable Integer id, @RequestBody QuestionInputDTO questionInputDTO) {
        questionInputDTO.setId(id);
        QuestionOutputDTO questionOutputDTO = questionService.update(questionInputDTO);
        return new ResponseEntity<>(questionOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = questionService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
