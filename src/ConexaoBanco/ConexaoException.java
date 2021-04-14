package ConexaoBanco;

public class ConexaoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ConexaoException(String msg) {
        super(msg);
    }
}
