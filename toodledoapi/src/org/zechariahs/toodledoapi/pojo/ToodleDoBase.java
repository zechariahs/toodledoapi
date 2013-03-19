package org.zechariahs.toodledoapi.pojo;

public class ToodleDoBase
{
     String errorCode = "";
     String errorDesc = "";
     String deleted = "";
     
     boolean hasError = false;

     public ToodleDoBase()
     {
          
     }

     public void setErrorCode(String errorCode)
     {
          hasError = true;
          this.errorCode = errorCode;
     }

     public String getErrorCode()
     {
          return errorCode;
     }

     public void setErrorDesc(String errorDesc)
     {
          hasError = true;
          this.errorDesc = errorDesc;
     }

     public String getErrorDesc()
     {
          return errorDesc;
     }

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
