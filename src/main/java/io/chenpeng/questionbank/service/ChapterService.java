package io.chenpeng.questionbank.service;

import io.chenpeng.questionbank.domain.Chapter;
import io.chenpeng.questionbank.dto.ChapterInputDTO;
import io.chenpeng.questionbank.dto.ChapterOutputDTO;
import io.chenpeng.questionbank.dto.ChapterSearchDTO;
import io.chenpeng.questionbank.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    public List<ChapterOutputDTO> findAll() {
        return chapterRepository.findAllByDeleted(false).stream().map(new ChapterOutputDTO()::convertFor).collect(Collectors.toList());
    }

    public ChapterOutputDTO getOne(Integer id) {
        Chapter chapter = chapterRepository.getOne(id);
        return new ChapterOutputDTO().convertFor(chapter);
    }

    public ChapterOutputDTO save(ChapterInputDTO chapterInputDTO) {
        Chapter chapter = chapterInputDTO.convertToChapter();
        chapter.setDeleted(false);
        chapter.setState(0);
        chapter = chapterRepository.save(chapter);
        return new ChapterOutputDTO().convertFor(chapter);
    }

    public ChapterOutputDTO update(ChapterInputDTO chapterInputDTO) {
        Integer id = chapterInputDTO.getId();
        Chapter chapter = chapterRepository.getOne(id);
        String name = chapterInputDTO.getName();
        if (!StringUtils.isEmpty(name)) {
            chapter.setName(name);
        }
        chapter = chapterRepository.save(chapter);
        return new ChapterOutputDTO().convertFor(chapter);
    }

    public int delete(Integer id) {
        Chapter chapter = chapterRepository.getOne(id);
        chapter.setDeleted(true);
        return chapterRepository.save(chapter) == null ? 0 : 1;
    }
}
