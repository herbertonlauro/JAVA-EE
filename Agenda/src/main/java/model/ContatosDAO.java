package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ContatosDAO.
 */
public class ContatosDAO {
	

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbcontatos?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "1517";
	
    /**
     * Conectar.
     *
     * @return the connection
     */
    private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	
	/**
	 * Inserir cadastro.
	 *
	 * @param SetContatoModel the set contato model
	 */
	public void inserirCadastro(JavaModel SetContatoModel) {
		String create = "insert into meuscontatos (nome,fone,email) value (?,?,?)";
		try {
			
			Connection con = conectar();
			
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, SetContatoModel.getNome());
			pst.setString(2, SetContatoModel.getFone());
			pst.setString(3, SetContatoModel.getEmail());
            pst.executeUpdate();
            
            con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	} 
	
	/**
	 * Listar cadastro.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaModel> listarCadastro() {
		ArrayList<JavaModel> Arrcontatos = new ArrayList<>();
		String read = "select * from meuscontatos order by nome";
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet sr = pst.executeQuery();
			while (sr.next()) {
				String idcon = sr.getString(1);
				String nome = sr.getString(2);
				String fone = sr.getString(3);
				String email = sr.getString(4);
				Arrcontatos.add(new JavaModel(idcon, nome, fone, email));
			}
			con.close();
			return Arrcontatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	 
	
	/**
	 * Seleciona cadastro.
	 *
	 * @param SetContatoModel the set contato model
	 */
	public void selecionaCadastro(JavaModel SetContatoModel) {
		String seleciona = "select * from meuscontatos where idcon =?";
		try {
			 
			Connection con = conectar();			 
			PreparedStatement pst = con.prepareStatement(seleciona);			
			pst.setString(1, SetContatoModel.getIdcon());
			ResultSet sr = pst.executeQuery();
			while (sr.next()) {
				SetContatoModel.setIdcon(sr.getString(1));
				SetContatoModel.setNome(sr.getString(2));
				SetContatoModel.setFone(sr.getString(3));
				SetContatoModel.setEmail(sr.getString(4));
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param SetContatoModel the set contato model
	 */
	public void alterarContato(JavaModel SetContatoModel) {
		String update = "update meuscontatos set nome=?,fone=?,email=?  where idcon=?";
		try {
			Connection con = conectar();			
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, SetContatoModel.getNome());
			pst.setString(2, SetContatoModel.getFone());
			pst.setString(3, SetContatoModel.getEmail());
			pst.setString(4, SetContatoModel.getIdcon());
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param SetContatoModel the set contato model
	 */
	public void deletarContato(JavaModel SetContatoModel) {
		String delete  = "delete from meuscontatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, SetContatoModel.getIdcon());
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
