package com.at.library.dto;

public class RentDTO extends DTO{

	private static final long serialVersionUID = 7364756623634860483L;

	private Integer idLibro;
	
	private Integer idUser;
	
	private Integer idWorker;
	
	//No ponemos la fecha pues el preferible añadirla nosotros desde nuestro servidor
	//private Date initDate;
	//private Date endDate;

	public Integer getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(Integer idWorker) {
		this.idWorker = idWorker;
	}

	@Override
	public String toString() {
		return "RentDTO [idLibro=" + idLibro + ", idUser=" + idUser + ", idWorker=" + idWorker + "]";
	}
	
}
