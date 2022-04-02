package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;


public class Dictionary {
	
	List<String> dizionario;
	
	public Dictionary() 
	{
		dizionario = new ArrayList<String>();
	}

	public void loadDictionary(String language)
	{
		try {
			FileReader fr = new FileReader(language);
			BufferedReader br = new BufferedReader(fr); 
			String word;
			while ((word = br.readLine()) != null) 
			{
				// Aggiungere parola alla struttura dati
				this.dizionario.add(word);
			}
			br.close();
			
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		
		Collections.sort(this.dizionario);
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList)
	{
		List<RichWord> rw = new ArrayList<RichWord>();
		
		for(String p: inputTextList)
		{
			if(dizionario.contains(p))
				rw.add(new RichWord(p, true));
			else 
				rw.add(new RichWord(p, false));
		}
		
		return rw;
		
	}
	

}
