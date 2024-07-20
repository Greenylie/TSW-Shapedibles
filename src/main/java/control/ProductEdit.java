package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import model.bean.ImageBean;
import model.bean.InfoBean;
import model.bean.NutritionTableBean;
import model.bean.ProductBean;
import model.dao.INutritionTableDao;
import model.datasource.ImageDaoDataSource;
import model.datasource.InfoDaoDataSource;
import model.datasource.NutritionTableDaoDataSource;
import model.datasource.ProductDaoDataSource;

/**
 * Servlet implementation class ProductEditControl
 */
@WebServlet("/ProductEdit")
@MultipartConfig()
public class ProductEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR= "assets\\images\\products";
	static ProductDaoDataSource prodDao;
	static ImageDaoDataSource imgDao;
	static InfoDaoDataSource infDao;
	static INutritionTableDao nutDao;
	Collection<String> images = new ArrayList<>();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	LocalDateTime now = LocalDateTime.now();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductEdit() {
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
		String savePath = request.getServletContext().getRealPath("") + SAVE_DIR;
		
		InfoBean beanIn = new InfoBean();
		InfoBean beanIn2 = new InfoBean();
		NutritionTableBean beanNt = new NutritionTableBean();
		
		
		DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
		prodDao= new ProductDaoDataSource(ds);
		imgDao= new ImageDaoDataSource(ds);
		infDao= new InfoDaoDataSource(ds);
		nutDao= new NutritionTableDaoDataSource(ds);
		
		String action= request.getParameter("action");
		
		if(action!=null && action.equalsIgnoreCase("edit")) 
		{
		String fileName;
			String message = "upload=/n";
			if(request.getParts() != null && request.getParts().size() > 0) 
			{
				for(Part part : request.getParts()) {
					fileName = extractFileName(part);
					if(fileName != null || !fileName.equals("")) {
						String fileExtension = getFileExtension(fileName);
						if (isValidFileType(fileExtension)) {
						  long fileSize = part.getSize();
						  if(fileSize <=5 * 1024 *1024) {
							  if(isValidMagicNumber(part)) {
								  part.write(savePath + File.separator + fileName);
								  images.add(fileName);
								  message = message + fileName + "\n";
							  } else  request.setAttribute("error", "Errore: Il file non ha un magic number valido"); 
						  }  else  request.setAttribute("error", "Errore: Il file ha taglia troppo grande");
						} else  request.setAttribute("error", "Errore: Il file non ha un estensione valida"); 
					 } else  request.setAttribute("error", "Errore: file mancante"); 
					}
				}
		}
		   
		try {
			
			ProductBean product= prodDao.doRetrieveByKey(Integer.parseInt(request.getParameter("product")));
			InfoBean info = infDao.doRetrieveByKey(product.getInfoCorrenti());
			NutritionTableBean nutritionTable = nutDao.doRetrieveByKey(product.getCodice());
			request.removeAttribute("productE");
			request.setAttribute("productE", product);
			request.removeAttribute("infoE");
			request.setAttribute("infoE", info);
			request.removeAttribute("nutritionTableE");
			request.setAttribute("nutritionTableE", nutritionTable);
			
			
			
		if(action!=null && action.equalsIgnoreCase("edit")) 
			{
			
				beanIn.setNome(request.getParameter("name"));
				beanIn.setDescrizione(request.getParameter("description"));
				beanIn.setCosto(Double.parseDouble(request.getParameter("price")));
				beanIn.setDisponibilità(Integer.parseInt(request.getParameter("quantity")));
				beanIn.setTipologia(request.getParameter("type"));
				infDao.doSave(beanIn);
			
				beanIn2=infDao.doRetrieveByName(request.getParameter("name"));
			
				prodDao.doUpdateInfo(product.getCodice(), beanIn2.getCodice());
				nutDao.doDelete(product.getCodice());
				
				beanNt.setCodiceProdotto(product.getCodice());
				beanNt.setEnergia(Integer.parseInt(request.getParameter("cal")));
				beanNt.setGrassi(Double.parseDouble(request.getParameter("fats")));
				beanNt.setGrassiSaturi(Double.parseDouble(request.getParameter("satFats")));
				beanNt.setCarboedrati(Double.parseDouble(request.getParameter("carbs")));
				beanNt.setZucherri(Double.parseDouble(request.getParameter("sugars")));
				beanNt.setFibre(Double.parseDouble(request.getParameter("fibers")));
				beanNt.setProteine(Double.parseDouble(request.getParameter("protein")));
				beanNt.setSale(Double.parseDouble(request.getParameter("salt")));
			
				nutDao.doSave(beanNt);
				
				Collection<?> prodImages = imgDao.doRetrieveByProduct(product.getCodice());
				Iterator<?> itp = prodImages.iterator();
				while(itp.hasNext()) {			
					ImageBean beanI = (ImageBean) itp.next();
					imgDao.doDelete(beanI.getNumImage(), beanI.getCodiceProdotto()); 
				}
				
				Iterator<?> it = images.iterator();
				while(it.hasNext()) {
					
					ImageBean beanI = new ImageBean();
					beanI.setCodiceProdotto(product.getCodice());
					beanI.setImg((String) it.next());
					imgDao.doSave(beanI); 
				}
				
			 }
			} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage());
				request.setAttribute("error",  "Error: c'è stato un errore nel elaborazione del ordine, assicurarsi di inserire i campi corretamente.");
			} 
		    if(action!=null && action.equalsIgnoreCase("edit")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin/productAdmin.jsp");
				dispatcher.forward(request, response);
		    } else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin/productEdit.jsp");
			dispatcher.forward(request, response); }
		}
	
	
	private String extractFileName(Part part) {
		// content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		System.out.println(contentDisp);
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				System.out.println("s =" + s);
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