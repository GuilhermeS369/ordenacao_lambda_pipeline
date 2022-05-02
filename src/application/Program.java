package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		String path = "D:\\MegaSync\\Docs e Projetos\\Programação\\Java\\testes\\in.txt";
		//FUNÇÃO PARA LEITURA DE ARQUIVO
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Employee> list = new ArrayList<>();//CRIADO LISTA DE EMPLOYEE
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.print("Enter Salary: ");
			Double limit = sc.nextDouble();
			//AQUI CRIAMOS UMA STREAM QUE FILTRA PELO SALARIO SENDO MENOR
			//QUE O VALOR INFROMADO, TRAZ OS EMAILS, ORGANIZA EM NOME E VOLTA PRA LISTA
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > limit)
					.map(p -> p.getEmail())
					.sorted()	
					.collect(Collectors.toList());
			
		
			//CRIAMOS OUTRA STREAM Q FILTRA PELO NOME M, TRAZ OS SALARIOS E SOMA.
			//LEMBREMOS Q X SÓ AUMENTA E Y SEMPRE MUDA COM O PROXIMO DA FILA
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x-> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
						
			//TRAZ TODO MUNDO
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", limit)+ ":");
			emails.forEach(System.out::println);
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
			
			
			
			
		} catch (IOException e) {//MEU LINDO CATCH AE
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}

}
