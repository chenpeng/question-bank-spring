package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.PhaseSubjectInputDTO;
import io.chenpeng.questionbank.dto.PhaseSubjectOutputDTO;
import io.chenpeng.questionbank.dto.PhaseSubjectSearchDTO;
import io.chenpeng.questionbank.service.PhaseSubjectService;
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
@RequestMapping(value = "/api/phaseSubject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "phaseSubject", description = "学段学科接口")
public class PhaseSubjectApi {

    @Autowired
    private PhaseSubjectService phaseSubjectService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回列表对象")
    public ResponseEntity findAll() {
        List<PhaseSubjectOutputDTO> list = phaseSubjectService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<PhaseSubjectOutputDTO> getOne(@PathVariable Integer id) {
        PhaseSubjectOutputDTO phaseSubjectOutputDTO = phaseSubjectService.getOne(id);
        return new ResponseEntity<>(phaseSubjectOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<PhaseSubjectOutputDTO> save(@Valid @RequestBody PhaseSubjectInputDTO phaseSubjectInputDTO) {
        PhaseSubjectOutputDTO phaseSubjectOutputDTO = phaseSubjectService.save(phaseSubjectInputDTO);
        return new ResponseEntity<>(phaseSubjectOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<PhaseSubjectOutputDTO> update(@PathVariable Integer id, @RequestBody PhaseSubjectInputDTO phaseSubjectInputDTO) {
        phaseSubjectInputDTO.setId(id);
        PhaseSubjectOutputDTO phaseSubjectOutputDTO = phaseSubjectService.update(phaseSubjectInputDTO);
        return new ResponseEntity<>(phaseSubjectOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = phaseSubjectService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
