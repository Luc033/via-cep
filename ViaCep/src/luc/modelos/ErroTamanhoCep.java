package luc.modelos;

public class ErroTamanhoCep extends RuntimeException{
    private String mensagem;

    public ErroTamanhoCep(String message) {
        this.mensagem = message;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
