package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.Chapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "chapterOutputDTO", description = "章节相关接口输出参数")
public class ChapterOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public Chapter convertToChapter() {
        ChapterOutputDTOConvert chapterOutputDTOConvert = new ChapterOutputDTOConvert();
        Chapter convert = chapterOutputDTOConvert.convert(this);
        return convert;
    }

    public ChapterOutputDTO convertFor(Chapter chapter) {
        ChapterOutputDTOConvert chapterOutputDTOConvert = new ChapterOutputDTOConvert();
        ChapterOutputDTO convert = chapterOutputDTOConvert.reverse().convert(chapter);
        return convert;
    }

    private static class ChapterOutputDTOConvert extends Converter<ChapterOutputDTO, Chapter> {
        @Override
        protected Chapter doForward(ChapterOutputDTO chapterOutputDTO) {
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(chapterOutputDTO, chapter);
            return chapter;
        }

        @Override
        protected ChapterOutputDTO doBackward(Chapter chapter) {
            ChapterOutputDTO chapterOutputDTO = new ChapterOutputDTO();
            BeanUtils.copyProperties(chapter, chapterOutputDTO);
            return chapterOutputDTO;
        }
    }
}
