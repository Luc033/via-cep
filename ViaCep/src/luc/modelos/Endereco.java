package luc.modelos;

public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(EnderecoViaCep enderecoViaCep) {
        if(enderecoViaCep.cep().length() == 9){
            this.cep = enderecoViaCep.cep();
            this.logradouro = enderecoViaCep.logradouro();
            this.complemento = enderecoViaCep.complemento();
            this.bairro = enderecoViaCep.bairro();
            this.cidade = enderecoViaCep.localidade();
            this.uf = enderecoViaCep.uf();
        }else {
            throw new ErroTamanhoCep("CEP fora do padrão");
        }

    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return "(Logradouro = "+logradouro+
                ", Complemento = "+complemento+
                ", Bairro = "+bairro+
                ", Cidade = "+cidade+
                ", UF = "+uf+
                ", CEP = "+cep+")";
    }
}
