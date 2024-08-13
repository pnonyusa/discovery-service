package com.sandabekery.customer_service.sandabakery.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SandaDtoToJpaMapper extends ModelMapper {

    public <S, T> List<T> mapList(List<S> source, Class<T> destination) {
        return source.stream()
                .map(element -> this.map(element, destination))
                .collect(Collectors.toList());
    }


    public <D, T> D mapFromJpaToDto(T entity, Class<D> outClass) {
        return this.map(entity, outClass);
    }

    public <D, T> D mapFromDtoToJpa(T entity, Class<D> outClass) {
        return this.map(entity, outClass);
    }
}
