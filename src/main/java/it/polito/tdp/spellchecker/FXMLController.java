package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	
	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLingua;

    @FXML
    private Label txtOutPut;

    @FXML
    private Label txtTempo;

    @FXML
    private TextArea txtTesto;

    @FXML
    private TextArea txtWrong;
    
    public void setModel(Dictionary model)
    {
    	this.model = model;
    }

    @FXML
    void doClearText(ActionEvent event) {

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	String text = txtTesto.getText().toLowerCase();
    	String lingua = cmbLingua.getValue();
    	String testo = text.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").toLowerCase();
    	
    	if(lingua == null)
    	{
    		txtOutPut.setText("ERRORE: choose the language");
    		txtTempo.setText("");
    		return;
    	}
    	
    	if(lingua.compareTo("Italiano") == 0)
    	{
    		model.loadDictionary("Italian.txt");
    	}
    	
    	if(lingua.compareTo("English") == 0)
    	{
    		model.loadDictionary("English.txt");
    	}
    	
    	//Creazione della lista da passare
    	String[] testoSplit = testo.split(" ");
    	List<String> inputTextList = new LinkedList<String>();
    	for(String p: testoSplit)
    		inputTextList.add(p);
    	
    	long start = System.currentTimeMillis( );
    	//Lista di parole esatte e corrette e preparazione output
    	List<RichWord> rw = model.spellCheckText(inputTextList);
    	long end = System.currentTimeMillis( );
    	
    	//Intervallo di tempo
    	long time = end-start;
    	
    	String output = "";
    	int cont =0;
    	for(RichWord rword : rw)
    		if(!rword.isCorrect())
    		{
    			cont++;
    			output+= rword.getParola()+"\n";
    		}
    	txtWrong.setText(output);
    	txtOutPut.setText("The text contains "+cont+" errors");
    	txtTempo.setText("Spell check completed in "+time+" seconds");
    }

    @FXML
    
    void initialize() {
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutPut != null : "fx:id=\"txtOutPut\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingua.getItems().clear();
        
        cmbLingua.getItems().add("Italiano");
        cmbLingua.getItems().add("English");
    }
    

}