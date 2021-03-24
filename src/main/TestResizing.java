package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

import imageResizePack.*;

public class TestResizing {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		File source, destination;
		if(args.length==0){
			Scanner scan=new Scanner(System.in);
			System.out.println("Introduceti sursa: ");
			String sourcePath = scan.nextLine(); //sursa imaginii
			source = new File(sourcePath); // se citeste sursa imaginii
			System.out.println("Introduceti destinatia: ");
			String destinationPath = scan.nextLine();//destinatia imaginii
			destination = new File(destinationPath); // se citeste desinatia
			scan.close();
		}
		else{
			source = new File(args[0]);
			destination = new File(args[1]);
		}
		BufferedImage b1 = null;		// initializarea campurilor pozei
		Picture picture1 = new Picture();
        b1 = ImageIO.read(source);
		Buffer b2 = new Buffer(b1.getWidth(),b1.getHeight());
		Producer p1 = new Producer(b2,b1); //producerul are taskul de a citii imaginea
		Consumer c1 = new Consumer(b2,b1,picture1); // consumerul are task -ul de a scrie imaginea
		
		p1.start(); // start producer thread
		c1.start(); // start consumer thread
		p1.join(); // join la cele doua threaduri pentru a avea secventa de citire/scriere
		c1.join();
		picture1.zoomIn(); //apelarea metodei de zoom a pozei
		picture1.write(destination); // scrierea pozei dupa zoom in destinatia dorita
	}

}
