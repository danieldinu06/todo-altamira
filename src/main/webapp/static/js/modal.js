const addBtn = document.querySelector(".add-button");
const modal = document.querySelector(".modal");
const span = document.querySelector(".close-modal");
const btnForm = document.querySelector("#btnForm");

addBtn.addEventListener("click", () => {
    modal.style.display = "block";

    const submitBtn = document.querySelector(".submit-modal");

    const name = document.querySelector("#name");
    const dueDate = document.querySelector("#due-date");
    const estimate = document.querySelector("#estimate");
    const type = document.querySelector("#task-type");

    btnForm.addEventListener("submit", async (e) => {
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

        const response = await fetch('/api/tasks');
        let tasks = await response.json();

        let newTask = {
            id: tasks[0].id + 1,
            type: type.value.toUpperCase(),
            color: color,
            name: name.value,
            dueDate: dueDate.value,
            estimate: estimate.value === "" ? '0' : estimate,
            completed: false
        };

        tasks.push(newTask);
        console.log(tasks);

        const request = await fetch("/api/tasks", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(tasks)
        });

        let divTask = document.createElement("div");
        divTask.classList.add("todo-component");
        divTask.innerHTML =
            `<li>
                <div>${name.value}</div>
                <div>Due date: ${dueDate.value}</div>
                <form action="" method="post" id="btnForm">
                    <button class="check" name="check" value="check-${newTask.id}">
                        <i class='bx bx-check'></i>
                    </button>
                    <button class="delete" name="delete" value="'delete-${newTask.id}">
                        <i class='bx bx-trash' ></i>
                    </button>
                </form>
            </li>`;

        document.querySelector(".todo-list").innerHTML += divTask.outerHTML;

        modal.style.display = "none";
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