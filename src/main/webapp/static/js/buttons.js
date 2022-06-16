const checkBtns = document.querySelectorAll(".check");
const deleteBtns = document.querySelectorAll(".delete");

deleteBtns.forEach(del);
checkBtns.forEach(check);

function del(btn) {
    btn.addEventListener("click", async (e) => {
        e.preventDefault();

        let btnId = btn.value.split("-")[1];

        const request = await fetch("/api/task/" + btnId, {
            method: "DELETE",
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            }
        });

        let task = btn.parentElement.parentElement;

        task.classList.add("fall");
        task.addEventListener('transitionend', () => {
            task.remove();
        })
    });
}

function check(btn) {
    btn.addEventListener("click", async (e) => {
        e.preventDefault();

        let btnId = btn.value.split("-")[1];

        const response = await fetch('/api/task/' + btnId);
        let task = await response.json();

        task.completed = true;

        btn.parentElement.parentElement.classList.add("completed");

        const request = await fetch("/api/task/" + btnId, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });
    });
}