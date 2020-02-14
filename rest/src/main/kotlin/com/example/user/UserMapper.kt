package com.example.user
import org.mapstruct.Mapper
import  com.example.model.User as UserDto

@Mapper
interface UserMapper {
    fun map(dto: UserDto): User
}
