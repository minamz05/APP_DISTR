package web.Models;
public class Candidat {
	protected int IdCandidat;
	protected String Name;
	protected String Surname;
	protected String Position;
	
	public Candidat() {
	}
	
	public Candidat(String Name, String Surname, String Position) {
		super();
		this.Name = Name;
		this.Surname = Surname;
		this.Position = Position;
	}

	public Candidat(int IdCandidat, String Name, String Surname, String Position) {
		super();
		this.IdCandidat = IdCandidat;
		this.Name = Name;
		this.Surname = Surname;
		this.Position = Position;
	}

	public int getIdCandidat() {
		return IdCandidat;
	}
	public void setIdCandidat(int IdCandidat) {
		this.IdCandidat = IdCandidat;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String Surname) {
		this.Surname = Surname;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String Position) {
		this.Position = Position;
	}
}