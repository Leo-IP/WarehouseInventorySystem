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
        alert("Product Code must be filled out");
        return false;
    }
}

function validateTransferForm(){
    const x = parseInt(document.getElementById("productTransferQty").value);
    const max = parseInt(document.getElementById("maxProductTransferQty").value);
    console.log("max value:" + max);
    const regex=/^[0-9]*$/
    // if(x.match(regex)){
    //     alert("Your input is not valid")
    //     return false;
    // }

    if(x < 1) {
        alert("Make sure the qty is larger than 0")
        return false;
    }

    if(x > max){
        alert("Make sure the qty is matched with the current inventory status: qty=" + max)
        return false;
    }else{
        return true;
    }

}