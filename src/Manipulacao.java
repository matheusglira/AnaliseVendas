import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Manipulacao {
	
	public static void main(String[] args){
		
		Path caminho = Paths.get("data/in/dados.txt");
		Path caminhoEscrito = Paths.get("data/out/saida.txt");
		ArrayList<String> dados = new ArrayList<String>();
		
		
		int cont = 0;
		try {
			
			byte[] texto = Files.readAllBytes(caminho);
			String leitura = new String(texto);
			String leituraAux[];
			String acumulador = null;
			String saida = null;
			String auxPiorVendedor = null;
			String splitPiorVendedor[];
			int qtdClientes = 0;
			int qtdVendedores = 0;
			int indexVenda = 0;
			int indexVendedor = 0;
			
			ArrayList<String> piorVendedor = new ArrayList<String>();
			String colecVendaMaisCara;
			String colecPiorVendedor;
			String colecaoValores = null;
			
			leituraAux = leitura.split("\n");
			
			int qtdDados = leituraAux.length;
			ArrayList<String> valorMaior = new ArrayList<String>();
			ArrayList<String> vendedorMaisCaro = new ArrayList<String>();
			
			while(cont < qtdDados){
				dados.add(leituraAux[cont].replace("ç", " | "));
				cont++;
			}
			
			cont = 0;
			
			while(cont < dados.size()){
				acumulador = dados.get(cont).substring(0, 3);
				
				if(acumulador.equals("002")){
					qtdClientes++;
				}
				
				if(acumulador.equals("001")){
					qtdVendedores++;
				}
				
				if(acumulador.equals("003")){
					int t1 = dados.get(cont).indexOf("[")+1;
					int t2 = dados.get(cont).indexOf("]");
					
					String vendas = dados.get(cont).substring(t1, t2);
					String vendasAux[];

					vendedorMaisCaro.add(dados.get(cont));
					
					piorVendedor.add(dados.get(cont));
					
					vendasAux = vendas.split(",");
					
					valorMaior.add(vendasAux[0]);
					valorMaior.add(vendasAux[2]);
					
					
				}
				
				cont++;
			}
			
			colecPiorVendedor = Collections.min(piorVendedor);
			colecVendaMaisCara  = Collections.max(valorMaior);
			indexVenda = valorMaior.indexOf(colecVendaMaisCara);
			
			indexVendedor = piorVendedor.indexOf(colecPiorVendedor);
			
			auxPiorVendedor = piorVendedor.get(indexVendedor);
			auxPiorVendedor = auxPiorVendedor.replace("|", ";");
			auxPiorVendedor = auxPiorVendedor.trim();
			splitPiorVendedor = auxPiorVendedor.split(";");
			
			colecaoValores = valorMaior.get(indexVenda-1);
						
			saida = "Total de clientes: " + qtdClientes + "\n" + 
					"Total de vendedores: " + qtdVendedores + "\n" +
					"Id da Venda mais cara: " + colecaoValores + "\n" +
					"Pior vendedor: " + splitPiorVendedor[3];
		
			Files.write(caminhoEscrito, saida.getBytes());
			
		}
		catch(Exception erro){
			System.out.println(erro);
		}
	}
}
