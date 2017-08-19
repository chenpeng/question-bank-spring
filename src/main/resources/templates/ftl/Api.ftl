package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.${MODEL}InputDTO;
import io.chenpeng.questionbank.dto.${MODEL}OutputDTO;
import io.chenpeng.questionbank.dto.${MODEL}SearchDTO;
import io.chenpeng.questionbank.service.${MODEL}Service;
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
@RequestMapping(value = "/api/${LOW_MODEL}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "${LOW_MODEL}", description = "${NOTE}接口")
public class ${MODEL}Api {

    @Autowired
    private Validator validator;

    @Autowired
    private ${MODEL}Service ${LOW_MODEL}Service;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    <#if (PAGE == "page")>
    public ResponseEntity findAll(${MODEL}SearchDTO ${LOW_MODEL}SearchDTO) {
        Set<ConstraintViolation<${MODEL}SearchDTO>> constraintViolations = validator.validate(${LOW_MODEL}SearchDTO);
        if (!constraintViolations.isEmpty()) {
            return new ResponseEntity<>(constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
        }

        Page<${MODEL}OutputDTO> page = ${LOW_MODEL}Service.findAll(${LOW_MODEL}SearchDTO);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    <#else>
    public ResponseEntity findAll() {
        List<${MODEL}OutputDTO> list = ${LOW_MODEL}Service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    </#if>


    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<${MODEL}OutputDTO> getOne(@PathVariable Integer id) {
        ${MODEL}OutputDTO ${LOW_MODEL}OutputDTO = ${LOW_MODEL}Service.getOne(id);
        return new ResponseEntity<>(${LOW_MODEL}OutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<${MODEL}OutputDTO> save(@Valid @RequestBody ${MODEL}InputDTO ${LOW_MODEL}InputDTO) {
        ${MODEL}OutputDTO ${LOW_MODEL}OutputDTO = ${LOW_MODEL}Service.save(${LOW_MODEL}InputDTO);
        return new ResponseEntity<>(${LOW_MODEL}OutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<${MODEL}OutputDTO> update(@PathVariable Integer id, @RequestBody ${MODEL}InputDTO ${LOW_MODEL}InputDTO) {
        ${LOW_MODEL}InputDTO.setId(id);
        ${MODEL}OutputDTO ${LOW_MODEL}OutputDTO = ${LOW_MODEL}Service.update(${LOW_MODEL}InputDTO);
        return new ResponseEntity<>(${LOW_MODEL}OutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = ${LOW_MODEL}Service.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
