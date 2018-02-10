var stor = window.localStorage; 
var input = document.getElementById("input");

function onClick_btn1(){
    stor.setItem("data-value", input.value);
    window.location = "view1.html";
}

function onClick_btn2(){
    stor.setItem("data-value", input.value);
    window.location = "view2.html";
}

function onClick_btn3(){
    stor.setItem("data-value", input.value);
    window.location = "view3.html";
}