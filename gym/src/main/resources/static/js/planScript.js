const monthNames = [
  "January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
];

let currentDate = new Date();

let calendar;

// 메인 캘린더
function renderCalendar(date) {
  const year = date.getFullYear();
  const month = date.getMonth();

  // 업데이트할 요소
  const monthElement = document.querySelector(".current-month");
  const yearElement = document.querySelector(".year");
  const datesElement = document.querySelector(".dates");

  // 월과 연도 설정
  monthElement.textContent = monthNames[month];
  yearElement.textContent = year;

  // 해당 월의 날짜 계산
  const firstDayOfMonth = new Date(year, month, 1).getDay(); // 월 첫 번째 날 요일
  const lastDateOfMonth = new Date(year, month + 1, 0).getDate(); // 월 마지막 날짜
  const lastDayOfLastMonth = new Date(year, month, 0).getDate(); // 이전 달 마지막 날짜

  // 캘린더 초기화
  datesElement.innerHTML = "";

  // 이전 달의 날짜 채우기
  const startDay = (firstDayOfMonth === 0 ? 7 : firstDayOfMonth) - 1;
  for (let i = startDay; i > 0; i--) {
    const btn = document.createElement("button");
    btn.textContent = lastDayOfLastMonth - i + 1;
    btn.style.opacity = "0.5"; // 비활성화 효과
    datesElement.appendChild(btn);
  }

  // 이번 달 날짜 채우기
  const today = new Date();
  for (let i = 1; i <= lastDateOfMonth; i++) {
    const btn = document.createElement("button");
    btn.innerHTML = `<time>${i}</time>`;

    // 오늘 날짜 강조
    if (
      year === today.getFullYear() &&
      month === today.getMonth() &&
      i === today.getDate()
    ) {
      btn.classList.add("today");
    }
    datesElement.appendChild(btn);
  }

  // 다음 달 날짜 채우기 (캘린더 채우기 용)
  const remainingCells = 42 - datesElement.children.length; // 6행 * 7열
  for (let i = 1; i <= remainingCells; i++) {
    const btn = document.createElement("button");
    btn.textContent = i;
    btn.style.opacity = "0.5"; // 비활성화 효과
    datesElement.appendChild(btn);
  }

  miniCalendarClickEvents();
}

function setupDropdown(buttonId, optionsId) {
  const dropdownButton = document.getElementById(buttonId);
  const options = document.getElementById(optionsId);

  // 드롭다운 버튼 클릭 시 옵션 표시/숨김
  dropdownButton.addEventListener("click", () => {
    options.style.display = options.style.display === "block" ? "none" : "block";
  });

  // 옵션 클릭 시 버튼에 값 표시
  options.addEventListener("click", (event) => {
    if (event.target.tagName === "DIV") {
      dropdownButton.textContent = event.target.textContent;
      options.style.display = "none"; // 옵션 숨기기
    }
  });

  // 외부 클릭 시 옵션 숨기기
  document.addEventListener("click", (event) => {
    if (!event.target.closest(`#${buttonId}`) && !event.target.closest(`#${optionsId}`)) {
      options.style.display = "none";
    }
  });
}

function formatDate(date) {

  const dayNames = ["일", "월", "화", "수", "목", "금", "토"]; // 요일 목록
  const formattedDate = `${date.getMonth() + 1}/${date.getDate()}(${dayNames[date.getDay()]})`;

  return formattedDate;
}

document.addEventListener("DOMContentLoaded", function () {
  // 이전/다음 버튼 이벤트 추가
  document.querySelector(".prev").addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar(currentDate);
  });

  document.querySelector(".next").addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar(currentDate);
  });

  // 초기 렌더링
  renderCalendar(currentDate);

  // 드롭다운 설정
  setupDropdown("dropdown-button-1", "options-1");
  setupDropdown("dropdown-button-2", "options-2");

  // modal
  const inputSchedule = document.querySelector('.input-schedule');
  const inputScheduleOpen = document.querySelector('.upd-schedule');
  const inputScheduleClose = document.querySelector('.input-schedule-close');

  inputScheduleOpen.addEventListener('click', function () {
    inputSchedule.style.display = 'block';
  });

  inputScheduleClose.addEventListener('click', function () {
    inputSchedule.style.display = 'none';
  });


  //메인 캘린더
  var calendarEl = document.getElementById("calendar");

  calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    dateClick: function (info) {
      // showTimeSelectionModal(info.dateStr);
      showDateSelected(info.dayEl, info.date);
    }
    
  });

  calendar.render();
  
});

