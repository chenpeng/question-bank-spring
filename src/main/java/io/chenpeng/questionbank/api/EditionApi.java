package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.EditionInputDTO;
import io.chenpeng.questionbank.dto.EditionOutputDTO;
import io.chenpeng.questionbank.dto.EditionSearchDTO;
import io.chenpeng.questionbank.service.EditionService;
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
@RequestMapping(value = "/api/edition", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "edition", description = "版本接口")
public class EditionApi {

    @Autowired
    private EditionService editionService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<EditionOutputDTO> list = editionService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<EditionOutputDTO> getOne(@PathVariable Integer id) {
        EditionOutputDTO editionOutputDTO = editionService.getOne(id);
        return new ResponseEntity<>(editionOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<EditionOutputDTO> save(@Valid @RequestBody EditionInputDTO editionInputDTO) {
        EditionOutputDTO editionOutputDTO = editionService.save(editionInputDTO);
        return new ResponseEntity<>(editionOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<EditionOutputDTO> update(@PathVariable Integer id, @RequestBody EditionInputDTO editionInputDTO) {
        editionInputDTO.setId(id);
        EditionOutputDTO editionOutputDTO = editionService.update(editionInputDTO);
        return new ResponseEntity<>(editionOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = editionService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
