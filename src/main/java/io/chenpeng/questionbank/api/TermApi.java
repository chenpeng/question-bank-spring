package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.TermInputDTO;
import io.chenpeng.questionbank.dto.TermOutputDTO;
import io.chenpeng.questionbank.service.TermService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@RestController
@RequestMapping(value = "/api/term", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "term", description = "学期接口")
public class TermApi {

    @Autowired
    private Validator validator;

    @Autowired
    private TermService termService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<TermOutputDTO> list = termService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<TermOutputDTO> getOne(@PathVariable Integer id) {
        TermOutputDTO termOutputDTO = termService.getOne(id);
        return new ResponseEntity<>(termOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<TermOutputDTO> save(@Valid @RequestBody TermInputDTO termInputDTO) {
        TermOutputDTO termOutputDTO = termService.save(termInputDTO);
        return new ResponseEntity<>(termOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<TermOutputDTO> update(@PathVariable Integer id, @RequestBody TermInputDTO termInputDTO) {
        termInputDTO.setId(id);
        TermOutputDTO termOutputDTO = termService.update(termInputDTO);
        return new ResponseEntity<>(termOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = termService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
