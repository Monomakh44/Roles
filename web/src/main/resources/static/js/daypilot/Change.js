function change() {
    const btn = document.getElementById("block-btn");
    if(btn.innerText === "Заблокировать") {
        btn.innerText = "Разблокировать";
    } else {
        btn.innerText = "Заблокировать";
    }
}