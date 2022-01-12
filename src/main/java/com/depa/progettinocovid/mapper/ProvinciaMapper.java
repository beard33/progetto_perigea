package com.depa.progettinocovid.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.models.ProvinciaDto;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper {

	ProvinciaMapper INSTANCE = Mappers.getMapper(ProvinciaMapper.class);
	
	ProvinciaDto mapToDto(Provincia source);
	
	@Mapping(target = "id", ignore = true)
	Provincia mapToEntity(ProvinciaDto source);
	
	List<ProvinciaDto> mapToDtoList(List<Provincia> source);
}