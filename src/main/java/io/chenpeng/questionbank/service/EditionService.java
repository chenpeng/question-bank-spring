package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Edition;
import io.chenpeng.questionbank.dto.EditionInputDTO;
import io.chenpeng.questionbank.dto.EditionOutputDTO;
import io.chenpeng.questionbank.dto.EditionSearchDTO;
import io.chenpeng.questionbank.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditionService {
    @Autowired
    private EditionRepository editionRepository;

    public List<EditionOutputDTO> findAll() {
        return editionRepository.findAllByDeleted(false).stream().map(new EditionOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public EditionOutputDTO getOne(Integer id) {
        Edition edition = editionRepository.getOne(id);
        return new EditionOutputDTO().convertFor(edition);
    }

    public EditionOutputDTO save(EditionInputDTO editionInputDTO) {
        Edition edition = editionInputDTO.convertToEdition();
        edition.setDeleted(false);
        edition.setState(0);
        edition = editionRepository.save(edition);
        return new EditionOutputDTO().convertFor(edition);
    }

    public EditionOutputDTO update(EditionInputDTO editionInputDTO) {
        Integer id = editionInputDTO.getId();
        Edition edition = editionRepository.getOne(id);
        String name = editionInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            edition.setName(name);
        }
        edition = editionRepository.save(edition);
        return new EditionOutputDTO().convertFor(edition);
    }

    public int delete(Integer id) {
        Edition edition = editionRepository.getOne(id);
        edition.setDeleted(true);
        return editionRepository.save(edition) == null ? 0 : 1;
    }
}
