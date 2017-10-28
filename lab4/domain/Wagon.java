package lab4.domain;

import java.util.UUID;

public class Wagon extends BaseEntity {
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSeatsCount() {
		return seatsCount;
	}
	public void setSeatsCount(int seatsCount) {
		this.seatsCount = seatsCount;
	}
	public UUID getTypeId() {
		return typeId;
	}
	public void setTypeId(UUID typeId) {
		this.typeId = typeId;
	}
	public UUID getTrainId() {
		return trainId;
	}
	public void setTrainId(UUID trainId) {
		this.trainId = trainId;
	}
	
	private int number;
	private UUID typeId;
	private int seatsCount;
	private UUID trainId;
}
