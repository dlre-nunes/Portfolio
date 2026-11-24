package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Projeto;

public class CadastroProjetos {
	
	public boolean CadastrarProjeto(Projeto projeto) throws Exception {
		
		String sql = """
					INSERT INTO project (titulo, descricao, link, tecnologia, data_criacao)
					VALUES (?, ?, ?, ?, ?)
				""";
		
		try(Connection connect = ConexaoDB.getConexao();
				PreparedStatement pStatement = connect.prepareStatement(sql)) {
				pStatement.setString(1, projeto.getTitulo());
				pStatement.setString(2, projeto.getDescricao());
				pStatement.setString(3, projeto.getLink());
				pStatement.setString(4, projeto.getTecnologias());
				pStatement.setString(5, projeto.getData_criacao());
				
				int registroCadastrado = pStatement.executeUpdate();
				return registroCadastrado > 0;
		}catch (SQLException e) {
			throw new SQLException("Erro ao cadastrar o  registro" + e.getMessage(), e);
		}
	}
	
	public void selecionarProjeto(Projeto projeto) throws Exception {
		String sql = "SELECT * FROM project WHERE id = ?";
		
		try(Connection conection = ConexaoDB.getConexao();
				PreparedStatement pStatement = connect.prepareStatement(sql)) {
				pStatement.setInt(1, projeto.getId());
			
			try(ResultSet rs = pStatement.executeQuery()) {
				if(rs.next()) {
					projeto.setId(rs.getInt("id"));
					projeto.setTitulo(rs.getString("titulo"));
					projeto.setDescriçao(rs.getString("descricao"));
					projeto.setLink(rs.getString("link"));
					projeto.setTecnologias(rs.getString("tecnologias"));
					projeto.setData_criacao(rs.getString("data_criacao"));
					
				}
			}catch (SQLException e) {
				throw new SQLException("Erro ao selecionar o registro");
			}
		}catch (SQLException e) {
			throw new SQLException("Erro ao consultar os dados");
		}
	}

	public ArrayList<Projeto> listarProjeto() throws Exception {
		String sql = "Select * FROM project";
		
		try(Connection connect = Conexao.getConexao();
				preparedStatement pStatement = connect.prepareCall(Statement(sql);
				ResultSet rs = pStatement.executeQuery()) {
					
				while(rs.next()) {
					Projeto projeto = new Projeto();
					
					projeto.setId(rs.getInt("id"));
					projeto.setTitulo(rs.getString("titulo"));
					projeto.setDescricao(rs.getString("descricao"));
					projeto.setLink(rs.getString("Link"));
					projeto.setTecnologias(rs.getString("tecnologias"));
					projeto.setData_criacao(rs.getString("data_criacao"));
					
					listarProjeto.add(projeto);
				}
				}catch (SQLException e) {
					throw new SQLException("Erro ao buscar o registro");
				}
				
				return listarProjeto;
	}
	
	public boolean alterarProjeto(Projeto projeto) throws Exception {
		
		String sql = """
						UPDATE project
						SET titulo = ?, descricao = ?, link = ?, tecnologias = ?, data_criacao = ?
						WHERE id = ? 
				""";
		
		try(Connection connect = ConexaoDB.getConexao();
				PreparedStatement pStatement = connect.prepareStatement(sql)) {
				pStatement.setString(1, projeto.getTitulo());
				pStatement.setString(2, projeto.getDescricao());
				pStatement.setString(3, projeto.getLink());
				pStatement.setString(4, projeto.getTecnologias());
				pStatement.setString(5, projeto.getData_criacao());
				pStatement.setInt(6, projeto.getId());
				
					int registroAtualizado = pStatement.executeUpdate();
					
					return registroAtualizado > 0;
				} catch (SQLException e) {
			throw new SQLException("Erro ao alterar o registro!");
		}
	}
	
	public boolean deletarProjeto(int id) throws Exception {
		String sql = "DELETE FROM project WHERE id = ";
		
		try (Connection connect = ConexaoDB.getConexao();
				PreparedStatement pStatement = connect.prepareStatement(sql)) {
				pStatement.setInt(1, id);
				int rowsAffect = pStatement.executeUpdate();
		
				return rowsAffect > 0;
		}catch (SQLException e) {
			throw new SQLException("Erro ao excluir o registro");
		}
	}
	}
