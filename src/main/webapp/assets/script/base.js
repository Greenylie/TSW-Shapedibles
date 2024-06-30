function updateScroller() {
    const scrollY = window.scrollY;
    const viewportHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    const scrollPos = Math.round((scrollY / (documentHeight - viewportHeight)) * 75);

    document.querySelector(".line").style.height = scrollPos + "vh"
    console.log(document.querySelector(".line").style.height)

    if (viewportHeight + window.scrollY > 1.2 * viewportHeight) {
        //WIP
    } else {
        //WIP
    }
}

document.onreadystatechange = () => {
    if (document.readyState === "complete") {
        main();
    }
};

function main() {
    window.addEventListener("scroll", updateScroller);
    window.addEventListener("resize", updateScroller);

    document.querySelector(".handle").addEventListener("click")
    updateScroller();
}

