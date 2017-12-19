package br.com.fiap.netgifs.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.netgifs.entity.Image;
import br.com.fiap.netgifs.helper.GenericaDAO;

@WebServlet(name = "ViewImage", urlPatterns = "/private/viewimage")
public class ViewImage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strImageId = req.getParameter("imageid");
		Image image = null;
		if ((strImageId != null) && (!strImageId.isEmpty())) 
		try {
			int imageId = Integer.parseInt(strImageId);
			GenericaDAO<Image> imgDAO = new GenericaDAO<>(Image.class);
			image = imgDAO.find(imageId);
			if (image != null) {
				InputStream is = image.getFile().getBinaryStream();
				byte[] imgBytes = new byte[(int) image.getFile().length()];
				is.read(imgBytes);
				//resp.setContentType("image/"+image.getFormat());
				resp.setContentType(image.getFormat());
				ServletOutputStream out = resp.getOutputStream(); 
				out.write(imgBytes);
			}
		} catch (Exception e) {
			image = null;
			System.out.println("Imagem não encontrada");
			//e.printStackTrace();
		}
		if (image == null) 
		try {
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/image/notfound.png");
			byte[] imgBytes = new byte[is.available()];
			is.read(imgBytes);
			resp.setContentType("image/png");
			ServletOutputStream out = resp.getOutputStream(); 
			out.write(imgBytes);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	
}
