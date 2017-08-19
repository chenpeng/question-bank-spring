package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.HelloWorldInputDTO;
import io.chenpeng.questionbank.dto.HelloWorldOutputDTO;
import io.chenpeng.questionbank.dto.HelloWorldSearchDTO;
import io.chenpeng.questionbank.service.HelloWorldService;
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

@RestController
@RequestMapping(value = "/api/helloWorld", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "helloWorld", description = "你好世界接口")
public class HelloWorldApi {

    @Autowired
    private Validator validator;

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll(HelloWorldSearchDTO helloWorldSearchDTO) {
        Set<ConstraintViolation<HelloWorldSearchDTO>> constraintViolations = validator.validate(helloWorldSearchDTO);
        if (!constraintViolations.isEmpty()) {
            return new ResponseEntity<>(constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
        }

        Page<HelloWorldOutputDTO> page = helloWorldService.findAll(helloWorldSearchDTO);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<HelloWorldOutputDTO> getOne(@PathVariable Integer id) {
        HelloWorldOutputDTO helloWorldOutputDTO = helloWorldService.getOne(id);
        return new ResponseEntity<>(helloWorldOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<HelloWorldOutputDTO> save(@Valid @RequestBody HelloWorldInputDTO helloWorldInputDTO) {
        HelloWorldOutputDTO helloWorldOutputDTO = helloWorldService.save(helloWorldInputDTO);
        return new ResponseEntity<>(helloWorldOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<HelloWorldOutputDTO> update(@PathVariable Integer id, @RequestBody HelloWorldInputDTO helloWorldInputDTO) {
        helloWorldInputDTO.setId(id);
        HelloWorldOutputDTO helloWorldOutputDTO = helloWorldService.update(helloWorldInputDTO);
        return new ResponseEntity<>(helloWorldOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = helloWorldService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
