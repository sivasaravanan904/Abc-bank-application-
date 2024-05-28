//Siva
package com.primesoftinc.message;

import java.io.Closeable;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MessageModelsApplication implements Closeable{

	private static ConfigurableApplicationContext run;

	public static void main(String[] args) {
		run  = SpringApplication.run(MessageModelsApplication.class, args);
	}

	@Override
	public void close() throws IOException {
		run.close();
	}

}
