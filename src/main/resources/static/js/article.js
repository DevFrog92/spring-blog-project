function init() {
    const deleteButton = document.querySelector(".delete-btn");


    const deleteBtnHandler = () => {
        let id = document.getElementById("articleId").value;

        fetch(`/api/articles/${id}`, {
            method: "DELETE"
        })
            .then(() => {
                alert("삭제가 완료되었습니다.");
                location.replace("/articles");
            });
    }

    deleteButton.addEventListener("click", deleteBtnHandler);
}

init();
