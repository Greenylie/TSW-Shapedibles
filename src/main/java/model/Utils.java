package model;

import model.bean.ImageBean;
import model.bean.ProductBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

public class Utils {
    public static String getSquareImage(HttpServletRequest request, ProductBean shaker, String type) {
        Collection<?> images = shaker.getImages();
        Iterator<?> it = images.iterator();
        String image = request.getContextPath() + "/assets/images/products/fallback_square.jpg";

        while (it.hasNext()) {
            ImageBean img = (ImageBean) it.next();
            if (img.getImgIfString(type) != null) {
                image = request.getContextPath() + "/assets/images/products/" + img.getImg();
                break; // Exit loop once we find a matching image
            }
        }

        return image;
    }
}
