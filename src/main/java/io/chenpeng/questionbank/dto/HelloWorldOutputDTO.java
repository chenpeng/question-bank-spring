package io.chenpeng.questionbank.dto;

import com.google.common.base.Converter;
import io.chenpeng.questionbank.domain.HelloWorld;
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
@ApiModel(value = "helloWorldOutputDTO", description = "你好世界相关接口输出参数")
public class HelloWorldOutputDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "string")
    private String name;

    public HelloWorld convertToHelloWorld() {
        HelloWorldOutputDTOConvert helloWorldOutputDTOConvert = new HelloWorldOutputDTOConvert();
        HelloWorld convert = helloWorldOutputDTOConvert.convert(this);
        return convert;
    }

    public HelloWorldOutputDTO convertFor(HelloWorld helloWorld) {
        HelloWorldOutputDTOConvert helloWorldOutputDTOConvert = new HelloWorldOutputDTOConvert();
        HelloWorldOutputDTO convert = helloWorldOutputDTOConvert.reverse().convert(helloWorld);
        return convert;
    }

    private static class HelloWorldOutputDTOConvert extends Converter<HelloWorldOutputDTO, HelloWorld> {
        @Override
        protected HelloWorld doForward(HelloWorldOutputDTO helloWorldOutputDTO) {
            HelloWorld helloWorld = new HelloWorld();
            BeanUtils.copyProperties(helloWorldOutputDTO, helloWorld);
            return helloWorld;
        }

        @Override
        protected HelloWorldOutputDTO doBackward(HelloWorld helloWorld) {
            HelloWorldOutputDTO helloWorldOutputDTO = new HelloWorldOutputDTO();
            BeanUtils.copyProperties(helloWorld, helloWorldOutputDTO);
            return helloWorldOutputDTO;
        }
    }
}
