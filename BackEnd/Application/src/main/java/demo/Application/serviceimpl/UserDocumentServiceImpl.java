package demo.Application.serviceimpl;

import demo.Application.message.ResponseFile;
import demo.Application.model.User;
import demo.Application.model.UserDocument;
import demo.Application.repo.UserDocumentRepository;
import demo.Application.repo.UserRepository;
import demo.Application.service.UserDocumentService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.*;

@Service
public class UserDocumentServiceImpl implements UserDocumentService {

//	 @Autowired
//	    private UserRepository userRepository;
//
//	    @Autowired
//	    private UserDocumentRepository userDocumentRepository;
//
//	    @Override
//	    public String saveDocument(Long userId, MultipartFile file, String documentName, String documentType) {
//	        try {
//	            Optional<User> userOptional = userRepository.findById(userId);
//	            if (userOptional.isPresent()) {
//	                User user = userOptional.get();
//	                UserDocument userDocument = new UserDocument();
//	                userDocument.setUserId(user);
//	                userDocument.setDocumentName(file.getOriginalFilename());
//	                userDocument.setDocumentType(documentType);
//	                userDocument.setDocumentData(file.getBytes());
//
//	                userDocumentRepository.save(userDocument);
//	                return "Document saved successfully.";
//	            } else {
//	                return "User not found.";
//	            }
//	        } catch (IOException e) {
//	            return "Error occurred while saving document.";
//	        }
//	    }
//
//	    @Override
//	    public List<UserDocument> getAllDocuments() {
//	        return userDocumentRepository.findAll();
//	    }
//
//	    @Override
//	    public List<UserDocument> getDocumentsByUserId(Long userId) {
//	        return userDocumentRepository.findByUserId(userId);
//	    }
//
//	    @Override
//	    public UserDocument getDocumentById(Long documentId) {
//	        return userDocumentRepository.findById(documentId)
//	                .orElse(null);
//	    }
	
	 @Autowired
	  private UserRepository UserRepository;
	 
	 @Autowired
	 private UserDocumentRepository userDocumentRepository;
	 
	 @Override
	 public String store(Long userId, MultipartFile file, String documentName, String documentType, String documentIndentity) {
	     try {
	         Optional<User> userOptional = UserRepository.findById(userId);

	         if (userOptional.isPresent()) {
	             UserDocument userDocument = new UserDocument();
	             User user = userOptional.get();

	             userDocument.setUserId(user);
	            
	             String orignalname = StringUtils.cleanPath(file.getOriginalFilename());	  
	             userDocument.setDocumentName(orignalname);
	             userDocument.setDocumentType(file.getContentType());
	             userDocument.setDocumentIndentity(documentIndentity);
	             userDocument.setDocumentData(file.getBytes());

	             userDocumentRepository.save(userDocument);

	             return "Document saved successfully.";
	         } else {
	             return "User not found.";
	         }
	     } catch (IOException e) {
	         return "Error occurred while saving document.";
	     }	  
	
	  }
	 
	 
	 
	 
	 @Override
	    public List<ResponseFile> getAllResponseFiles() {
	        return userDocumentRepository.findAll().stream()
	                .map(UserDocument -> new ResponseFile(
	                		UserDocument.getDocumentName(),
	                        "/all/files/" + UserDocument.getDocumentId(),
	                        UserDocument.getDocumentType(),
	                        UserDocument.getDocumentIndentity(),
	                        UserDocument.getUserId(),
	                        (long) UserDocument.getDocumentData().length
	                ))
	                .collect(Collectors.toList());
	    }
	 
	 
	  @Override
	    public byte[] getFileData(Long documentId) {
	        UserDocument userDocument = userDocumentRepository.findById(documentId).orElse(null);
	        return userDocument != null ? userDocument.getDocumentData() : null;
	    }

	    @Override
	    public String getFileName(Long documentId) {
	        UserDocument userDocument = userDocumentRepository.findById(documentId).orElse(null);
	        
	        return userDocument != null ? userDocument.getDocumentName() : null;
	    }
	    

	    
	    
	    
	    
	    
	    @Override
	    public List<ResponseFile> getFilesByUserId(User userId) {
	        List<UserDocument> userDocuments = userDocumentRepository.findByUserId(userId);
	        return userDocuments.stream()
	                .map(userDocument -> new ResponseFile(
	                        userDocument.getDocumentName(),
	                        "/user/files/" + userDocument.getDocumentId(),
	                        userDocument.getDocumentType(),
	                        userDocument.getDocumentIndentity(),
	                        userDocument.getUserId(),
	                        (long) userDocument.getDocumentData().length
	                ))
	                .collect(Collectors.toList());
	    }
	    
	    
//	    @Override
//	    public UserDocument getUserDocumentById(Long documentId) {
//	        return userDocumentRepository.findById(documentId).orElse(null);
//	    }


	    @Override
	    public ResponseEntity<byte[]> downloadFile(Long documentId) {
	        byte[] fileData = getFileData(documentId);
	        String fileName = getFileName(documentId);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	                .body(fileData);
	    }

	
//	    
//	    @Override
//	    public byte[] createZipArchive(List<ResponseFile> files) {
//	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//	        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
//	            for (ResponseFile file : files) {
//	                ZipEntry zipEntry = new ZipEntry(file.getDname());
//	                zipOutputStream.putNextEntry(zipEntry);
//	                zipOutputStream.write(getFileData(file.getUser().getDocumentId()));
//	                zipOutputStream.closeEntry();
//	            }
//	        } catch (IOException e) {
//	            // Handle exception
//	            e.printStackTrace();
//	        }
//	        return byteArrayOutputStream.toByteArray();
//	    }




	
}
