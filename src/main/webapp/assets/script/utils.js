var ImageUtils;
ImageUtils = {
    getImageWithStringInName: function (images,string) {
        /*
        images is an array of ImageBean objects, the img name is stored in the img property
        string is the string to search for in the img name
         */
        for (let i = 0; i < images.length; i++) {
            if (images[i].img.includes(string)) {
                return images[i].img;
            }
        
        }
    }
}