package DAO;

import Model.Corrida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorridaDAO extends MainDAO {

    // Buscar todas as corridas
    public static List<Corrida> buscarCorridas() {
        final String sql = "SELECT * FROM corridas ORDER BY preco";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet resultSet = pstmt.executeQuery();
        ) {
            List<Corrida> corridas = new ArrayList<>();

            while (resultSet.next()) {
                corridas.add(resultSetToCorrida(resultSet));
            }

            return corridas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar uma corrida pelo ID
    public static Corrida buscarCorridaPorId(Integer id) {
        final String sql = "SELECT * FROM corridas WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            Corrida corrida = null;

            if (resultSet.next()) {
                corrida = resultSetToCorrida(resultSet);
            }

            resultSet.close();
            return corrida;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar corrida(s) por data de ínicio
    public static List<Corrida> buscarCorridasPorData(String data) {
        final String sql = "SELECT * FROM corridas WHERE data_inicio LIKE ? ORDER BY preco";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, "%" + data + "%");
            ResultSet resultSet = pstmt.executeQuery();
            List<Corrida> corridas = new ArrayList<>();

            while (resultSet.next()) {
                corridas.add(resultSetToCorrida(resultSet));
            }
            return corridas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Inserir uma nova corrida no BD
    public static boolean criarCorrida(Corrida corrida) {
        final String sql = "INSERT INTO corridas (tipo_pagamento, detalhes_pagamento, data_inicio, preco, usuario_id, motorista_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, corrida.getTipoPagamento());
            pstmt.setString(2, corrida.getDetalhesPagamento());
            pstmt.setString(3, corrida.getDataInicio());
            pstmt.setDouble(4, corrida.getPreco());
            pstmt.setInt(5, corrida.getUsuarioId());
            pstmt.setInt(6, corrida.getMotoristaId());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar corrida no BD
    public static boolean atualizarCorrida(Corrida corrida) {
        final String sql = "UPDATE corridas SET tipo_pagamento = ?, detalhes_pagamento = ?, data_inicio = ?, preco = ?, usuario_id = ?, motorista_id = ? WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, corrida.getTipoPagamento());
            pstmt.setString(2, corrida.getDetalhesPagamento());
            pstmt.setString(3, corrida.getDataInicio());
            pstmt.setDouble(4, corrida.getPreco());
            pstmt.setInt(5, corrida.getUsuarioId());
            pstmt.setInt(6, corrida.getMotoristaId());
            pstmt.setInt(7, corrida.getId());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletar corrida do BD
    public static boolean deletarCorrida(int id) {
        final String sql = "DELETE FROM corridas WHERE id = ?";

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
    private static Corrida resultSetToCorrida(ResultSet resultSet) throws SQLException {
        Corrida corrida = new Corrida();
        corrida.setId(resultSet.getInt("id"));
        corrida.setTipoPagamento(resultSet.getString("tipo_pagamento"));
        corrida.setDetalhesPagamento(resultSet.getString("detalhes_pagamento"));
        corrida.setDataInicio(resultSet.getString("data_inicio"));
        corrida.setPreco(resultSet.getDouble("preco"));
        corrida.setUsuarioId(resultSet.getInt("usuario_id"));
        corrida.setMotoristaId(resultSet.getInt("motorista_id"));

        return corrida;
    }
}