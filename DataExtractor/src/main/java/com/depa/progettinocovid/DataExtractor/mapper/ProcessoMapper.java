package com.depa.progettinocovid.DataExtractor.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.DataExtractor.model.Processo;
import com.depa.progettinocovid.DataExtractor.model.ProcessoDto;

@Mapper(componentModel = "spring")
public interface ProcessoMapper {

	ProcessoMapper INSTANCE = Mappers.getMapper(ProcessoMapper.class);
	
	ProcessoDto mapToDto(Processo source);
	
	Processo mapToEntity(ProcessoDto source);
	
	List<ProcessoDto> mapToDtoList(List<Processo> source);

}
