const addBtn = document.querySelector(".add-button");
const modal = document.querySelector(".modal");
const span = document.querySelector(".close-modal");

const todayDate = new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+(new Date().getDate());

addBtn.addEventListener("click", () => {
    modal.style.display = "block";

    const submitBtn = document.querySelector(".submit-modal");

    const name = document.querySelector("#name");
    const dueDate = document.querySelector("#due-date");
    const estimate = document.querySelector("#estimate");
    const type = document.querySelector("#task-type");

    submitBtn.addEventListener("click", (e) => {
        if (dueDate.value < todayDate) {
            e.preventDefault();
            console.log(dueDate.value);
        }
    })
});

span.addEventListener("click", () => {
    modal.style.display = "none";
})

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}