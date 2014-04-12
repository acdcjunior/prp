package net.acdcjunior.prp.domain.valueobject;


public class Mes {
	
	public static final int QTD_MESES_NO_ANO = 12;
	
	private int ano;
	private int mes;
	
	public Mes(int ano, int mes) {
		this.ano = ano;
		this.mes = mes;
	}
	
	public int getAno() {
		return ano;
	}
	public int getMes() {
		return mes;
	}
	
	public Mes getMesAnterior() {
		if (mes == 1) {
			return new Mes(ano-1, 12);
		}
		return new Mes(ano, mes-1); 
	}

}