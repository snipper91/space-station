package com.example.spacestation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpaceStationApplication {

	public static void main(String[] args) throws IOException {
		try {
			SpringApplication.run(SpaceStationApplication.class, args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
