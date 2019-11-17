package com.example.democsvartemis.service.impl

import com.example.democsvartemis.service.CsvArtemisService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import java.nio.file.StandardCopyOption


@Service
class CsvArtemisServiceImpl: CsvArtemisService {
    private val log = LoggerFactory.getLogger(CsvArtemisServiceImpl::class.java)

    override fun procesarFichero(directorio: String) {
        try{
            Files.list(Paths.get("$directorio/input"))
                    .filter { Files.isRegularFile(it) }
                    .map { it.toFile() }
                    .filter { it.name.endsWith(".csv") }
                    .forEach { file ->
                        log.info("Procesando $file")
                        file.forEachLine { s ->
                            s.split(";").forEach {
                                println(it)
                            }
                        }

                        val sourcepath = Paths.get(file.absolutePath)
                        val destinationepath = Paths.get("$directorio/processed/${file.name}")
                        Files.move(sourcepath, destinationepath, StandardCopyOption.REPLACE_EXISTING)
                    }
        } catch (e: NoSuchFileException) {
            log.error("No existe el directorio ", e)
        }
    }
}
