package constructor;

public class User {
	private String user;
	private String password;
	
	public User(String anUser, String unaPassword) {
		this.user = anUser;
		this.password = unaPassword;
		
	}
	public String getUser() {
		return this.user;
		
	}
	public String getPassword() {
		return this.password;
		
	}
	public void setUser(String anUser){
		this.user = anUser;

    }
	public void setPassword(String unaPassword){
        this.password = unaPassword;

    }
	public String toString() {
		String cadena = "User: " + this.user + ", Password: " + this.password;
		return cadena;
		
	}
	
}
