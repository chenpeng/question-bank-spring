package io.chenpeng.questionbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.HelloWorld;
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
@ApiModel(value = "helloWorldInputDTO", description = "你好世界相关接口传入参数")
public class HelloWorldInputDTO implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "请输入name参数")
    @ApiModelProperty(name = "name", value = "名称", dataType = "string", required = true, example = "名称")
    private String name;

    public HelloWorld convertToHelloWorld() {
        HelloWorldInputDTOConvert helloWorldInputDTOConvert = new HelloWorldInputDTOConvert();
        HelloWorld convert = helloWorldInputDTOConvert.convert(this);
        return convert;
    }

    public HelloWorldInputDTO convertFor(HelloWorld helloWorld) {
        HelloWorldInputDTO.HelloWorldInputDTOConvert helloWorldInputDTOConvert = new HelloWorldInputDTO.HelloWorldInputDTOConvert();
        HelloWorldInputDTO convert = helloWorldInputDTOConvert.reverse().convert(helloWorld);
        return convert;
    }

    private static class HelloWorldInputDTOConvert extends Converter<HelloWorldInputDTO, HelloWorld> {
        @Override
        protected HelloWorld doForward(HelloWorldInputDTO helloWorldInputDTO) {
            HelloWorld helloWorld = new HelloWorld();
            BeanUtils.copyProperties(helloWorldInputDTO, helloWorld);
            return helloWorld;
        }

        @Override
        protected HelloWorldInputDTO doBackward(HelloWorld helloWorld) {
            HelloWorldInputDTO helloWorldInputDTO = new HelloWorldInputDTO();
            BeanUtils.copyProperties(helloWorld, helloWorldInputDTO);
            return helloWorldInputDTO;
        }
    }
}
