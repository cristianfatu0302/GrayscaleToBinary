package packTest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import packWork.*;

public class Main {
	public static void main(String[] args) throws IOException{
		
		String inputFile = null;
		String outputFilePath = null; 
		Buffer buffer = new Buffer(); //reprezinta legatura dintre consumer si producer
		
		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream(out, 1555254);
		
		if(args.length==0){
		
		try { //efectuez citirea de la tastatura a fisierului in care va fi poza prelucrata

			InputStreamReader isr = new InputStreamReader(System.in); 
			BufferedReader br = new BufferedReader(isr);
			System.out.println("Numele pozei dupa prelucrare(.bmp):");
			outputFilePath = (br.readLine()).toString();

			while (!outputFilePath.endsWith(".bmp")) { //conditie ca fisierul sa fie de tip .bmp
				System.out.println("Fisierul trebuie sa fie de tip .bmp");
				outputFilePath = (br.readLine()).toString();
			}
		}
		//tratarea exceptiei
		catch (IOException e) {
			System.out.println("Fisierul nu este gasit!!");
			e.printStackTrace();
		}
		
		ReadImage reader = new ReadImage();
		//citirea numelui fisierului de la tastatura apeland metoda ReadFile()
		inputFile = reader.ReadFile();
		}
		else {
			inputFile = args[0];
			outputFilePath = args[1];
		}
		GrayscaleToBinary img = new GrayscaleToBinary(inputFile);

		//creez producerul
		Producer producer = new Producer(img,  buffer);
		producer.start();
	
		//creez consumerul
		Consumer consumer = new Consumer(img, buffer, out);
		consumer.start();
		
		WriterResults writerResults = new WriterResults(outputFilePath, in, img);
		writerResults.start();
		
	}
}
