package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDatabase {

	private Map<Integer, StudentRecord> database = new HashMap<>();
	
	public StudentDatabase(List<String> list) {
	
		for(String s : list) {
			
			String arr[] = s.split("\t");
			StudentRecord sr = new StudentRecord(arr[0], arr[1], arr[2], Integer.valueOf(arr[3]));
			database.put(Integer.valueOf(arr[0]), sr);
			
		}
		
	}
	
	public Map<Integer, StudentRecord> getMap(){
		return this.database;
	}
	
	public int size() {
		return this.database.size();
	}
	
	public StudentRecord forJMBAG(String jmbag) {
		
		int intJMBAG = Integer.valueOf(jmbag);
		return database.get(intJMBAG);
	
	}
	
	public List<StudentRecord> filter(IFilter filter){
		
		List<StudentRecord> filteredDB = new ArrayList<>();
		
		for(StudentRecord sr : database.values()) {
			
			if(filter.accepts(sr)) {
				filteredDB.add(sr);
			}
		}
		
		
		return filteredDB;
		
	}
	
	
}
