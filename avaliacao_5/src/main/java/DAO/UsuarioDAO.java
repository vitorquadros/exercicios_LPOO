package DAO;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends MainDAO {

    // Buscar todos os usuários
    public static List<Usuario> buscarUsuarios() {
        final String sql = "SELECT * FROM usuarios ORDER BY nome";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet resultSet = pstmt.executeQuery();
        ) {
            List<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {
                usuarios.add(resultSetToUsuario(resultSet));
            }

            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar um usuário pelo ID
    public static Usuario buscarUsuarioPorId(Integer id) {
        final String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            Usuario usuario = null;

            if (resultSet.next()) {
                usuario = resultSetToUsuario(resultSet);
            }

            resultSet.close();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar usuário(s) pelo nome
    public static List<Usuario> buscarUsuariosPorNome(String nome) {
        final String sql = "SELECT * FROM usuarios WHERE nome LIKE ? ORDER BY nome";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, nome.toLowerCase() + "%");
            ResultSet resultSet = pstmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {
                usuarios.add(resultSetToUsuario(resultSet));
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Inserir novo usuário no BD
    public static boolean criarUsuario(Usuario usuario) {
        final String sql = "INSERT INTO usuarios (nome, email, telefone) VALUES (?, ?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar usuário no BD
    public static boolean atualizarUsuario(Usuario usuario) {
        final String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setInt(4, usuario.getId());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletar usuário do BD
    public static boolean deletarUsuario(int id) {
        final String sql = "DELETE FROM usuarios WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            if (deleted == 0) {
                return false;
            } else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Converter result set
    private static Usuario resultSetToUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setTelefone(resultSet.getString("telefone"));

        return usuario;
    }
}
