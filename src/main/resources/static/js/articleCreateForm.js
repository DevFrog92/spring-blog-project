function init() {
    const createButton = document.querySelector(".create-btn");
    const createBtnHandler = () => {
        const title = document.querySelector("#title").value;
        const content = document.querySelector("#content").value;
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title,
                content,
            }),
        }).then((json) => {
            return json.json();
        })
            .then(({id}) => {
                alert("등록 완료되었습니다.");
                location.replace(`/articles/${id}`);
            })
    }

    createButton.addEventListener("click", createBtnHandler);
}

init();
