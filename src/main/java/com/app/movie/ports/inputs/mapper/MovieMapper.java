package com.app.movie.ports.inputs.mapper;

import com.app.movie.domain.models.Movie;
import com.app.movie.ports.inputs.requests.MovieRequest;
import com.app.movie.ports.inputs.requests.MovieUpdate;
import com.app.movie.ports.inputs.responses.MovieFilterResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {

   Movie toModel(MovieRequest aux);

   Movie updateToModel(MovieUpdate aux);

   List<MovieFilterResponse> toListFilter(List<Movie> aux);
}
