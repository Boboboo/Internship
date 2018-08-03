package visant.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import visant.db.DBConnection;

public class ProcessManager {
	private final String NodesFile="nodes.txt";
	private final String NamesFile="names.txt";
	
	public void progress() throws Exception {
		//update mind_original_nodes table
//		System.out.println("Please input the path of nodes.dmp. Example /Users/air/Desktop/nodes.txt");
//		String nodesFilePath=getFilePath(NodesFile);
//		if(nodesFilePath!=null) {
//			readNodesData(nodesFilePath);	
//		}
//		System.out.println("(1/3)Update table mind_original_nodes successfully.");
		
		//update mind_original_names table
		System.out.println("Please input the path of names.dmp. Example /Users/air/Desktop/names.txt");
		String namesFilePath=getFilePath(NamesFile);
		if(namesFilePath!=null) {
			readNamesData(namesFilePath);	
		}
		System.out.println("(2/3)Update table mind_original_names successfully.");
		
		//update mind_lineage table	
	}
	
	
	private String getFilePath(String fileName) {
		//System.out.println("Please input the path of nodes.dmp. Example /Users/air/Desktop");
		
		Scanner scanner =new Scanner(System.in);      
		String inputPath="";
		File file;
		
		while(true){
			inputPath=scanner.nextLine();
			if(inputPath.charAt(inputPath.length()-1)!='/') {
				inputPath=inputPath+'/';
			}
			
			file=new File(inputPath);
			if(file.exists()) break;
			else {
				System.out.println("Cannot find the file. Please try again.");
			}	
		}
		return file.getPath();	
	}
	
	
	private void readNodesData(String path)  throws Exception {  
		 System.out.println("Read nodes data path: "+path);
		 
		 DBConnection DBconn=new DBConnection();
		 Connection conn=DBconn.getDBConnection();
		 String sql="";
	
		 FileInputStream fstream = new FileInputStream(path);
		 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		 String strLine;
		 
		 int taxid=0, parent_taxid=0;
		 String rank=null;
		 
		 try {
			 initailTable("original_nodes");
			 
			 while ((strLine = br.readLine()) != null)   {
				 String[] array=strLine.split("\t\\|\t");
				 taxid=Integer.valueOf(array[0].trim());
				 parent_taxid=Integer.valueOf(array[1].trim());
				 rank=array[2].trim();	
				 
				 insertEachRowToNodes(DBconn,conn,taxid, parent_taxid,rank);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    br.close(); 
	}
	
	private void readNamesData(String path)  throws Exception {  
		 System.out.println("Read names data path: "+path);
		 
		 DBConnection DBconn=new DBConnection();
		 Connection conn=DBconn.getDBConnection();
		 String sql="";
	
		 FileInputStream fstream = new FileInputStream(path);
		 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		 String strLine;
		 
		 int id=0,taxid=0;
		 String name=null,type=null,unique_name="";
		 
		 try {
			 initailTable("original_names");
			 
			 while ((strLine = br.readLine()) != null)   {
				 id++;
				 String[] array=strLine.split("\t\\|\t");
				 taxid=Integer.valueOf(array[0].trim());
				 name=array[1].trim();
				 unique_name=array[2].trim();
				 type=array[3].trim();
				 insertEachRowToNames(DBconn,conn,id, name, type, taxid,unique_name);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    br.close(); 
	}
	
	private void insertEachRowToNodes(DBConnection DBconn,Connection conn,int taxid,int parent_taxid,String rank) {
		String sql="";
		
		try {		
			sql = "INSERT INTO original_nodes VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, taxid);
	        	pstmt.setInt(2, parent_taxid);
	        	pstmt.setString(3, rank);
	        pstmt.executeUpdate();    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertEachRowToNames(DBConnection DBconn,Connection conn,int id,String name,String type,int taxid,String unique_name) {
		String sql="";
		
		try {		
			sql = "INSERT INTO original_names VALUES (?,?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, type);
	        	pstmt.setInt(4, taxid);
	        	pstmt.setString(5, unique_name);
	        pstmt.executeUpdate();    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void initailTable(String tableName) {	
		DBConnection DBconn=new DBConnection();
		Connection conn=DBconn.getDBConnection();
		Statement statement=null;
		String sql="";
		
		try {
			statement = conn.createStatement();
			sql = "DELETE FROM "+tableName; 
			statement.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private  void writeUsingFileWriter(String data,String path) {
        File file = new File(path+"result.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }	
	
	

}