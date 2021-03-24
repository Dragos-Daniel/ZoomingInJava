package imageResizePack;
import java.awt.image.BufferedImage;

public abstract class PictureAbstract implements PictureInterface { //clasa abstracta folosita pentru clasa 
	protected int width;
	protected int height;
	protected BufferedImage picture;
	//functii ce vor fi folosite in Picture
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract BufferedImage getPicture();
	public abstract void setWidth(int width);
	public abstract void setHeight(int height);
	public abstract void setPicture(BufferedImage Picture);
	
	
	PictureAbstract(){
		width = 0;
		height = 0;
		picture = null;
	}

}
