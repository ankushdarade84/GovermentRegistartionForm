package demo.Application.model;

import java.util.Arrays;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "UserFiles")
public class UserDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User userId;
    private String documentName;
    private String documentType;
    private String documentIndentity;
    
    
    @Lob
    private byte[] documentData;
    
    
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public byte[] getDocumentData() {
		return documentData;
	}
	public void setDocumentData(byte[] documentData) {
		this.documentData = documentData;
	}
		
	public String getDocumentIndentity() {
		return documentIndentity;
	}
	public void setDocumentIndentity(String documentIndentity) {
		this.documentIndentity = documentIndentity;
	}
	
	
	public UserDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDocument [documentId=" + documentId + ", userId=" + userId + ", documentName=" + documentName
				+ ", documentType=" + documentType + ", documentData=" + Arrays.toString(documentData) + "]";
	}
	public UserDocument(Long documentId, User userId, String documentName, String documentType, String documentIndentity, byte[] documentData) {
		super();
		this.documentId = documentId;
		this.userId = userId;
		this.documentName = documentName;
		this.documentType = documentType;
		this.documentData = documentData;
		this.documentIndentity =documentIndentity;
	}

    
	
	
    


    
    
}
