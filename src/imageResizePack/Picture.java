package imageResizePack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture extends PictureAbstract{ //clasa in care se definesc citire/scrierea si metoda Zero Order Hold
	private BufferedImage zoomed;
	public Picture(){
		super();
	}
	public Picture(BufferedImage picture){ //constructor cu parametri
		this.width = 0;
		this.height = 0;
		this.picture = picture;
	}
	
	public void read(int [][] buffer_image, int start, int finish, int col){//functie in care se citeste continutul pozei
		for(int i = start; i< finish; i++){
			for(int j=0;j < col; j++){
				picture.setRGB(j, i, buffer_image[i-start][j]);
			}
		}
	}
	public void write(File path) throws IOException{ // functie de scriere a continutului pozei
		if(this.picture != null){ // in cazul  in care poza are continut null se arunca exceptie
			//File output = new File(path);
			ImageIO.write(zoomed,"bmp",path);
			System.out.println(path);
			System.out.println("\nS-a scris in fisier cu succes");
		}
	}
	
	@Override
	public int getHeight(){
		return height;
	}
	
	@Override
	public int getWidth(){
		return width;
	}
	
	@Override
	public BufferedImage getPicture(){
		return picture;
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public void setHeight(int height){
		this.height = height;
	}
	
	@Override
	public void setPicture(BufferedImage picture){
		this.picture = picture;
	}
	 public BufferedImage modifyDimensions(int width, int height){ //metoda ce mareste dimensiunile pozei in scopul zoom-ului
		 return new BufferedImage((2* width)-1 , (2*height)-1, BufferedImage.TYPE_INT_RGB); 
	 }
	 private int mediePixeli(int pixel1, int pixel2) { //metoda de medie a doi pixeli  ce deserveste in metoda Zero Order Hold
	        return  (int) (((((pixel1) ^ (pixel2)) & 0xfffefefeL) >> 1) + ((pixel1) & (pixel2)));
	    }
	 public void zoomIn(){//metoda de Zoom In folosind Zero Order Hold
		 zoomed = modifyDimensions(picture.getWidth(),getHeight()); //in primul pas se maresc dimensiunile pozei
		 for(int i = 0; i < zoomed.getHeight(); i += 2) {
	            for(int j = 0; j < zoomed.getWidth(); j += 2) {
	                zoomed.setRGB(j, i, picture.getRGB(j / 2,i / 2));

	                if (j + 1 < zoomed.getWidth()) {
	                    zoomed.setRGB(j + 1, i, mediePixeli(picture.getRGB(j / 2, i / 2), picture.getRGB((j + 2) / 2, i / 2))); // intre doua coloane se adauga media pixelilor din acestea
	                }
	            }
	      }


	      for(int i = 1; i < zoomed.getHeight(); i += 2){
	          	for(int j = 0; j < zoomed.getWidth(); j++ ){
	                zoomed.setRGB(j, i, mediePixeli(picture.getRGB((j - 1) / 2, i / 2), picture.getRGB((j + 1) / 2, (i / 2))));
	            }
	        }
	       
	 }
	
	
	
	
	
	

}