// 미니 캘린더 날짜 클릭했을 때
function miniCalendarClickEvents() {
  const dateButtons = document.querySelectorAll(".dates button");
  dateButtons.forEach((btn) => {
    btn.addEventListener("click", function () {
      // 클릭한 날짜의 값 가져오기
      const selectedDate = this.querySelector("time") 
        ? this.querySelector("time").textContent 
        : this.textContent;

      const year = currentDate.getFullYear();
      const month = currentDate.getMonth() + 1; // JS에서 month는 0부터 시작하므로 +1 필요
      const day = selectedDate;

      // 선택한 날짜를 완전한 날짜 형식으로 저장
      const clickedDate = new Date(year, month - 1, day); // month는 0-indexed
      console.log("Selected Date:", clickedDate);

      const formattedDate = formatDate(clickedDate); // 예: '12/06(Wed)'

      // 변환된 날짜를 HTML 요소에 반영
      const dateElement = document.querySelector(".set-time-date span");
      dateElement.textContent = formattedDate;


      // GET 요청을 보낼 URL 생성
      const url = `/user/schedule/plan/${year}/${month}/${day}`;
      console.log("Request URL:", url);

      // XMLHttpRequest 객체 생성
      const xhr = new XMLHttpRequest();

      // 요청 초기화
      xhr.open("GET", url, true);

      // 요청 상태 변화 이벤트 처리
      xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
          if (xhr.status === 200) {
            // 요청 성공
            const response = JSON.parse(xhr.responseText); // 응답 데이터를 JSON으로 파싱
            console.log("Response Data:", response);
            // 응답 데이터를 처리하는 로직 추가

            const comment = response.comment;
            const planList = response.planList;
            const reservationList = response.reservationList;

            console.log("Comment:", comment);
            console.log("Plan List:", planList);
            console.log("Reservation List:", reservationList);

            // 분리된 데이터를 사용해 HTML을 업데이트
            updateThymeleafTemplate({ comment, planList, reservationList });

            calendar.gotoDate(clickedDate);
          } else {
            // 요청 실패
            console.error("Error with the GET request:", xhr.status, xhr.statusText);
          }
        }
      };

      // 요청 전송
      xhr.send();

      // 필요한 작업 수행
      window.selectedDate = clickedDate; // 전역 변수로 저장
      console.log("miniCalendarClickEvents selectedDate: "+ selectedDate);
    });
  });
}

// 비동기 응답을 받았을 때 
function updateThymeleafTemplate({ comment, planList, reservationList }) {

  // Comment Section 업데이트
  const commentDateElement = document.querySelector(".comment-content .comment-date span");
  const commentContentElement = document.querySelector(".comment-content .comment-c .cContent");
  const dietContentElement = document.querySelector(".comment-content .comment-c .fContent");

  if (comment) {
    // 날짜 형식 변환 (한국어 요일 출력)
    const date = new Date(comment.commentDate);
    const formattedDate = formatDate(date);

    // 데이터 설정
    commentDateElement.textContent = formattedDate;
    commentContentElement.textContent = comment.ccontent || "";
    dietContentElement.textContent = comment.fcontent || "";
  } else {
    // 데이터가 없을 때 빈 내용 설정
    commentDateElement.textContent = "";
    commentContentElement.textContent = "";
    dietContentElement.textContent = "";
  }
  console.log("updateThymeleaf selectedDate: "+ selectedDate);
}


// 메인 캘린더 날짜 클릭 시
function showDateSelected(selectedCell, selectedDate) {

  if (selectedCell.classList.contains("focused-day")) {
    selectedCell.classList.remove("focused-day"); // 스타일 제거
    return; // 추가 작업 중단
  }

  document.querySelectorAll(".fc-daygrid-day").forEach((cell) => {
    cell.classList.remove("focused-day");
  });

  // 클릭된 셀에 focus 스타일 추가
  selectedCell.classList.add("focused-day");
}