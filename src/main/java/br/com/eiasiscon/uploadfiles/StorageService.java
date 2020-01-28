package br.com.eiasiscon.uploadfiles;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
 
@Service
public class StorageService {
 
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
 
	public void store(Path rootLocation, MultipartFile file) {
		try {
			Files.createDirectories(rootLocation);
			Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("FAIL!");
		}
	}
	
	public String getPath(String empresaID, String filename) {
		String sep = System.getProperty("file.separator");
		String path = sep + "EIASisCon" + sep + empresaID;
		Path rootLocation = Paths.get(System.getProperty("user.dir")).getRoot();
		return rootLocation.resolve(path) + sep + filename;
	}
 
	public Resource loadFile(String path, String filename) {
		try {
			Path rootLocation = Paths.get(path);
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public void deleteAll(String path) {
		Path rootLocation = Paths.get(path);
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
}