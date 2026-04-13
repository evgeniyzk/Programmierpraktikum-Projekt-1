package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
	/* get text from file */
	 public static StringBuffer getText(String path){
	        StringBuffer sBuf = new StringBuffer();
	        try (BufferedReader bufR = new BufferedReader(new FileReader(path))){
	            String line;
	            bufR.readLine(); // skip first line
	            while ((line = bufR.readLine()) != null){
	                sBuf.append(line + "\n");
	            }
	        } catch (IOException e){
	            System.out.println(e);
	        }
	        return sBuf;
	    }

}
