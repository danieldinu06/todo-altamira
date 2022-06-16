let filer = document.querySelector(".filter-todo");

filer.addEventListener("click", async () => {
    const response = await fetch('/api/tasks');
    let tasks = await response.json();

    let todoList = document.querySelector(".todo-list");
    todoList.innerHTML = "";

    let order = -1;

    if (filer.value === 'ascending') order = 1;

    tasks = tasks.sort((a, b) => {
        let keyA = new Date(a.dueDate);
        let keyB = new Date(b.dueDate);

        if (keyA < keyB) return -order;
        if (keyA > keyB) return order;
        return 0;
    });

    tasks.forEach((task) => {
        let taskDiv = document.createElement("div");
        taskDiv.classList.add("todo-component");
        if (task.completed) taskDiv.classList.add("completed");

        let liElement = document.createElement("li");

        let nameDiv = document.createElement("div");
        nameDiv.style.color = task.color;
        nameDiv.innerHTML = task.name;

        let dueDateDiv = document.createElement("div");
        dueDateDiv.style.color = task.daysLeft === 1 ? 'red' : '';
        dueDateDiv.innerHTML = 'Due date: ' + task.dueDate;

        let daysLeftDiv = document.createElement("div");
        if (task.daysLeft < 0) task.daysLeft = 0;
        daysLeftDiv.innerHTML = 'Days left: ' + task.daysLeft;

        liElement.appendChild(nameDiv);
        liElement.appendChild(dueDateDiv);
        liElement.appendChild(daysLeftDiv);

        let formDiv = document.createElement("form");
        formDiv.setAttribute("id", "btnForm")
        formDiv.method = "post";
        formDiv.action = "";

        let checkBtn = document.createElement("button");
        checkBtn.classList.add("check");
        checkBtn.name = "check";
        checkBtn.value = `check-${task.id}`;
        checkBtn.innerHTML = `<i class='bx bx-check'></i>`;

        let deleteBtn = document.createElement("button");
        deleteBtn.classList.add("delete");
        deleteBtn.name = "delete";
        deleteBtn.value = `delete-${task.id}`;
        deleteBtn.innerHTML = `<i class='bx bx-trash'></i>`;

        formDiv.appendChild(checkBtn);
        formDiv.appendChild(deleteBtn);

        taskDiv.appendChild(liElement);
        taskDiv.appendChild(formDiv);

        todoList.appendChild(taskDiv);
    });

    const checkBtns = document.querySelectorAll(".check");
    const deleteBtns = document.querySelectorAll(".delete");

    deleteBtns.forEach(del);
    checkBtns.forEach(check);
})