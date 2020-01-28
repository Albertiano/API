package br.com.eiasiscon.uploadfiles;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
@RestController
@RequestMapping("/upload")
public class UploadEndPoint {
 
	@Autowired
	StorageService storageService;
 
	@PostMapping
	public ResponseEntity<Arquivo> handleFileUpload(@RequestParam("path") String path, @RequestParam("file") MultipartFile file) {
		Arquivo arquivo = new Arquivo();
		try {
			String sep = System.getProperty("file.separator");
			path = sep + "EIASisCon" + sep + path;
			Path rootLocation = Paths.get(System.getProperty("user.dir")).getRoot();
			storageService.store(rootLocation.resolve(path), file);
 
			arquivo.setPath(rootLocation.resolve(path) + sep + file.getOriginalFilename());
			arquivo.setFile(file.getOriginalFilename());
			
			return ResponseEntity.status(HttpStatus.OK).body(arquivo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
}
