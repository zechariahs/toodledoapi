package org.zechariahs.toodledoapi.pojo;

public class ToodleDoFolder extends ToodleDoBase
{
     
     int id;
     String name = "";
     int privateFolder;
     int archive;
     int ord;

     public ToodleDoFolder()
     {
     }

     public boolean equals(Object a_oFolderRHS)
     {
          ToodleDoFolder rhs = (ToodleDoFolder)a_oFolderRHS;
          
          if(getId() == rhs.getId() || getName().equalsIgnoreCase(rhs.getName()))
          {
               
               System.out.println("\t\t\t" + getId() + " vs " + rhs.getId() + " OR " + getName() + " vs " + rhs.getName());
               
               return true;
          }
          else
          {
               return false;
          }
     }
     
     public void setId(int id)
     {
          this.id = id;
     }

     public int getId()
     {
          return id;
     }

     public void setName(String name)
     {
          this.name = name;
     }

     public String getName()
     {
          return name;
     }

     public void setArchive(int archive)
     {
          this.archive = archive;
     }

     public int getArchive()
     {
          return archive;
     }

     public void setOrd(int ord)
     {
          this.ord = ord;
     }

     public int getOrd()
     {
          return ord;
     }

     public void setPrivateFolder(int private_folder)
     {
          this.privateFolder = private_folder;
     }

     public int getPrivateFolder()
     {
          return privateFolder;
     }
}
