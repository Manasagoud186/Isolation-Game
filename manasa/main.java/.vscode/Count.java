import java.io.*; 
public class Count { public static void main(String [] args){ int charcount=0; int linecount=0; int wordcount=0; try{ 
BufferedReader br=new BufferedReader(new FileReader("Addition.java")); String line; 
while((line=br.readLine()) !=null) 
{ 
linecount++; charcount+=line.length(); 
String words[]=line.trim().split("\\s+"); if(!line.trim().isEmpty()) 
{ 
wordcount+=words.length; 
} 
} 
System.out.println("number of lines "+linecount); 
System.out.println("number of characters "+charcount); 
System.out.println("number of words "+wordcount); 
} 
catch(Exception e) 
{ 
System.out.println(e); 
}}} 
