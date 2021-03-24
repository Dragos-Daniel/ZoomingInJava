package imageResizePack;

import java.awt.image.BufferedImage;

public class Consumer extends Thread { // clasa ce consuma informatia produsa de Producer
	private Buffer buffer;
	private BufferedImage pic;
	private Picture picture;
	private int [][] buffer_picture;
	
	public Consumer(Buffer b, BufferedImage pic, Picture picture){ // constructor Consumer
		this.buffer = b;
		this.picture = picture;
		this.pic = pic;
		System.out.println("Consumerul a fost creat");
	}
	
	public void run(){ // metoda de run a Consumer Threadului
		System.out.println("Consumerul incepe executia");
		int width = pic.getWidth(); //se salveaza inaltimea si latimea pozei
		int height = pic.getHeight();
		picture.setWidth(width);
		picture.setHeight(height);
		picture.setPicture(new BufferedImage(width,height,pic.getType()));
		for(int i = 0;i< 4; i++){ // secventa de citit 1/4 de informatie pe rand
			buffer_picture = buffer.get();
			picture.read(buffer_picture,(height/4*i),(height/4)*(i+1),width);
			System.out.println("Consumer gets: "+(i+1) + " sfert");
		}
		
		System.out.println("Consumer dead"); // mesaj ce anunta incheierea run-ului de la Consumer
	}
	public Picture getPicture(){
		return picture;
	}

}
