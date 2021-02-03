package com.Perfunity.Model;

public class UserBean {
	
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private String gender;
	private String registrationDate;
	private String numTel;
	
	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public UserBean(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
	
	public UserBean() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "UserBean [email=" + email + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome + ", gender=" + gender
				+ ", registrationDate=" + registrationDate + "]";
	}

}
