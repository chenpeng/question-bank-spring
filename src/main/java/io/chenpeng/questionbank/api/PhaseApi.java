package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.PhaseInputDTO;
import io.chenpeng.questionbank.dto.PhaseOutputDTO;
import io.chenpeng.questionbank.service.PhaseService;
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
@RequestMapping(value = "/api/phase", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "phase", description = "学段接口")
public class PhaseApi {

    @Autowired
    private PhaseService phaseService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<PhaseOutputDTO> list = phaseService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<PhaseOutputDTO> getOne(@PathVariable Integer id) {
        PhaseOutputDTO phaseOutputDTO = phaseService.getOne(id);
        return new ResponseEntity<>(phaseOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<PhaseOutputDTO> save(@Valid @RequestBody PhaseInputDTO phaseInputDTO) {
        PhaseOutputDTO phaseOutputDTO = phaseService.save(phaseInputDTO);
        return new ResponseEntity<>(phaseOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<PhaseOutputDTO> update(@PathVariable Integer id, @RequestBody PhaseInputDTO phaseInputDTO) {
        phaseInputDTO.setId(id);
        PhaseOutputDTO phaseOutputDTO = phaseService.update(phaseInputDTO);
        return new ResponseEntity<>(phaseOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = phaseService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
