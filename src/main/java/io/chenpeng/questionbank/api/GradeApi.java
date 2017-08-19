package io.chenpeng.questionbank.api;

import io.chenpeng.questionbank.dto.GradeInputDTO;
import io.chenpeng.questionbank.dto.GradeOutputDTO;
import io.chenpeng.questionbank.service.GradeService;
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
@RequestMapping(value = "/api/grade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "grade", description = "年级接口")
public class GradeApi {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    @ApiOperation(nickname = "findAll", value = "查询所有", notes = "返回分页对象")
    public ResponseEntity findAll() {
        List<GradeOutputDTO> list = gradeService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(nickname = "getOne", value = "根据ID查询", notes = "返回单个对象")
    public ResponseEntity<GradeOutputDTO> getOne(@PathVariable Integer id) {
        GradeOutputDTO gradeOutputDTO = gradeService.getOne(id);
        return new ResponseEntity<>(gradeOutputDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(nickname = "save", value = "创建", notes = "创建一个对象")
    public ResponseEntity<GradeOutputDTO> save(@Valid @RequestBody GradeInputDTO gradeInputDTO) {
        GradeOutputDTO gradeOutputDTO = gradeService.save(gradeInputDTO);
        return new ResponseEntity<>(gradeOutputDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(nickname = "update", value = "修改", notes = "修改一个对象")
    public ResponseEntity<GradeOutputDTO> update(@PathVariable Integer id, @RequestBody GradeInputDTO gradeInputDTO) {
        gradeInputDTO.setId(id);
        GradeOutputDTO gradeOutputDTO = gradeService.update(gradeInputDTO);
        return new ResponseEntity<>(gradeOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(nickname = "delete", value = "删除", notes = "根据ID删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        Integer i = gradeService.delete(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
