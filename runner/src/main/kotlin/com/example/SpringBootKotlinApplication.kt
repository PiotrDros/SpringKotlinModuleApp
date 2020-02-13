package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
//@EnableAspectJAutoProxy
class SpringBootKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBootKotlinApplication>(*args)
}
