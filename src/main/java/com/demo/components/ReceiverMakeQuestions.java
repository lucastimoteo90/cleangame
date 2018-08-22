package com.demo.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.demo.domain.GitClone;
import com.demo.domain.MediumRoom;
import com.demo.domain.PMDErrors;
import com.demo.domain.Question;
import com.demo.repositories.PMDErrorsRepository;
import com.demo.security.UserSS;
import com.demo.services.MediumRoomService;
import com.demo.services.PMDErrorService;
import com.demo.services.QuestionService;
import com.opencsv.CSVReader;;

@Component
public class ReceiverMakeQuestions {
	private GitClone gitClone;
	private String dirGit;
    
	
	
		
	@Autowired
	private MediumRoomService mediumRoomService;

	@Autowired
	private QuestionService questionService;
	
	@JmsListener(destination = "makeQuestions", containerFactory = "myFactory")
	public void mainMakeQuestions(GitClone gitClone) {
		this.setGitClone(gitClone);
		mediumRoomService.setInExecutionMakeQuestionStatus(this.getGitClone());
		
		System.out.println("GERANDO QUESTÕES <" + gitClone.getRoom().getGit() + ">");
		/*Refatorar...*/
		this.getGitClone().setRoom(mediumRoomService.findById(this.getGitClone().getRoom().getId()));
		//this.getGitClone().setRoom(mediumRoomService.findById(48));
		System.out.println("TOTAL DE ERROS NO PROJETO:" + this.getGitClone().getRoom().getPmdErrors().size());
    	makeQuestions();		
		mediumRoomService.setCompleteMakeQuestionStatus(this.getGitClone());
		
	}
	
	private void makeQuestions(){
		//Percorre todos os erros encontrados pelo pmd
		System.out.println("PERCORRER ERROS<" + gitClone.getRoom().getGit() + ">");
		int x = 0;
		for(PMDErrors pmderror : this.getGitClone().getRoom().getPmdErrors()) {
			Question question = new Question();
			question.setCode(readCode(pmderror.getFile_dir()));
			question.setAsk("Existe um badsmell proximo a linha "+pmderror.getLine() +". Qual é esse badsmell?");
			question.setCorrect(pmderror.getDescription());
			question.setFilename(pmderror.getFile_dir().substring(48, pmderror.getFile_dir().length()));
			question.setFake1("fake1");
			question.setFake2("fake1");
			question.setFake3("fake1");
			
			question.setTip(pmderror.getRule());
			question.setTip(pmderror.getRule_set());
			
			question.setRoom(this.getGitClone().getRoom());
			
			questionService.insert(question);
			System.out.println("PERCORRER ERROS<" + gitClone.getRoom().getGit() + ">");
			
			if(x++ > 100)
				 break;
			
		}
	}
	
	
	
	private String readCode(String file){
		String fileTxt = "";
	
		System.out.println("LER ARQUIVO <" + gitClone.getRoom().getGit() + ">");
		
		try {
			 FileReader arq = new FileReader(file);
		
			 BufferedReader lerArq = new BufferedReader(arq);
			 String linha = lerArq.readLine(); // lê a primeira linha
		      // a variável "linha" recebe o valor "null" quando o processo
		      // de repetição atingir o final do arquivo texto
			 fileTxt = linha;
			 while (linha != null) {
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		        fileTxt =  fileTxt + linha+"\n";
		     }
		     arq.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileTxt;
	}
	
	
	public GitClone getGitClone() {
		return gitClone;
	}

	public void setGitClone(GitClone gitClone) {
		this.gitClone = gitClone;
	}

	public String getDirGit() {
		return dirGit;
	}

	public void setDirGit(String dirGit) {
		this.dirGit = dirGit;
	}
	
	
}
