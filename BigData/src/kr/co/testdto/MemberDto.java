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
	public String toString() { // Ŭ������ ���� ��� ������ ���! �̷��� �Ǹ� DTO�� �ϳ� �����Ѱ�
		return "MemberDto [ID=" + ID + ", PW=" + PW + ", NAME=" + NAME + "]";
	}
}
