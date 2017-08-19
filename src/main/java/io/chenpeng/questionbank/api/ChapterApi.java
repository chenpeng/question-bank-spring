package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.ChapterInputDTO;
import io.chenpeng.questionbank.dto.ChapterOutputDTO;
import io.chenpeng.questionbank.dto.ChapterSearchDTO;
import io.chenpeng.questionbank.service.ChapterService;
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
@RequestMapping(value = "/api/chapter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "chapter", description = "章节接口")
public class ChapterApi {

    @Autowired
    private Validator validator;

    @Autowired
    private ChapterService chapterService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<ChapterOutputDTO> list = chapterService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<ChapterOutputDTO> getOne(@PathVariable Integer id) {
        ChapterOutputDTO chapterOutputDTO = chapterService.getOne(id);
        return new ResponseEntity<>(chapterOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<ChapterOutputDTO> save(@Valid @RequestBody ChapterInputDTO chapterInputDTO) {
        ChapterOutputDTO chapterOutputDTO = chapterService.save(chapterInputDTO);
        return new ResponseEntity<>(chapterOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<ChapterOutputDTO> update(@PathVariable Integer id, @RequestBody ChapterInputDTO chapterInputDTO) {
        chapterInputDTO.setId(id);
        ChapterOutputDTO chapterOutputDTO = chapterService.update(chapterInputDTO);
        return new ResponseEntity<>(chapterOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = chapterService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
