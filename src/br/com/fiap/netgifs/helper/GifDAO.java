package br.com.fiap.netgifs.helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Image;

public class GifDAO extends GenericaDAO<Gif> {

	public GifDAO() {
		super(Gif.class);
	}

	@Override
	public boolean saveOrUpdate(Gif reg) {
		try {
			if (reg.getInputStream() != null) {
				Image image = new Image();
				image.setFormat("gif");
				byte[] imgBytes = new byte[(int) reg.getInputStream().available()];
				reg.getInputStream().read(imgBytes);
				image.setFile(new SerialBlob(imgBytes));
				
				InputStream is = image.getFile().getBinaryStream();
				
				//Converter para png e reduzir a imagem
				
				BufferedImage img1 = ImageIO.read(is);
				BufferedImage img2 = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);//img1.getType());
				Graphics2D g2d = img2.createGraphics();
				g2d.drawImage(img1, 0, 0, 100, 100, null);
				g2d.dispose();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(img2 , "png", os);
				
				
				/*
				BufferedImage im1 = ImageIO.read(reg.getInputStream());
				BufferedImage im2 = new BufferedImage(100, 100, 
		                BufferedImage.TYPE_4BYTE_ABGR);
				im2.getGraphics().drawImage(im1, 0, 0, 100, 100, null);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(im2 , "png", os);
				*/
				
				Image mini = new Image();
				mini.setFormat("png");
				mini.setFile(new SerialBlob(os.toByteArray()));
				
				GenericaDAO<Image> imgDAO = new GenericaDAO<>(Image.class);
				imgDAO.saveOrUpdate(image);
				imgDAO.saveOrUpdate(mini);
				
				reg.setImage(image);
				reg.setMini(mini);
			}
			return super.saveOrUpdate(reg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
