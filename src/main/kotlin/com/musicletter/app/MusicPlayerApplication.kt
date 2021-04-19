package com.musicletter.app

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)
class MusicPlayerApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        //print(BCryptPasswordEncoder().encode("12345"))
    }
}

fun main(args: Array<String>) {
    runApplication<MusicPlayerApplication>(*args)
}
