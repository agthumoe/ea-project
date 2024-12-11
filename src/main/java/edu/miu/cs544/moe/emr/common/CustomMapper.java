package edu.miu.cs544.moe.emr.common;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomMapper extends ModelMapper {
    public <T> List<T> map(List<?> list, Class<T> clazz) {
        return list.stream().map(d -> this.map(d, clazz)).collect(Collectors.toList());
    }

    public <T> Page<T> map(Page<?> page, Class<T> clazz) {
        List<T> list = this.map(page.getContent(), clazz);
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }
}
