package org.zechariahs.toodledoapi.pojo;

public class ToodleDoUser extends ToodleDoBase
{
     private String userid = "";
     private String email = "";
     private String password = "";
     private String token = "";
     private String key = "";
     
     
     public ToodleDoUser()
     {
     }

     public ToodleDoUser(String a_sEmail, String a_sPassword)
     {
          setEmail(a_sEmail);
          setPassword(a_sPassword);
     }
     

     public void setUserid(String userid)
     {
          this.userid = userid;
     }

     public String getUserid()
     {
          return userid;
     }

     public void setEmail(String username)
     {
          this.email = username;
     }

     public String getEmail()
     {
          return email;
     }

     public void setPassword(String password)
     {
          this.password = password;
     }

     public String getPassword()
     {
          return password;
     }

     public void setToken(String token)
     {
          this.token = token;
     }

     public String getToken()
     {
          return token;
     }

     public void setKey(String key)
     {
          this.key = key;
     }

     public String getKey()
     {
          return key;
     }
}
