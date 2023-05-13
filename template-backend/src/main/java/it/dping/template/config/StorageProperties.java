package it.dping.template.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;

//@ConfigurationProperties("storage")

@Configuration
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	@Value("${filesystem.basePath}")
	private String location="/opt/tomcat/villastellamarina/ROOT/assets/media";

	private final Path rootLocation;

	public StorageProperties() {
		rootLocation = Path.of(location);
	}

	@Bean
	CommandLineRunner init() {
		return (args) -> {
			Files.createDirectories(rootLocation);
		};
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
