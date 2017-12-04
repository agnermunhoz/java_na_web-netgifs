package br.com.fiap.netgifs.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.rowset.serial.SerialBlob;

import br.com.fiap.netgifs.entity.Gif;
import br.com.fiap.netgifs.entity.Image;
import br.com.fiap.netgifs.entity.Session;
import br.com.fiap.netgifs.helper.GenericaDAO;
import br.com.fiap.netgifs.helper.GifDAO;
import br.com.fiap.netgifs.listener.LocalEntityManagerFactory;

public class TestImage {

	public static void main(String[] args) {
		new LocalEntityManagerFactory().contextInitialized(null);
		/*
		GenericaDAO<Image> imgDAO = new GenericaDAO<>(Image.class);
		
		Image img = new Image();
		
		String filePath = "C:\\tmp\\image.jgp";
		try {
			Path path = Paths.get(filePath);
			byte[] imgBytes = Files.readAllBytes(path);
			img.setFile(new SerialBlob(imgBytes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img.setFormat("jpeg");
			
		imgDAO.saveOrUpdate(img);
		*/
		GifDAO gifDAO = new GifDAO();
		GenericaDAO<Session> sessionDAO = new GenericaDAO<>(Session.class);
		Session session = sessionDAO.find(1);
		
		InputStream is = null;
		try {
			is = new FileInputStream("C:\\tmp\\image.gif");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gif gif = new Gif("Teste de Conversão", session, is);
		
		gifDAO.saveOrUpdate(gif);
		
		new LocalEntityManagerFactory().contextDestroyed(null);
	}

}
