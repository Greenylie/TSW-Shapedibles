var ImageUtils;
ImageUtils = {
    getImageWithStringInName: function (contextPath, images, string) {
        /*
        images is an array of ImageBean objects, the img name is stored in the img property
        string is the string to search for in the img name
         */
        let baseUrl = contextPath + "assets/images/products/";
        let finalUrl;
        for (let i = 0; i < images.length; i++) {
            if (images[i].img.includes(string)) {
                finalUrl = baseUrl + images[i].img;
                break;
            }
        }
        let image = new Image();
        image.src = finalUrl;
        if (image.width === 0)
            finalUrl = baseUrl + "fallback_" + string + ".jpg";

        return finalUrl;
    }
}