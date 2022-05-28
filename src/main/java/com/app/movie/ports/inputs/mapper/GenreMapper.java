package com.app.movie.ports.inputs.mapper;

import com.app.movie.domain.models.Genre;
import com.app.movie.ports.inputs.requests.GenreCreateRequest;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

   Genre GenreCreateRequestToGenre(GenreCreateRequest request);
}
