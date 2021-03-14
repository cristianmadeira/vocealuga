package br.cefetrj.mg.bsi.vocealuga.dao;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteErrorMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveErrorMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getSaveSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateErrorMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getUpdateSuccessMessage;
import static br.cefetrj.mg.bsi.vocealuga.utils.Utils.*;
import br.cefetrj.mg.bsi.vocealuga.config.ConnectionFactory;
import br.cefetrj.mg.bsi.vocealuga.exception.InvalidIdException;
import br.cefetrj.mg.bsi.vocealuga.model.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GrupoDAOImpl implements IDAO<Grupo> {

	private static final Logger logger = LogManager.getLogger(GrupoDAOImpl.class);
	private ConnectionFactory factory = null;
	private static final ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");
	private static final String TITLE = "grupo";

	public GrupoDAOImpl() {
		this.factory = ConnectionFactory.getInstance();
	}

	@Override
	public Grupo save(Grupo o) throws SQLException {
		Connection conn = this.factory.getConn();
		String sql = "INSERT INTO grupos(nome, created_at) VALUES(?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			pst.setString(1, o.getNome());
			pst.setTimestamp(2, converterLocalDateTimeToTimestamp(LocalDateTime.now(ZONE_ID)));
			pst.executeUpdate();
			conn.commit();
			int id = this.getLastId();
			o = this.find(id);
			logger.info(getSaveSuccessMessage(TITLE + o.getNome()));
			return o;
		} catch (SQLException | InvalidIdException e) {
			conn.rollback();
			String errorMsg = getSaveErrorMessage(TITLE, e.getMessage());
			logger.error(errorMsg);
			throw new SQLException(errorMsg);
		}

	}

	@Override
	public Grupo update(Grupo o) throws SQLException {
		String sql = "UPDATE grupos SET nome = ?, updated_at = ? WHERE id = ?";
		Connection conn = this.factory.getConn();
		try (PreparedStatement pst = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			Grupo oldGrupo = this.find(o.getId());
			pst.setString(1, o.getNome());
			pst.setTimestamp(2, converterLocalDateTimeToTimestamp(LocalDateTime.now(ZONE_ID)));
			pst.setInt(3, oldGrupo.getId());
			pst.executeUpdate();
			conn.commit();
			logger.info(getUpdateSuccessMessage(TITLE + o.getNome()));
			return this.find(o.getId());
		} catch (SQLException | InvalidIdException e) {
			conn.rollback();
			logger.error(getUpdateErrorMessage(TITLE, e.getMessage()));
			throw new SQLException(getUpdateErrorMessage(TITLE, e.getMessage()));
		}

	}

	@Override
	public Grupo delete(int id) throws SQLException {
		Connection conn = this.factory.getConn();
		String sql = "UPDATE grupos SET deleted_at = ? WHERE id = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			pst.setTimestamp(1, converterLocalDateTimeToTimestamp(LocalDateTime.now(ZONE_ID)));
			pst.setInt(2, id);
			pst.executeUpdate();
			conn.commit();
			logger.info(getDeleteSuccessMessage(TITLE));
			return this.find(id);
		} catch (SQLException | InvalidIdException e) {
			conn.rollback();
			logger.error(getDeleteErrorMessage(TITLE, e.getMessage()));
			throw new SQLException(getDeleteErrorMessage(TITLE, e.getMessage()));
		}

	}

	@Override
	public List<Grupo> findAll() throws SQLException {
		String sql = "SELECT * FROM grupos";
		Connection conn = null;
		List<Grupo> grupos = new ArrayList<>();
		conn = this.factory.getConn();
		try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
			while (rs.next())
				grupos.add(this.getFilledObject(rs));

		} catch (SQLException e) {
			logger.error(TITLE.concat(e.getMessage()));
			throw new SQLException(e.getMessage());
		}
		return grupos;

	}

	@Override
	public Grupo find(int id) throws SQLException, InvalidIdException {
		Connection conn = this.factory.getConn();
		Grupo g = null;
		String sql = "SELECT * FROM grupos WHERE id = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (!rs.next())
					throw new InvalidIdException(id);
				else
					g = this.getFilledObject(rs);
			}
			return g;
		} catch (SQLException e) {
			logger.error("findById:".concat(e.getMessage()));
			throw new SQLException(e.getMessage());
		}

	}

	@Override
	public int getLastId() throws SQLException {
		int id = 0;
		String sql = "SELECT max(id) as id FROM grupos ;";
		Connection conn = this.factory.getConn();
		try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {

			if (rs.next())
				id = rs.getInt("id");

			return id;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new SQLException(e.getMessage());
		}

	}

	@Override
	public Grupo getFilledObject(ResultSet rs) throws SQLException {
		Grupo g = new Grupo();
		g.setId(rs.getInt("id"));
		g.setNome(rs.getString("nome"));
		LocalDateTime date = rs.getTimestamp("created_at").toLocalDateTime();
		g.setCreatedAt(date);

		if (rs.getTimestamp("updated_at") != null) {
			date = rs.getTimestamp("updated_at").toLocalDateTime();
			g.setUpdatedAt(date);
		}
		if (rs.getTimestamp("deleted_at") != null) {
			date = rs.getTimestamp("deleted_at").toLocalDateTime();
			g.setDeletedAt(date);
		}
		return g;
	}

}
