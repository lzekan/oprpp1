package hr.fer.zemris.java;

public class Slikar 
{
	private String ime;
	private double cijena;
	private boolean over10Milli;
	
	public Slikar(String ime, double cijena, boolean over10Milli) {
		this.ime = ime;
		this.cijena = cijena;
		this.over10Milli = over10Milli;
	}
	
	public String getIme() {
		return this.ime;
	}
	
	public double getCijena() {
		return this.cijena;
	}
	
	public boolean isOver10Milli() {
		return this.over10Milli;
	}
        
    @Override
    public String toString() {
        return "Slikar{" +
                "ime='" + ime + '\'' +
                ", cijena=" + cijena +
                ", over10Milli=" + over10Milli +
                '}';
    }
}
