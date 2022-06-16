const addBtn = document.querySelector(".add-button");
const modal = document.querySelector(".modal");
const span = document.querySelector(".close-modal");
const btnForm = document.querySelector("#btnForm");

addBtn.addEventListener("click", () => {
    modal.style.display = "block";

    const form = document.querySelector(".modalForm");
    const submitForm = document.querySelector(".submit-modal");

    const name = document.querySelector("#name");
    const dueDate = document.querySelector("#due-date");
    const estimate = document.querySelector("#estimate");
    const type = document.querySelector("#task-type");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        if (name.value === "") {
            alert("Name field can't be empty!");
            return;
        }
        if (Date.parse(dueDate.value) < Date.now()) {
            alert("Due date must not be in the past!");
            return;
        }
        if (dueDate.value === "") {
            alert("Due date can't be empty!");
            return;
        }
        if (parseInt(estimate.value) < 0) {
            alert("Estimate time can't be negative!")
            return;
        }
        if (type.value === "none") {
            alert("Please select a type!");
            return;
        }

        let color = "purple";
        if (type.value.toUpperCase() === "HOME") {
            color = "blue";
        } else if (type.value.toUpperCase() === "HOBBY") {
            color = "green";
        }

        // const response = await fetch('/api/tasks');
        // let tasks = await response.json();

        let newTask = {
            type: type.value.toUpperCase(),
            color: color,
            name: name.value,
            dueDate: dueDate.value,
            estimate: (estimate.value === "" ? '0' : estimate.value),
        };

        // tasks.push(newTask);

        const request = await fetch("/api/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newTask)
        });

        modal.style.display = "none";
    });
});

span.addEventListener("click", () => {
    modal.style.display = "none";
})

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}