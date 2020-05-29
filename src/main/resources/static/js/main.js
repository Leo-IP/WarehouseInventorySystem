// function searchProduct(){
//     const currentUrl = window.location.href.replace("?", "");
//     console.log(currentUrl);
//     const url = currentUrl + "/" + document.getElementById("product_code").value;
//     console.log(url);
//     const search_form = document.getElementById('search_form');
//     search_form.action = url;
// }

function validateForm(){
    const x = document.getElementById("product_code").value;
    if(x.trim() == ""){
        alert("Product Code must be filled out")
        return false;
    }
}