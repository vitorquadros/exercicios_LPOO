package DAO;

import Model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends MainDAO {

    // Buscar todos os veiculos
    public static List<Veiculo> buscarVeiculos() {
        final String sql = "SELECT * FROM veiculos ORDER BY tipo";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet resultSet = pstmt.executeQuery();
        ) {
            List<Veiculo> veiculos = new ArrayList<>();

            while (resultSet.next()) {
                veiculos.add(resultSetToVeiculo(resultSet));
            }

            return veiculos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar um usuário pelo ID
    public static Veiculo buscarVeiculoPorId(Integer id) {
        final String sql = "SELECT * FROM veiculos WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            Veiculo veiculo = null;

            if (resultSet.next()) {
                veiculo = resultSetToVeiculo(resultSet);
            }

            resultSet.close();
            return veiculo;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar usuário(s) pelo nome
    public static List<Veiculo> buscarVeiculosPorAno(String ano) {
        final String sql = "SELECT * FROM veiculos WHERE ano_fabricacao LIKE ? ORDER BY tipo";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, ano.toLowerCase() + "%");
            ResultSet resultSet = pstmt.executeQuery();
            List<Veiculo> veiculos = new ArrayList<>();

            while (resultSet.next()) {
                veiculos.add(resultSetToVeiculo(resultSet));
            }
            return veiculos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Inserir novo usuário no BD
    public static boolean criarVeiculo(Veiculo veiculo) {
        final String sql = "INSERT INTO veiculos (tipo, placa, ano_fabricacao) VALUES (?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, veiculo.getTipo());
            pstmt.setString(2, veiculo.getPlaca());
            pstmt.setString(3, veiculo.getAnoFabricacao());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar usuário no BD
    public static boolean atualizarVeiculo(Veiculo veiculo) {
        final String sql = "UPDATE veiculos SET tipo = ?, placa = ?, ano_fabricacao = ? WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, veiculo.getTipo());
            pstmt.setString(2, veiculo.getPlaca());
            pstmt.setString(3, veiculo.getAnoFabricacao());
            pstmt.setInt(4, veiculo.getId());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletar usuário do BD
    public static boolean deletarVeiculo(int id) {
        final String sql = "DELETE FROM veiculos WHERE id = ?";

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
    private static Veiculo resultSetToVeiculo(ResultSet resultSet) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(resultSet.getInt("id"));
        veiculo.setTipo(resultSet.getString("tipo"));
        veiculo.setPlaca(resultSet.getString("placa"));
        veiculo.setAnoFabricacao(resultSet.getString("ano_fabricacao"));
        // veiculo.setMotorista(MotoristaDAO.buscarMotoristaPorVeiculo(resultSet.getInt("id")));

        return veiculo;
    }
}
