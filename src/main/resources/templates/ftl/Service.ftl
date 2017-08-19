package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.${MODEL};
import io.chenpeng.questionbank.dto.${MODEL}InputDTO;
import io.chenpeng.questionbank.dto.${MODEL}OutputDTO;
import io.chenpeng.questionbank.dto.${MODEL}SearchDTO;
import io.chenpeng.questionbank.repository.${MODEL}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${MODEL}Service {
    @Autowired
    private ${MODEL}Repository ${LOW_MODEL}Repository;

    <#if (PAGE == "page")>
    public Page<${MODEL}OutputDTO> findAll(${MODEL}SearchDTO ${LOW_MODEL}SearchDTO) {
        Page<${MODEL}> page = ${LOW_MODEL}Repository.findAll(new PageRequest(${LOW_MODEL}SearchDTO.getPageIndex() - 1, ${LOW_MODEL}SearchDTO.getPageSize()));
        return new PageImpl<>(page.getContent().stream().map(new ${MODEL}OutputDTO()::convertFor).collect(Collectors.toList()));
    }
    <#else>
    public List<${MODEL}OutputDTO> findAll() {
        return ${LOW_MODEL}Repository.findAllByDeleted(false).stream().map(new ${MODEL}OutputDTO()::convertFor).collect(Collectors.toList());
    }
    </#if>

    public ${MODEL}OutputDTO getOne(Integer id) {
        ${MODEL} ${LOW_MODEL} = ${LOW_MODEL}Repository.getOne(id);
        return new ${MODEL}OutputDTO().convertFor(${LOW_MODEL});
    }

    public ${MODEL}OutputDTO save(${MODEL}InputDTO ${LOW_MODEL}InputDTO) {
        ${MODEL} ${LOW_MODEL} = ${LOW_MODEL}InputDTO.convertTo${MODEL}();
        ${LOW_MODEL}.setDeleted(false);
        ${LOW_MODEL} = ${LOW_MODEL}Repository.save(${LOW_MODEL});
        return new ${MODEL}OutputDTO().convertFor(${LOW_MODEL});
    }

    public ${MODEL}OutputDTO update(${MODEL}InputDTO ${LOW_MODEL}InputDTO) {
        Integer id = ${LOW_MODEL}InputDTO.getId();
        ${MODEL} ${LOW_MODEL} = ${LOW_MODEL}Repository.getOne(id);
        String name = ${LOW_MODEL}InputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            ${LOW_MODEL}.setName(name);
        }
        ${LOW_MODEL} = ${LOW_MODEL}Repository.save(${LOW_MODEL});
        return new ${MODEL}OutputDTO().convertFor(${LOW_MODEL});
    }

    public int delete(Integer id) {
        ${MODEL} ${LOW_MODEL} = ${LOW_MODEL}Repository.getOne(id);
        ${LOW_MODEL}.setDeleted(true);
        return ${LOW_MODEL}Repository.save(${LOW_MODEL}) == null ? 0 : 1;
    }
}
