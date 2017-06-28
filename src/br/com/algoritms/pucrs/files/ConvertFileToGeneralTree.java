package br.com.algoritms.pucrs.files;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import br.com.algoritms.pucrs.datastructures.GenericGeneralTree;

public class ConvertFileToGeneralTree {
	private GenericGeneralTree<Character> genericGeneralTree;
	private Path filePath;
	
	public ConvertFileToGeneralTree(Path filePath) {
		this.filePath = filePath;
		this.genericGeneralTree = new GenericGeneralTree<>();
		//Add the '*' as root node.
		this.genericGeneralTree.add('*', null);
	}
	
	private List<String> readLines() throws IOException{
		try(BufferedReader reader = Files.newBufferedReader(filePath,Charset.forName("utf8"))){
			String line;
			String[] linhaSeparada;
			String palavra;
			String significado;
			
			while ((line = reader.readLine()) != null) {
				linhaSeparada = line.split(";");
				palavra = linhaSeparada[0]line.trim().toLowerCase();
				significado = linhaSeparada[1];
				
				if(genericGeneralTree.containsRootChild(palavra.charAt(0))){
					
				}
			}
		}catch (IOException e) {
			throw new IOException();
		}
	}
}
