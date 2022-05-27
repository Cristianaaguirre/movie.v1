package com.app.movie.ports.inputs.mapper;

import com.app.movie.domain.models.User;
import com.app.movie.ports.inputs.requests.UserRegistration;
import com.app.movie.ports.inputs.responses.UserDetailsResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

   User userRegistrationToUser(UserRegistration aux);

   UserDetailsResponse userToUserDetails(User aux);
}
