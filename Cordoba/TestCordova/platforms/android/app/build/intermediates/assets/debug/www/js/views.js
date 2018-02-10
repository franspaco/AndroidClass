

var stor = window.localStorage; 

var target = document.getElementById("target");

target.innerText = stor.getItem("data-value");