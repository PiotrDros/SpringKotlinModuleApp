package com.example

import org.springframework.stereotype.Service

@Service
class UserService {

    fun getUser(): String {
        return "John Doe"
    }
}
