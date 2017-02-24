package entities;


public class Metier {

	private Integer idMetier;
	private String Nom;
	private String Description;
	

	public Metier(Integer idMetier, String Nom ,String Description) {
		super();
		this.idMetier = idMetier;
		this.Nom = Nom;
		this.Description= Description;
		
		
	}
	public Integer getIdMetier() {
		return idMetier;
	}

	public void setIdMetier(Integer idMetier) {
		this.idMetier = idMetier;
	}
	public String getDescription() {
		return Description; 
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getNom() {
		return Nom;
	}

	public void setNom(String Nom) {
		this.Nom = Nom;
	}

}
