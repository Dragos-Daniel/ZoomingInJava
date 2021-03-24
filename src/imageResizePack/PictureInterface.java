package imageResizePack;

import java.awt.image.BufferedImage;

public interface PictureInterface { // interfata folosita la clasa Picture
	public abstract int getHeight(); //getteri pentru latime si inaltime
	public abstract int getWidth();
	public abstract BufferedImage getPicture(); // getter pentru imagine
	public abstract void setWidth(int width); // setter pentru imagine si latime/inaltime
	public abstract void setHeight(int height);
	public abstract void setPicture(BufferedImage Picture);

}
