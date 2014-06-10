package br.com.infoCenter.negocio;

import java.sql.SQLException;
import java.util.List;

import br.com.infoCenter.infra.NotaFiscalDTO;
import br.com.infoCenter.persistencia.NotaFiscalDAO;

public class NotaFiscalBO {

	private NotaFiscalDAO notaFiscalDAO;

	public NotaFiscalBO(NotaFiscalDAO notaFiscalDAO) {
		this.notaFiscalDAO = notaFiscalDAO;
	}

	public List<NotaFiscalDTO> buscarNotas() throws SQLException {
		return notaFiscalDAO.getNotas();
	}
}
