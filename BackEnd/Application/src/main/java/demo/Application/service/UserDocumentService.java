package demo.Application.service;

import demo.Application.message.ResponseFile;
import demo.Application.model.User;
import demo.Application.model.UserDocument;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface UserDocumentService {

//	 String saveDocument(Long userId, MultipartFile file, String documentName, String documentType);
//
//	    List<UserDocument> getAllDocuments();
//
//	    List<UserDocument> getDocumentsByUserId(Long userId);
//
//	    UserDocument getDocumentById(Long documentId);
	

//
//    UserDocument getFile(Long documentId);
//    Stream<UserDocument> getAllFiles();

	//Save Document
    public String store(Long userId, MultipartFile file, String documentName, String documentType, String documentIdentity);
    
    //Get all Doecument
    List<ResponseFile> getAllResponseFiles();
    
    
    //Get Docuemnt by Id
    byte[] getFileData(Long documentId);
    String getFileName(Long documentId);

    //Get Docuemnt by UserId
    List<ResponseFile> getFilesByUserId(User userId);
    ResponseEntity<byte[]> downloadFile(Long documentId);
    
  

//    UserDocument getUserDocumentById(Long documentId);
    
//    public List<ResponseFile> getFilesByUserId(Long userId);
//    byte[] createZipArchive(List<ResponseFile> files);

}
