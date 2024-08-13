package com.sandabekery.customer_service.sandabakery.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SandaDataToDtoMapper extends ModelMapper {

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(element -> this.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <D, T> D mapFromDataToDto(T entity, Class<D> outClass) {
        return this.map(entity, outClass);
    }

    public <D, T> D mapFromDtoToData(T entity, Class<D> outClass) {
        return this.map(entity, outClass);
    }
}
