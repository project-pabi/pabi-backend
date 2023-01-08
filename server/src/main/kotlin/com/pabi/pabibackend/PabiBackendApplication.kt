package com.pabi.pabibackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["com.pabi"])
class PabiBackendApplication

fun main(args: Array<String>) {
    runApplication<PabiBackendApplication>(*args)
}