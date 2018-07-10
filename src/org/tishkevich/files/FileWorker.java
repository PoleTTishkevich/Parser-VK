package org.tishkevich.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;


import org.tishkevich.post.Post;

public class FileWorker {
	String dir;
	File tmp, tmp2;
	Map<Integer, Post> curMap;
	int id;

	
	public FileWorker(int id) throws IOException {
		this.id=id;
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);
		int year = date.get(Calendar.YEAR);
		this.dir=System.getProperty("user.dir")+"/results/"+year + "/" + (month+1) + "/" + day;
	
		this.tmp = new File(this.dir);
		//System.out.println(tmp.getAbsolutePath());
		tmp.mkdirs();
		this.tmp2 = new File(dir, id + ".dat");
		if (!tmp2.exists()) tmp2.createNewFile();
		
	}
	
	@SuppressWarnings("unchecked")
	public Map <Integer, Post> getData(){
		if (tmp.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp2)))
	        {
			
	            curMap=(TreeMap<Integer, Post>)ois.readObject();
	            if(curMap==null){
	            	curMap=new TreeMap<>();
	            }
	            
	        }
	        catch(Exception ex){
	             
	            System.out.println("‘айл еще не был создан;" + ex.getMessage());
	        } 
			
			
		} else {
			tmp.mkdirs();
			curMap=new TreeMap<>();
			
		}
		 
		return curMap;
		
	}
	
	@SuppressWarnings("unchecked")
	public Map <Integer, Post> getDataByDate(Calendar date){
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);
		int year = date.get(Calendar.YEAR);
		String tDir=System.getProperty("user.dir")+"/results/"+year + "/" + (month) + "/" + day;
		this.tmp = new File(tDir);
		
		this.tmp2 = new File(tDir, id + ".dat");
		if (tmp.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp2)))
	        {
			
	            curMap=(TreeMap<Integer, Post>)ois.readObject();
	            if(curMap==null){
	            	curMap=new TreeMap<>();
	            }
	            
	        }
	        catch(Exception ex){
	             
	            System.out.println("‘айл еще не был создан;" + ex.getMessage());
	        } 
			
			
		} else {
			curMap=new TreeMap<>();
			
		}
		 
		return curMap;
		
	}
		public void save(Map<Integer, Post> savingMap){
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp2))) {
			oos.writeObject(savingMap);
			oos.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}
	}

}
