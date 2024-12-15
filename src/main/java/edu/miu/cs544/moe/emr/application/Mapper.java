package edu.miu.cs544.moe.emr.application;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.stream.Collectors;

@Component
public class Mapper extends ModelMapper {
    public <T> List<T> map(List<?> list, Class<T> clazz) {
        return list.stream().map(model -> this.map(model, clazz)).collect(Collectors.toList());
    }

    public <T> Page<T> map(Page<?> page, Class<T> clazz) {
        List<T> list = this.map(page.getContent(), clazz);
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }
}
