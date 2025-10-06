package food.example.online.food.config;

public class loginResp {

	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public loginResp(String jwt) {
		super();
		this.jwt = jwt;
	}

	public loginResp() {
		super();
	}
}
