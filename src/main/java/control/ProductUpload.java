package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import javax.servlet.RequestDispatcher;
import model.bean.ProductBean;
import model.datasource.ProductDaoDataSource;

/**
 * Servlet implementation class ProductUpload
 */
@WebServlet("/ProductUpload")
@MultipartConfig()
public class ProductUpload extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	static String SAVE_DIR= "img";
	static ProductDaoDataSource prodDao;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	LocalDateTime now = LocalDateTime.now();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Collection<?> prodotti = (Collection<?>) request.getSession().getAttribute("Products");
		String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;
		ProductBean p1 = new ProductBean();
		
		DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
		prodDao= new ProductDaoDataSource(ds);
		
		String fileName= null;
		String message = "upload=/n";
		if(request.getParts() != null && request.getParts().size() > 0) 
		{
			for(Part part : request.getParts()) {
				fileName = extractFileName(part);
				//System.out.println("file name =" + fileName);
				if(fileName != null || !fileName.equals("")) {
					String fileExtension = getFileExtension(fileName);
					if (isValidFileType(fileExtension)) {
					  long fileSize = part.getSize();
					  if(fileSize <=5 * 1024 *1024) {
						  if(isValidMagicNumber(part)) {
							  part.write(savePath + File.separator + fileName);
							  //p1.setImg(fileName);
							  message = message + fileName + "\n";
						  } else  System.out.println("Errore: Il file non ha un magic number valido"); //request.setAttribute("error", "Errore: Il file non ha un magic number valido");
					  }  else  System.out.println("Errore: limite messimo consentito");//request.setAttribute("error", "Errore: Il file non ha un magic number valido");
					} else  System.out.println("Errore: tipo file sbagliato");//request.setAttribute("error", "Errore: Il file non ha un magic number valido");
				 } else  System.out.println("Errore: selezionare almeno un file"); //request.setAttribute("error", "Errore: Il file non ha un magic number valido");
				}
			}
		   String name = request.getParameter("name");
		   String description = request.getParameter("description");
		   Double price= Double.parseDouble(request.getParameter("price"));
		   int quantity= Integer.parseInt(request.getParameter("quantity"));
		   String type= request.getParameter("type");
		try {
			
			//System.out.println(p1.getImg());
			
				p1.setNome(name);
				p1.setDescrizione(description);
				p1.setCosto(price);
				p1.setDisponibilità(quantity);
				p1.setTipologia(type);
				prodDao.doSave(p1);
			} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TestDAO.jsp");
		dispatcher.forward(request, response);	
		}

	private String extractFileName(Part part) {
		// content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		//System.out.println(contentDisp);
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				//System.out.println("s =" + s);
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
        	return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }
	
	private boolean isValidFileType(String fileExtension) {
	        // Definire una lista di tipi di file consentiti
		    System.out.println("file extension =" + fileExtension);
			String[] allowedFileTypes = {"jpg", "jpeg", "png", "gif", "bmp"};
	        for (String type : allowedFileTypes) {
	            if (type.equals(fileExtension)) {
	                return true;
	            }
	        }
	        return false;
	    }
	
	 private boolean isValidMagicNumber(Part part) throws IOException {
	        // Definire i magic number per i file consentiti
	        byte[] jpgMagic = new byte[]{(byte)0xFF, (byte)0xD8, (byte)0xFF};
	        byte[] pngMagic = new byte[]{(byte)0x89, 'P', 'N', 'G'};
	        byte[] gifMagic = new byte[]{'G', 'I', 'F'};
	        byte[] bmpMagic = new byte[]{'B', 'M'};

	        // Leggere i primi byte del file per confrontarli con i magic number noti
	        try (InputStream inputStream = part.getInputStream()) {
	            byte[] fileHeader = new byte[4];
	            if (inputStream.read(fileHeader) != fileHeader.length) {
	                return false;
	            }

	            if (Arrays.equals(fileHeader, 0, 3, jpgMagic, 0, 3) ||
	                Arrays.equals(fileHeader, 0, 4, pngMagic, 0, 4) ||
	                Arrays.equals(fileHeader, 0, 3, gifMagic, 0, 3) ||
	                Arrays.equals(fileHeader, 0, 2, bmpMagic, 0, 2)) {
	                return true;
	            }
	        }

	        return false;
	    }
	
}
