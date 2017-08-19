package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.SubjectInputDTO;
import io.chenpeng.questionbank.dto.SubjectOutputDTO;
import io.chenpeng.questionbank.dto.SubjectSearchDTO;
import io.chenpeng.questionbank.service.SubjectService;
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
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/subject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "subject", description = "学科接口")
public class SubjectApi {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<SubjectOutputDTO> list = subjectService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<SubjectOutputDTO> getOne(@PathVariable Integer id) {
        SubjectOutputDTO subjectOutputDTO = subjectService.getOne(id);
        return new ResponseEntity<>(subjectOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<SubjectOutputDTO> save(@Valid @RequestBody SubjectInputDTO subjectInputDTO) {
        SubjectOutputDTO subjectOutputDTO = subjectService.save(subjectInputDTO);
        return new ResponseEntity<>(subjectOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<SubjectOutputDTO> update(@PathVariable Integer id, @RequestBody SubjectInputDTO subjectInputDTO) {
        subjectInputDTO.setId(id);
        SubjectOutputDTO subjectOutputDTO = subjectService.update(subjectInputDTO);
        return new ResponseEntity<>(subjectOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = subjectService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
