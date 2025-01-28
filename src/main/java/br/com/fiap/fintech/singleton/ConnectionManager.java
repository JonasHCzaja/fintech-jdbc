package br.com.fiap.fintech.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe Singleton para gerenciar conexões com o banco de dados.
 * Utiliza o padrão Singleton para garantir que apenas uma instância
 * do gerenciador de conexões seja criada durante o ciclo de vida da aplicação.
 */

public class ConnectionManager {
	
	private static volatile ConnectionManager connectionManager;

	// URL, usuário e senha do banco de dados obtidos a partir de variáveis de ambiente
    private static final String DB_URL = System.getenv("DB_URL"); 
    private static final String DB_USER = System.getenv("DB_USER"); 
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    
    /**
     * Construtor privado para evitar instâncias externas da classe.
     * Verifica se as configurações de banco de dados estão definidas
     * e registra o driver JDBC necessário.
     */
    private ConnectionManager() {
        if (DB_URL == null || DB_USER == null || DB_PASSWORD == null) {
            throw new IllegalStateException("Configurações de banco de dados não encontradas! Verifique as variáveis de ambiente.");
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC Oracle não encontrado! Verifique se ele está no classpath.", e);
        }
    }

    
    
    // Método para obter a instância única do ConnectionManager.
    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            synchronized (ConnectionManager.class) {
                if (connectionManager == null) {
                    connectionManager = new ConnectionManager();
                }
            }
        }
        return connectionManager;
    }

   
    // Método responsável por criar e retornar uma conexão com o banco de dados.
    public Connection getConnection() {
        try {
        	
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados! Verifique as configurações.", e);
        }
    }
}
