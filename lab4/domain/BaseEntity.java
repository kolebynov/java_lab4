package lab4.domain;

import java.util.UUID;

public abstract class BaseEntity {
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	private UUID id;
}
