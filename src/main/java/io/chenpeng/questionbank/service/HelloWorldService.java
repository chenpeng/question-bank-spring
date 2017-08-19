package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.HelloWorld;
import io.chenpeng.questionbank.dto.HelloWorldInputDTO;
import io.chenpeng.questionbank.dto.HelloWorldOutputDTO;
import io.chenpeng.questionbank.dto.HelloWorldSearchDTO;
import io.chenpeng.questionbank.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Service
public class HelloWorldService {
    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public Page<HelloWorldOutputDTO> findAll(HelloWorldSearchDTO helloWorldSearchDTO) {
        Page<HelloWorld> page = helloWorldRepository.findAll(new PageRequest(helloWorldSearchDTO.getPageIndex() - 1, helloWorldSearchDTO.getPageSize()));
        return new PageImpl<>(page.getContent().stream().map(new HelloWorldOutputDTO()::convertFor).collect(Collectors.toList()));
    }

    public HelloWorldOutputDTO getOne(Integer id) {
        HelloWorld helloWorld = helloWorldRepository.getOne(id);
        return new HelloWorldOutputDTO().convertFor(helloWorld);
    }

    public HelloWorldOutputDTO save(HelloWorldInputDTO helloWorldInputDTO) {
        HelloWorld helloWorld = helloWorldInputDTO.convertToHelloWorld();
        helloWorld.setDeleted(false);
        helloWorld = helloWorldRepository.save(helloWorld);
        return new HelloWorldOutputDTO().convertFor(helloWorld);
    }

    public HelloWorldOutputDTO update(HelloWorldInputDTO helloWorldInputDTO) {
        Integer id = helloWorldInputDTO.getId();
        HelloWorld helloWorld = helloWorldRepository.getOne(id);
        String name = helloWorldInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            helloWorld.setName(name);
        }
        helloWorld = helloWorldRepository.save(helloWorld);
        return new HelloWorldOutputDTO().convertFor(helloWorld);
    }

    public int delete(Integer id) {
        HelloWorld helloWorld = helloWorldRepository.getOne(id);
        helloWorld.setDeleted(true);
        return helloWorldRepository.save(helloWorld) == null ? 0 : 1;
    }
}
