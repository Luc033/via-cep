import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import luc.modelos.Endereco;
import luc.modelos.EnderecoViaCep;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner ler = new Scanner(System.in);
        String busca = "";
        List<Endereco> enderecos = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("===================================");
        System.out.println("        .xX BUSCA CEP Xx.");
        System.out.println("===================================");
        System.out.println("Bem vindo!");
        while (!busca.equalsIgnoreCase("sair")){
            System.out.println("Digite um CEP ou 'sair' para encerrar o programa:");
            busca = ler.next();
            if (busca.equalsIgnoreCase("sair")){
                break;
            }
            String endereco = "http://viacep.com.br/ws/"+busca.replace(" ","+")+"/json/";
            try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(endereco)).build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            EnderecoViaCep endViaCep = gson.fromJson(json, EnderecoViaCep.class);
            Endereco end = new Endereco(endViaCep);
            System.out.println("Objeto da classe Endereco recebendo json: "+end);
            enderecos.add(end);
            }catch (Exception e){
                System.out.println("Rapaaaaiz!");
            }
        }
        if (enderecos.size() == 0) {
            System.out.println("Programa encerrado!");
        }else {
            System.out.println(enderecos.toString());
            FileWriter file = new FileWriter("MyCep.json");
            file.write(gson.toJson(enderecos));
            file.close();
            System.out.println("Programa encerrado!");
        }
    }
}