package com.thulasi.easyway.mapper;

import com.thulasi.easyway.model.User;
import com.thulasi.easyway.payload.response.SignInResponseDto;
import com.thulasi.easyway.payload.response.UserResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T15:15:21+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class CommonMapperImpl implements CommonMapper {

    @Override
    public SignInResponseDto createUserToSignInResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        SignInResponseDto signInResponseDto = new SignInResponseDto();

        signInResponseDto.setFirstName( user.getFirstName() );
        signInResponseDto.setLastName( user.getLastName() );
        signInResponseDto.setEmail( user.getEmail() );
        signInResponseDto.setIsPasswordChangedForTheFirstTime( user.getIsPasswordChangedForTheFirstTime() );

        return signInResponseDto;
    }

    @Override
    public UserResponseDto createUserToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.email( user.getEmail() );
        userResponseDto.firstName( user.getFirstName() );
        userResponseDto.lastName( user.getLastName() );

        return userResponseDto.build();
    }
}
