package datastructurep;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author nouraalmadhi
 */
import java.io.File;
import java.util.Scanner;
public class ReadFile {
    
    public static void Load(String fileName){
        
    String line=null;
    
    try{
        File data=new File(fileName);
        Scanner scan=new Scanner(data);
        
        scan.nextLine(); // عشان نتخطى أول سطر حق الأسماء
        
        while(scan.hasNextLine()){ // حتى نهاية الملف  طالما يوجد سطر بعده
        line=scan.nextLine();
        
     
        
        System.out.println(line);
        
        String id=line.substring(0,line.indexOf(','));
        int docId = Integer.parseInt(id.trim());
        String docContent = line.substring(line.indexOf(',')+1).trim();
        
        }
    } catch (Exception e){
        System.out.println("End of file."); }
    
    }//end Load
  
    
    
  public static void main(String[]args){  
  Load("dataset.csv");    }  //end main
  
}
