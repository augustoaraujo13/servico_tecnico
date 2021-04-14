package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conn = null;
    public static String banco = "jdbc:mysql://localhost:3306/serviço_tecnico?useSSL=false";
    public static String login = "root";
    public static String senha = "12345";

    public Conexao() {

    }

    public static Connection abrirBanco() {

        if (conn == null) {

            try {
                return conn = DriverManager.getConnection(banco, login, senha);

            } catch (SQLException e) {
                throw new ConexaoException(e.getMessage());
            }
        }
        return conn;
    }

    public static Connection fecharBanco() {

        if (conn != null) {

            try {
                conn.close();
            } catch (SQLException e) {
                throw new ConexaoException(e.getMessage());
            }
        }
        return conn;
    }

}
