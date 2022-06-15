const addBtn = document.querySelector(".add-button");
const modal = document.querySelector(".modal");
const span = document.querySelector(".close-modal");

addBtn.addEventListener("click", () => {
    modal.style.display = "block";

    const submitBtn = document.querySelector(".submit-modal");

    const name = document.querySelector("#name");
    const dueDate = document.querySelector("#due-date");
    const estimate = document.querySelector("#estimate");
    const type = document.querySelector("#task-type");

    submitBtn.addEventListener("click", (e) => {
        if (name.value === "") {
            e.preventDefault();
            alert("Name field can't be empty!");
        } else if (type.value === "none") {
            e.preventDefault();
            alert("Please select a type!");
        } else if (Date.parse(dueDate.value) < Date.now()) {
            e.preventDefault();
            alert("Due date must not be in the past!");
        } else if (dueDate.value === "") {
            e.preventDefault();
            alert("Due date can't be empty!");
        } else {
            modal.style.display = "none";
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