package org.zechariahs.toodledoapi.pojo;

/**
 * Represents a Folder in ToodleDo.
 * 
 * When changing the name, private flag or archive flag be sure
 * to use the related set method.  Not doing so will prevent
 * the editFolder method from correctly invoking the
 * ToodleDo API.
 * 
 * @author zechariahs
 *
 */
public class ToodleDoFolder extends ToodleDoBase
{
     
     int id;
     String name = "";
     int privateFolder;
     int archive;
     int ord;
     
     boolean nameDirty = false;
     boolean privateFolderDirty = false;
     boolean archiveDirty = false;

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
          setNameDirty(true);
     }

     public String getName()
     {
          return name;
     }

     public void setArchive(int archive)
     {
          this.archive = archive;
          setArchiveDirty(true);
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
          setPrivateFolderDirty(true);
     }

     public int getPrivateFolder()
     {
          return privateFolder;
     }

	public boolean isNameDirty() {
		return nameDirty;
	}

	public void setNameDirty(boolean nameDirty) {
		this.nameDirty = nameDirty;
	}

	public boolean isPrivateFolderDirty() {
		return privateFolderDirty;
	}

	public void setPrivateFolderDirty(boolean privateFolderDirty) {
		this.privateFolderDirty = privateFolderDirty;
	}

	public boolean isArchiveDirty() {
		return archiveDirty;
	}

	public void setArchiveDirty(boolean archiveDirty) {
		this.archiveDirty = archiveDirty;
	}


}
