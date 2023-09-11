package hr.fer.oprpp1.hw04.db;

public enum FieldValueGetters implements IFieldValueGetter{
	FIRST_NAME, 
	LAST_NAME,
	JMBAG;

	@Override
	public String get(StudentRecord record) {
		
		if(FieldValueGetters.this == FieldValueGetters.FIRST_NAME) {
			return record.getFirstName();
		} else if(FieldValueGetters.this == FieldValueGetters.LAST_NAME) {
			return record.getLastName();
		} else {
			return record.getJMBAG();
		}
		
	}
	
	
}
