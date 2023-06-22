function init() {
    const editButton = document.querySelector(".edit-btn");
    const editBtnHandler = () => {
        const id = document.querySelector("#articleId").value;
        const title = document.querySelector("#title").value;
        const content = document.querySelector("#content").value;
        fetch(`/api/articles/${id}`, {
            method: "PUT",
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
                alert("수정 완료되었습니다.");
                location.replace(`/articles/${id}`);
            })
    }

    editButton.addEventListener("click", editBtnHandler);
}

init();
