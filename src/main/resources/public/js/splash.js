document.onreadystatechange = () => {
    if (document.readyState == "complete") {
       let elem = document.getElementById("pageLoader");
       if(elem != undefined){
           elem.classList.add('page-loader-loaded');
           setTimeout(()=>{elem.classList.add('page-loader-complete')}, 1000);
           
       }
    } 
}