package DAO;

import Model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO extends MainDAO {

    // Buscar todos os usuários
    public static List<Motorista> buscarMotoristas() {
        final String sql = "SELECT * FROM motoristas ORDER BY nome";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet resultSet = pstmt.executeQuery();
        ) {
            List<Motorista> motoristas = new ArrayList<>();

            while (resultSet.next()) {
                motoristas.add(resultSetToMotorista(resultSet));
            }

            return motoristas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar um usuário pelo ID
    public static Motorista buscarMotoristaPorId(Integer id) {
        final String sql = "SELECT * FROM motoristas WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            Motorista motorista = null;

            if (resultSet.next()) {
                motorista = resultSetToMotorista(resultSet);
            }

            resultSet.close();
            return motorista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar usuário(s) pelo nome
    public static List<Motorista> buscarMotoristasPorNome(String nome) {
        final String sql = "SELECT * FROM motoristas WHERE nome LIKE ? ORDER BY nome";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, nome.toLowerCase() + "%");
            ResultSet resultSet = pstmt.executeQuery();
            List<Motorista> motoristas = new ArrayList<>();

            while (resultSet.next()) {
                motoristas.add(resultSetToMotorista(resultSet));
            }
            return motoristas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Inserir novo usuário no BD
    public static boolean criarMotorista(Motorista motorista) {
        final String sql = "INSERT INTO motoristas (nome, email, telefone, veiculo_id) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, motorista.getNome());
            pstmt.setString(2, motorista.getEmail());
            pstmt.setString(3, motorista.getTelefone());
            pstmt.setInt(4, motorista.getVeiculoId());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar usuário no BD
    public static boolean atualizarMotorista(Motorista motorista) {
        final String sql = "UPDATE motoristas SET nome = ?, email = ?, telefone = ?, veiculo_id = ? WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, motorista.getNome());
            pstmt.setString(2, motorista.getEmail());
            pstmt.setString(3, motorista.getTelefone());
            pstmt.setInt(4, motorista.getVeiculoId());
            pstmt.setInt(5, motorista.getId());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletar usuário do BD
    public static boolean deletarMotorista(int id) {
        final String sql = "DELETE FROM motoristas WHERE id = ?";

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
    private static Motorista resultSetToMotorista(ResultSet resultSet) throws SQLException {
        Motorista motorista = new Motorista();
        motorista.setId(resultSet.getInt("id"));
        motorista.setNome(resultSet.getString("nome"));
        motorista.setEmail(resultSet.getString("email"));
        motorista.setTelefone(resultSet.getString("telefone"));
        motorista.setVeiculoId(resultSet.getInt("veiculo_id"));

        return motorista;
    }
}
