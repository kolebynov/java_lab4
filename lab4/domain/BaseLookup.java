package lab4.domain;

public abstract class BaseLookup extends BaseEntity {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String name;
}
