package org.zechariahs.toodledoapi.pojo;

public class ToodleDoContext extends ToodleDoBase
{
     
     private int id;
     private String name = "";
     
     public ToodleDoContext()
     {
     }
     
     public boolean equals(Object a_oRHS)
     {
          ToodleDoContext RHS = (ToodleDoContext)a_oRHS;
          
          if(getId() == RHS.getId() || getName().equalsIgnoreCase(RHS.getName()))
          {
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
}
