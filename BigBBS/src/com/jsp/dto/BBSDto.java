package com.jsp.dto;

public class BBSDto {
	private String bbsId; // �Խù� ��ȣ
	private String id; // ���̵�
	private String bbsTitle; // ����
	private String bbsDate; // ��¥
	private String bbsCategory; // ī�װ�
	private String bbsContent; // ����
	private String bbsHit; // ��ȸ��
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBbsId() {
		return bbsId;
	}
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getBbsCategory() {
		return bbsCategory;
	}
	public void setBbsCategory(String bbsCategory) {
		this.bbsCategory = bbsCategory;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsHit() {
		return bbsHit;
	}
	public void setBbsHit(String bbsHit) {
		this.bbsHit = bbsHit;
	}
	@Override
	public String toString() {
		return "BBSDto [bbsId=" + bbsId + ", id=" + id + ", bbsTitle=" + bbsTitle + ", bbsDate=" + bbsDate
				+ ", bbsCategory=" + bbsCategory + ", bbsContent=" + bbsContent + ", bbsHit=" + bbsHit + ", img=" + img
				+ "]";
	}
	
	
}
