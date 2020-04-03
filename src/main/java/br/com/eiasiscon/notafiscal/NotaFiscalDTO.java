package br.com.eiasiscon.notafiscal;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
public class NotaFiscalDTO {
	
	private Date ini;
	private Date fim;
	private Long empresa;
}
