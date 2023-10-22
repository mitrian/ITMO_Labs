const $ = e=>document.getElementById(e);
let R = -100;
let Y = -100;
let X = NaN;
let eqs = 0.00000001;
let MaxX = 5.0;
let regex = /^(null|-?\d+(\.\d{0,10})?)?$/
function clear () {
    fetch(`https://se.ifmo.ru/~s367238/hit.php?t=1`, {
        method: "GET",
        headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" }
    })
    updateResponse('');
}

function onBtnClickR() {
    R = parseFloat(this.value);
}

function onBtnClickY() {
    Y = parseInt(this.value);
}

function inpX() {
    // if (isNaN(this.value) && this.value!='-'){
    //     this.value='';
    // }

    // if (this.value > 5) {
    //     this.value='';
    // }
    // if (this.value < -5){
    //     this.value='';
    // }
    // // if ((Math.abs(this.value-5) < 0.00000001) && (this.value!=5)){
    // //     this.value=''
    // // }
    // // if ((Math.abs(this.value+5) < 0.00000001) && (this.value!=5)){
    // //     this.value=''
    // // }
    // if (!regex.test(this.value)){
    //     this.value=''
    // }
    // X = this.value || X;
    if ((regex.test(this.value) && this.value<=5 && this.value>=-5) || this.value==='-'){
        X = this.value || X
    } else {
        this.value=''
    }
}

function errorMsg(msg) {
    $('erMsg').innerText = msg;
    $('erMsg').classList.replace('erMsg-hidden', 'erMsg-show');
}


function getCords(ev) {
    $('erMsg').classList.replace('erMsg-show', 'erMsg-hidden');
    if (isNaN($('inpX').value) || ($('inpX').value > 5) || ($('inpX').value < -5) || $('inpX').value=='' || $('inpX').value==-0){
        errorMsg("Неправильный X");
    }else if (Y==-100){
        errorMsg("Неправильный Y");
    }else if (R<0){
        errorMsg("Неправильное R");
    } else {
        fetch(`./hit.php?x=${X}&y=${Y}&r=${R}`, {
        method: "GET",
        headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" }
        }).then(r => r.text()).then(r => updateResponse(r));
    }
}

function updateResponse(res) {
    if (res==-1)
        errorMsg('Wrong validation');
    localStorage.setItem("session", res);
    $('result').innerHTML = res;
}

window.addEventListener('load', ()=>{

    document.getElementById("result").innerHTML = localStorage.getItem("session");

    $('button1').addEventListener('click', getCords);


    ["R1","R15","R2","R25","R3"].forEach((i)=>{
       $(i).addEventListener('click',onBtnClickR);
    });

    ["Y-4","Y-3","Y-2","Y-1","Y0","Y1","Y2","Y3","Y4"].forEach((i)=>{
        $(i).addEventListener('click',onBtnClickY);
    });

    
    $('inpX').addEventListener('input',inpX);

    $('button2').addEventListener('click',clear);

});

