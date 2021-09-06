package com.taxisystem;

import com.uber.h3core.H3Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@SpringBootApplication
public class TaxisystemApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(TaxisystemApplication.class, args);
	}

}
