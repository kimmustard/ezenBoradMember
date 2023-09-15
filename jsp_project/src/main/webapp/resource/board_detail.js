// 댓글 등록시 필요한데이터 bno, writer, content
document.getElementById('cmtAddBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    console.log(cmtText);
    if (cmtText == null || cmtText == "") {
        alert('댓글을 입력해주세요.');
        return false;
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById("cmtWriter").value,
            content: cmtText
        };

        //전송 함수 functin 호출
        postCommentToServer(cmtData).then(result => {
            if (result > 0) {
                alert("댓글 등록성공!");
            } else {
                alert("댓글 등록 실패ㅠㅠ");
            }
            printCommentList(cmtData.bno);
        });


    }
})

//받을때
async function postCommentToServer(cmtData) {
    try {
        // 데이터보낼때 url 설정과 config설정
        const url = "/cmt/post";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'

            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text(); //0 or 1을 리턴(isOk 값)
        return result;
    } catch (error) {
        console.log(error);
    }
}


//화면에 댓글 뿌리기
function spreadCommentList(result) {    //result 댓글 list
    console.log(result);

    let div = document.getElementById('accordionExample');
    div.innerHTML = "";
    for (let i = 0; i < result.length; i++) {
        let str = `<div class="accordion-item">`;
        str += `<h2 class="accordion-header id="heading${i}">`;
        str += `<button class="accordion-button" type="button"`;
        str += `data-bs-toggle="collapse" data-bs-target="#collapse${i}"`;
        str += `aria-expanded="true" aria-controls="collapse${i}">`;
        str += `${result[i].cno},${result[i].writer},${result[i].regdate}`;
        str += `</button> </h2>`;
        str += `<div id="collapse${i}" class="accordion-collapse collapse show"`;
        str += `data-bs-parent="#accordionExample">`;
        str += `<div class="accordion-body">`;
        str += `<input type="text" class="form-control" id="cmtText" value="${result[i].content}">`;
        str += `<button type="button" data-cno="${result[i].cno}" class="btn btn-outline-warning cmtModBtn">%</button>`;
        str += `<button type="button" data-cno="${result[i].cno}" class="btn btn-outline-danger cmtDelBtn">X</button>`;
        str += `</div> </div> </div>`;
        div.innerHTML += str;   //누적해서 담기
    }


}

//서버에 댓글 리스트를 달라고 요청
async function getCommentListFromServer(bno) {
    try {
        const resp = await fetch('/cmt/list/' + bno); // /cmt/list/151
        const result = await resp.json();
        return result;

    } catch (error) {
        console.log(error);
    }
}

function printCommentList(bno) {
    getCommentListFromServer(bno).then(result => {
        console.log(result);
        if (result.length > 0) {
            spreadCommentList(result);
        } else {
            let div = document.getElementById('accordionExample');
            div.innerHTML = `comment가 없습니다.`;
        }
    })
}