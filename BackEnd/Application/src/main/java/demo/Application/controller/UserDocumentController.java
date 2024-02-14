package demo.Application.controller;

import demo.Application.message.ResponseFile;
import demo.Application.model.User;
import demo.Application.model.UserDocument;
import demo.Application.pojo.EducationPojo;
import demo.Application.service.UserDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin()
@RestController
public class UserDocumentController {

	 @Autowired
	    private UserDocumentService userDocumentService;

//	    @PostMapping("/upload/{userId}")
//	    public ResponseEntity<String> uploadDocument(
//	            @PathVariable Long userId,
//	            @RequestParam("file") MultipartFile file,
//	            @RequestParam("documentName") String documentName,
//	            @RequestParam("documentType") String documentType) {
//	        String result = userDocumentService.saveDocument(userId, file, documentName, documentType);
//	        return ResponseEntity.ok(result);
//	    }
//
//	    @GetMapping("/all")
//	    public ResponseEntity<List<UserDocument>> getAllDocuments() {
//	        List<UserDocument> documents = userDocumentService.getAllDocuments();
//	        return ResponseEntity.ok(documents);
//	    }
//
//	    @GetMapping("/by-user/{userId}")
//	    public ResponseEntity<List<UserDocument>> getDocumentsByUserId(@PathVariable Long userId) {
//	        List<UserDocument> documents = userDocumentService.getDocumentsByUserId(userId);
//	        return ResponseEntity.ok(documents);
//	    }
//
//	    @GetMapping("/byid/{documentId}")
//	    public ResponseEntity<UserDocument> getDocumentById(@PathVariable Long documentId) {
//	        UserDocument document = userDocumentService.getDocumentById(documentId);
//	        return ResponseEntity.ok(document);
//	    }
	 

//	    @PostMapping("/upload/{userId}")
//	    public ResponseEntity<Void> uploadFile(@PathVariable Long userId,
//	            @RequestBody UserDocument userfile,
//	            @RequestParam("file") MultipartFile file) {
//	    	userDocumentService.store(userId, file, null, null, null);
//	        return ResponseEntity.status(HttpStatus.CREATED).build();
//	    }
//
//	    @GetMapping
//	    public ResponseEntity<List<ResponseFile>> getAllFiles() {
//	        List<ResponseFile> files = fileStorageService.getAllFiles();
//	        return ResponseEntity.status(HttpStatus.OK).body(files);
//	    }
//
//	    @GetMapping("/{id}")
//	    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
//	        FileDB fileDB = fileStorageService.getFile(id);
//	        ByteArrayResource resource = new ByteArrayResource(fileDB.getData());
//
//	        return ResponseEntity.ok()
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//	                .body(resource);
//	    }
	 
	 
	 
	 
	 @PostMapping("/upload/{userId}")
	    public ResponseEntity<String> uploadDocument(
	            @PathVariable Long userId,
	            @RequestParam("file") MultipartFile file,
	            @RequestParam("documentName") String documentName,
	            @RequestParam("documentType") String documentType,
	            @RequestParam("documentIndentity") String documentIndentity) {

	        try {
	            String responseMessage = userDocumentService.store(userId, file, documentName, documentType, documentIndentity);
	            return ResponseEntity.ok(responseMessage);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Error occurred: " + e.getMessage());
	        }
	    }
	 
	 
	 @GetMapping("/listalldoc")
	    public ResponseEntity<List<ResponseFile>> getListFiles() {
	        List<ResponseFile> files = userDocumentService.getAllResponseFiles();

	        return ResponseEntity.status(HttpStatus.OK).body(files);
	    }
	 
	 
//	 @GetMapping("/listalldoc")
//	    public ResponseEntity<byte[]> getListFiles() {
//	        List<ResponseFile> files = userDocumentService.getAllResponseFiles();
//
//	        byte[] zipBytes = userDocumentService.createZipArchive(files);
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	        headers.setContentDispositionFormData("attachment", "all_files.zip");
//	        headers.setContentLength(zipBytes.length);
//
//	        return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
//	    }
//	

	    @GetMapping("/documentbyid/{documentId}")
	    public ResponseEntity<byte[]> getFile(@PathVariable Long documentId) {
	        byte[] fileData = userDocumentService.getFileData(documentId);
	        String fileName = userDocumentService.getFileName(documentId);

	        if (fileData != null && fileName != null) {
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
	                    .body(fileData);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	 
	
	    
	    @GetMapping("/docuserid/{userId}")
	    public ResponseEntity<List<ResponseFile>> getFilesByUserId(@PathVariable User userId) {
	        List<ResponseFile> files = userDocumentService.getFilesByUserId(userId);
	        return ResponseEntity.ok(files);
	    }
	    
	    

}
