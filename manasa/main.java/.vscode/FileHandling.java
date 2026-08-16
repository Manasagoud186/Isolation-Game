import java.io.*; 
public class FileHandling { public static void main(String[] args){ try 
{ 
File f=new File("Addition.java"); if(!f.exists()) 
{ 
System.out.println("file not exist"); return; 
} 
else{ 
System.out.println(f.exists() ?"exist":"not exist"); 
System.out.println("file name:"+f.getName()); 
System.out.println(f.canWrite()?"yes":"no"); 
System.out.println(f.canRead()?"yes":"no"); 
System.out.println(f.isFile()?"yes":"no"); 
System.out.println(f.length()+"in bytes"); 
} 
} 
catch(Exception e) 
{ 
System.out.println(e); 
}}} 

    

