package kr.co.testdto;

public class MemberDto {
	private String ID;
	private String PW;
	private String NAME;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	@Override
	public String toString() { // 클래스가 가진 모든 값들을 출력! 이렇게 되면 DTO를 하나 생성한것
		return "MemberDto [ID=" + ID + ", PW=" + PW + ", NAME=" + NAME + "]";
	}
}
