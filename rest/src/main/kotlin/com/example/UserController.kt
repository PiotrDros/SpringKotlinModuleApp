package com.example

import com.example.api.UserApi
import com.example.model.User
import com.example.user.UserMapper
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val service: UserService) : UserApi {

    override fun addUser(user: User): ResponseEntity<User> {
        println("User added")


        val converter = Mappers.getMapper(UserMapper::class.java)
        val map = converter.map(user)
        print(user)
        return   ResponseEntity(HttpStatus.OK)
    }

    override fun usersGet(): ResponseEntity<User> {
        println("Get users")
        return ResponseEntity(HttpStatus.OK)
    }


    @GetMapping("/hello")
    fun sayHello(): String {
        return "Hello " + service.getUser().toString() + " from kotlin!"
    }
}
