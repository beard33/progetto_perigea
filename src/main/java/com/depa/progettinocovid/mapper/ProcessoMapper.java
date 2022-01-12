package com.depa.progettinocovid.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.models.Processo;
import com.depa.progettinocovid.models.ProcessoDto;

@Mapper(componentModel = "spring")
public interface ProcessoMapper {

	ProcessoMapper INSTANCE = Mappers.getMapper(ProcessoMapper.class);
	
	ProcessoDto mapToDto(Processo source);
	
	@Mapping(target = "id", ignore = true)
	Processo mapToEntity(ProcessoDto source);
	
	List<ProcessoDto> mapToDtoList(List<Processo> source);
}