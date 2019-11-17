package com.example.democsvartemis

import com.example.democsvartemis.service.CsvArtemisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoCsvArtemisApplication(private val csvArtemisService: CsvArtemisService) {
	@Value("\${csv-file}")
	lateinit var directorio: String

	@Bean
	fun init() = CommandLineRunner {
		csvArtemisService.procesarFichero(directorio)
	}
}

fun main(args: Array<String>) {
	runApplication<DemoCsvArtemisApplication>(*args)
}
