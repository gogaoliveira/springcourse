package com.douglasoliveira.cursosb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglasoliveira.cursosb.services.S3Service;

@SpringBootApplication
public class CursosbApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(CursosbApplication.class, args);
			
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Cursos\\Spring Boot\\ws_spring_boot\\doug.png");
	}

}
