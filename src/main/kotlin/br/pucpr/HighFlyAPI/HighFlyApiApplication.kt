package br.pucpr.HighFlyAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HighFlyApiApplication

fun main(args: Array<String>) {
    runApplication<HighFlyApiApplication>(*args)
}
