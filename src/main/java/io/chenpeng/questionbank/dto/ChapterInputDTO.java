package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Chapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "chapterInputDTO", description = "章节相关接口传入参数")
public class ChapterInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public Chapter convertToChapter() {
        ChapterInputDTOConvert chapterInputDTOConvert = new ChapterInputDTOConvert();
        Chapter convert = chapterInputDTOConvert.convert(this);
        return convert;
    }

    public ChapterInputDTO convertFor(Chapter chapter) {
        ChapterInputDTO.ChapterInputDTOConvert chapterInputDTOConvert = new ChapterInputDTO.ChapterInputDTOConvert();
        ChapterInputDTO convert = chapterInputDTOConvert.reverse().convert(chapter);
        return convert;
    }

    private static class ChapterInputDTOConvert extends Converter<ChapterInputDTO, Chapter> {
        @Override
        protected Chapter doForward(ChapterInputDTO chapterInputDTO) {
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(chapterInputDTO, chapter);
            return chapter;
        }

        @Override
        protected ChapterInputDTO doBackward(Chapter chapter) {
            ChapterInputDTO chapterInputDTO = new ChapterInputDTO();
            BeanUtils.copyProperties(chapter, chapterInputDTO);
            return chapterInputDTO;
        }
    }
}
