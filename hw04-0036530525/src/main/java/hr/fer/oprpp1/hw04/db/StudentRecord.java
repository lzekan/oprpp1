package hr.fer.oprpp1.hw04.db;

public class StudentRecord {
	
	private String jmbag;
	private String lastName;
	private String firstName;
	private int finalGrade;
	
	public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
		
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
		
	}
	
	public String toString() {
		return "[" + jmbag + ", " + firstName + " " + lastName + ", " + finalGrade + "]"; 
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getJMBAG() {
		return this.jmbag;
	}

}
