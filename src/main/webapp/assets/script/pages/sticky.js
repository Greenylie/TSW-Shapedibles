let scrollTimeout;
let scrollPos;
let inputChangeTimeout;

function updateScroller() {
    const scrollY = window.scrollY;
    const viewportHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;
    const handle = document.querySelector(".handle");
    const line = document.querySelector(".line");

    scrollPos = Math.round((scrollY / (documentHeight - viewportHeight)) * 75);

    line.style.height = scrollPos + "vh"
    handle.style.opacity = line.style.opacity = "100%";

    clearTimeout(scrollTimeout);
    
    scrollTimeout = setTimeout(() => {
        if (scrollPos === 0) 
            handle.style.opacity = "0%";
        else
            line.style.opacity = "20%";
            handle.style.opacity = "40%";
    }, 300);
}

function searchInputChange(elSearchbar, elInputSearch, elButtonSearch) {
    
    elSearchbar.style.boxShadow = "0 0 15px rgba(255, 255, 255, 0.20),inset 0 0 30px rgba(0, 0, 0, 0.16)";
    
    setTimeout(() => {
        elSearchbar.style.boxShadow = "0 0 5px rgba(255, 255, 255, 0), inset 0 0 30px rgba(0, 0, 0, 0.16)";
    }, 400);
}

document.onreadystatechange = () => {
    if (document.readyState === "complete") {
        main();
    }
};

function main() {
    //Elements
    var elInputSearch = document.getElementById("inputSearch");
    var elSearchBar = document.getElementsByClassName("searchbar")[0];
    var elButtonSearch = document.getElementById("buttonSearch");
    
    
    window.addEventListener("scroll", updateScroller);
    window.addEventListener("resize", updateScroller);
    elInputSearch.addEventListener("input", () => 
        searchInputChange(elSearchBar, elInputSearch, elButtonSearch));
    elInputSearch.addEventListener("focus", () => {
        elButtonSearch.style.left = `calc(99% - ${elButtonSearch.offsetWidth}px)`;
    });
    elInputSearch.addEventListener("blur", () => {
        elButtonSearch.style.left = '1%';
    });
    updateScroller();
}

